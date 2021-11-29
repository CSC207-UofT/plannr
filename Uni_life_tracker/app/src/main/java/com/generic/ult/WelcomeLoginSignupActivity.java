package com.generic.ult;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class WelcomeLoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_login_signup);
        Button btnSignUp = findViewById(R.id.btn_sign_up_main);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeLoginSignupActivity.this, SignUpActivity.class));
            }
        });
        Button btnLogin = findViewById(R.id.btn_login_main);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeLoginSignupActivity.this, LoginActivity.class));
            }
        });


    }
}







