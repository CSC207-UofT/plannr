package com.generic.plannr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity  extends AppCompatActivity {

    // initialize variables
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // connecting variables to UI features in activities by their id
        tiEmail = findViewById(R.id.ti_email_login);
        tiPassword = findViewById(R.id.ti_password_login);
    }

    private void openMain() {
        // Moves to the MainPageActivity
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

    public boolean LoginInput () {
        // Creates an instance of validator to be able to access its methods
        Validator input = new Validator();
        // Opens the database, so it can be passed in since it needs an activity
        UserInfoDatabaseHelper user = createDatabase();
        // Returns whether the login info inputted is valid
        return input.validate(tiEmail, user, tiEmail, tiPassword, false) &
                input.validate(tiPassword, user, tiEmail, tiPassword, false);
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
}


