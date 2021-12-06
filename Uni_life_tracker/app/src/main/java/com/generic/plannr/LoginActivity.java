package com.generic.plannr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity  extends AppCompatActivity {

    // initialize variables
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private MainActivity activity;
    UserGateway ug = new UserGateway(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // connecting variables to UI features in activities by their id
        tiEmail = findViewById(R.id.ti_email_login);
        tiPassword = findViewById(R.id.ti_password_login);

        activity = new MainActivity();
    }

    private void openMain() {
        // Moves to the MainActivity
        activity.redirectActivity(this, MainActivity.class);
        finish();
    }

    public boolean LoginInput () {
        // Creates an instance of validator to be able to access its methods
        Validator input = new Validator();
        // Returns whether the login info inputted is valid
        return input.validateEntry(tiEmail, ug, tiEmail, tiPassword, false) &
                input.validateEntry(tiPassword, ug, tiEmail, tiPassword, false);
    }

    public void clickLogin(View view) {
        // If all login credentials are correct, go into the main page
        if (LoginInput()) {
            openMain();
        }
    }

    public void clickSignup(View view) {
        // Moves to the MainActivity
        activity.redirectActivity(this, SignUpActivity.class);
    }
}


