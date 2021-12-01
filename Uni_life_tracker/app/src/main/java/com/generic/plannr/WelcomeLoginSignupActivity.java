package com.generic.plannr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class WelcomeLoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_login_signup);
        Button btnSignUp = findViewById(R.id.btn_sign_up_main);
        btnSignUp.setOnClickListener(v -> startActivity(new Intent(WelcomeLoginSignupActivity.this, SignUpActivity.class)));
        Button btnLogin = findViewById(R.id.btn_login_main);
        btnLogin.setOnClickListener(v -> startActivity(new Intent(WelcomeLoginSignupActivity.this, LoginActivity.class)));
    }
}
