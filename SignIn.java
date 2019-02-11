package com.example.Ajiri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    private EditText emailAdd, pass;
    private Button Login;

    private ProgressDialog progressDialog;

    private FirebaseAuth  mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        progressDialog = new ProgressDialog(this) ;
        emailAdd = (MaterialEditText)findViewById(R.id.EmailAddress_Login);
        pass = (MaterialEditText)findViewById(R.id.Password_Login);

        Login = (Button)findViewById(R.id.SignIN);

        mAuth = FirebaseAuth.getInstance();

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (emailAdd.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SignIn.this, "Please fill in the empty fields",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.setMessage("Logging in...");
                        progressDialog.show();

                        mAuth.signInWithEmailAndPassword(emailAdd.getText().toString(), pass.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();

                                            try {

                                                Intent login = new Intent(SignIn.this, Main.class);
                                                startActivity(login);
                                                finish();
                                            } catch (Exception e){
                                                Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            progressDialog.hide();
                                            Toast.makeText(SignIn.this, task.getException().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });

    }
}
