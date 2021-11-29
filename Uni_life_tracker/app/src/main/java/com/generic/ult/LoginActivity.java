package com.generic.ult;

import Database.UserInfoDatabaseHelper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class LoginActivity  extends AppCompatActivity {
    private static final Pattern PASSWORD_REQ =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^&+=]).{6,}$");

    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    UserInfoDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        tiEmail = findViewById(R.id.ti_email_login);
        tiPassword = findViewById(R.id.ti_password_login);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> {

            // If all signup credentials are correct, store the credentials
            // and go into the main page
            if (LoginInput()) {

                String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();
                String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

                dbhelper = new UserInfoDatabaseHelper(LoginActivity.this);
                dbhelper.openDatabase();
                //dbhelper.insertUserInfo(email, password);

                //TODO: add lines that compare the user input to the sign up information in the database

                openMain();
            }
        });
    }

    private void openMain() {

        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

    private boolean validate(TextInputLayout textInput) {
        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();

        if (Input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        } else if (textInput == tiEmail && !Patterns.EMAIL_ADDRESS.matcher(Input).matches()) {
            textInput.setError("Please enter a valid email address");
            return false;
        } else if (textInput == tiPassword && !PASSWORD_REQ.matcher(Input).matches()) {
            StringBuilder str = passwordReq(Input);
            textInput.setError(str.toString());
            return false;
        } else {
            textInput.setError(null);
            return true;
        }
    }

    private StringBuilder passwordReq(String Input) {
        Pattern uppercase = Pattern.compile(getString(R.string.uppercase));
        Pattern lowercase = Pattern.compile(getString(R.string.lowercase));
        Pattern number = Pattern.compile(getString(R.string.number));
        Pattern specialChar = Pattern.compile(getString(R.string.specialChar));
        Pattern minChar = Pattern.compile(getString(R.string.minLength));
        StringBuilder str = new StringBuilder();
        str.append("Your password requires: \n");
        if (!minChar.matcher(Input).find())
        {
            str.append("- A length of at least 6 characters \n");
        }
        if (!uppercase.matcher(Input).find())
        {
            str.append("- At least 1 uppercase character \n");
        }
        if (!lowercase.matcher(Input).find())
        {
            str.append("- At least 1 lowercase character \n");
        }
        if (!number.matcher(Input).find())
        {
            str.append("- At least 1 number \n");
        }
        if (!specialChar.matcher(Input).find())
        {
            str.append("- At least 1 special character \n");
        }
        return str;
    }

    public boolean LoginInput() {
        return validate(tiEmail) & validate(tiPassword);
    }

}
