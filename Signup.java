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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Signup extends AppCompatActivity {
    private EditText emailAdd, password, name, idNo,location;
    private Button SignUp;
    private ProgressDialog progressDialog;

    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    private Client c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       try {
           mAuth = FirebaseAuth.getInstance();
       } catch (Exception e){
           Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
       }

        progressDialog = new ProgressDialog(this);
        name = (MaterialEditText)findViewById(R.id.Full_Names_SignUp);
        idNo = (MaterialEditText)findViewById(R.id.id_No_Signup);
        location = (MaterialEditText)findViewById(R.id.Location_Signup);
        emailAdd = (MaterialEditText)findViewById(R.id.Email_Address_signup);
        password = (MaterialEditText)findViewById(R.id.Password_signup);

        SignUp = findViewById(R.id.SigningUp);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                final String E = emailAdd.getText().toString();
                String P = password.getText().toString();
                String na = name.getText().toString();
                String id = idNo.getText().toString();
                String lo = location.getText().toString();

                if (!na.isEmpty()) {
                    if (!id.isEmpty()) {
                        if (!lo.isEmpty()) {
                            if (!E.isEmpty()) {
                                if (!P.isEmpty()) {

                                    c = new Client(na, E, lo, id);

                                    progressDialog.setMessage("Registering user...");
                                    progressDialog.show();
                                    mAuth.createUserWithEmailAndPassword(emailAdd.getText().toString().trim(), password.getText().toString().trim()).
                                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        progressDialog.dismiss();

                                                        db.collection("Client")
                                                                .document(E)
                                                                .set(c)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Toast.makeText(Signup.this, "User created Successfully.",
                                                                                Toast.LENGTH_SHORT).show();
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });

                                                        Intent I = new Intent(Signup.this, SignIn.class);
                                                        startActivity(I);
                                                        finish();

                                                    } else {
                                                        Toast.makeText(Signup.this, task.getException().getMessage(),
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(Signup.this, "Please enter the Password",
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                Toast.makeText(Signup.this, "Please enter the email",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } else {
                            Toast.makeText(Signup.this, "Please enter your Location", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(Signup.this, "Please enter your Identification  Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(Signup.this, "Please enter your names", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
