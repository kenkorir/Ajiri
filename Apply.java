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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Apply extends AppCompatActivity {

    private EditText firstName, age, idNo, phoneNo, location, email, description;

    private Button apply;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance() ;

    private Applicant ap;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        firstName = (MaterialEditText)findViewById(R.id.Full_Names_Apply);
        age = (MaterialEditText)findViewById(R.id.Age_Apply);
        idNo = (MaterialEditText)findViewById(R.id.id_No_Apply);
        phoneNo = (MaterialEditText)findViewById(R.id.Phone_Number_Apply);
        location = (MaterialEditText) findViewById(R.id.Location_Apply);
        email = (MaterialEditText)findViewById(R.id.Email_Address_Apply);
        description = (MaterialEditText) findViewById(R.id.Description);
        progressDialog = new ProgressDialog(this);

        apply = (Button)findViewById(R.id.Apply);



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String em  = email.getText().toString();
                String fn  = firstName.getText().toString();
                String ag = age.getText().toString();
                String id  = idNo.getText().toString();
                String phn = phoneNo.getText().toString();
                String lo  = location.getText().toString();
                String de  = description.getText().toString();

                if (!fn.isEmpty()) {
                    if (!ag.isEmpty()) {
                        if (!id.isEmpty()) {
                            if (!phn.isEmpty()) {

                                if (!lo.isEmpty()) {
                                    if (!em.isEmpty()) {
                                        if (!de.isEmpty()) {

                                            ap = new Applicant(fn, ag, id, phn, lo, em, de);

                                            progressDialog.setMessage("Submitting...");
                                            progressDialog.show();

                                            db.collection("Applicant")
                                                    .document(em)
                                                    .set(ap)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                            Toast.makeText(Apply.this, "Submitted Successfully.",
                                                                    Toast.LENGTH_SHORT).show();
                                                            Intent i = new Intent(Apply.this, MainActivity.class);
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(Apply.this, e.getMessage(),
                                                                    Toast.LENGTH_LONG).show();
                                                            finish();
                                                        }
                                                    });
                                        } else {
                                            Toast.makeText(Apply.this, "Please Fill in your Description",
                                                    Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                    } else {
                                        Toast.makeText(Apply.this, "Please Enter Your Email Address",
                                                Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                } else {
                                    Toast.makeText(Apply.this, "Please Enter Your Location",
                                            Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } else {
                                Toast.makeText(Apply.this, "Please Enter Your Phone Number",
                                        Toast.LENGTH_LONG).show();
                                return;
                            }
                        } else {
                            Toast.makeText(Apply.this, "Please Enter Your Identification Number",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                    } else {
                        Toast.makeText(Apply.this, "Please Enter Your Age.", Toast.LENGTH_LONG).show();
                        return;
                    }
                } else {
                    Toast.makeText(Apply.this, "Please Enter Your Full Names.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }


}
