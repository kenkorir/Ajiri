package com.example.Ajiri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private Button btnLoginM, btnSignupM, btnApplyM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        btnSignupM = (Button)findViewById(R.id.btnSignUpmain);
        btnLoginM = (Button)findViewById(R.id.btnLoginmain);
        btnApplyM = (Button)findViewById(R.id.btnApplymain);

        btnLoginM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, SignIn.class);
                startActivity(login);

            }
        });

        btnSignupM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sign = new Intent(MainActivity.this, Signup.class);
                startActivity(Sign);
            }
        });

        btnApplyM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apply = new Intent(MainActivity.this, Apply.class);
                startActivity(apply);

            }
        });
    }
}
