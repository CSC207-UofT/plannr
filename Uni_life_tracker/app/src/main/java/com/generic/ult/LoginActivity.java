package com.generic.ult;

import Database.UserInfoDatabaseHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity  extends AppCompatActivity {

    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        tiEmail = findViewById(R.id.ti_email_login);
        tiPassword = findViewById(R.id.ti_password_login);
    }

    private void openMain() {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }


    private boolean validate(TextInputLayout textInput) {
        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();
        String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();
        String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

        UserInfoDatabaseHelper user = createDatabase();

        if (Input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        } else if (textInput == tiEmail && user.uniqueEmail(email)) {
            textInput.setError("The email you entered does not belong to any account");
            return false;
        } else if (textInput == tiPassword && !user.getPassword(email).equals(password) && !user.uniqueEmail(email)) {
            textInput.setError("The password you entered is incorrect");
            return false;
        } else {
            textInput.setError(null);
            user.updateLoggedInUser(email);
            return true;
        }
    }

    public boolean LoginInput () {
            return validate(tiEmail) & validate(tiPassword);
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


