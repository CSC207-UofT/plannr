package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity  extends AppCompatActivity {

    // initialize variables
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private MainPageActivity activity;
    UserGateway ug = new UserGateway(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // connecting variables to UI features in activities by their id
        tiEmail = findViewById(R.id.ti_email_login);
        tiPassword = findViewById(R.id.ti_password_login);

        activity = new MainPageActivity();
    }

    private void openMain() {
        // Moves to the MainPageActivity
        activity.redirectActivity(this, MainPageActivity.class);
    }

    public boolean LoginInput () {
        // Creates instances of validators to be able to access its methods
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();

        // Returns whether the login info inputted is valid
        return emailValidator.validateEntry(tiEmail, ug, tiEmail, tiPassword, false) &
                passwordValidator.validateEntry(tiPassword, ug, tiEmail, tiPassword, false);
    }

    public void clickLogin(View view) {
        // If all login credentials are correct, go into the main page
        if (LoginInput()) {
            openMain();
        }
    }

    public void clickSignup(View view) {
        // Moves to the MainPageActivity
        activity.redirectActivity(this, SignUpActivity.class);
    }
}


