package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity  extends AppCompatActivity {

    // initialize variables
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private MainPageActivity activity;

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
        // Creates an instance of validator to be able to access its methods
        Validator input = new Validator();
        // Opens the database, so it can be passed in since it needs an activity
        UserInfoDatabaseHelper user = createDatabase();
        // Returns whether the login info inputted is valid
        return input.validateEntry(tiEmail, user, tiEmail, tiPassword, false) &
                input.validateEntry(tiPassword, user, tiEmail, tiPassword, false);
    }

    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(LoginActivity.this);
        user.openDatabase();
        return user;
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


