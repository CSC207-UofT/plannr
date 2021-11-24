package com.generic.ult;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    // At least one number, uppercase letter, lowercase letter and special char. Min of 6 characters
    private static final Pattern PASSWORD_REQ =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^&+=]).{6,}$");

    private TextInputLayout textInputName;
    private TextInputLayout textInputUni;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputName = findViewById(R.id.text_name);
        textInputUni = findViewById(R.id.text_university);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_password);

        Button signupButton = findViewById(R.id.btn_sign_up);
        signupButton.setOnClickListener(v -> {

            // If all signup credentials are correct, store the credentials
            // and go into the main page
            if (signupInput()) {
                saveCredentialsAndOpenMain();
            }
        });
    }


    private void saveCredentialsAndOpenMain() {
        saveName();
        saveUni();
        saveEmail();
        savePassword();
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

    private boolean validate(TextInputLayout textInput) {
        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();

        if (Input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        } else if (textInput == textInputEmail && !Patterns.EMAIL_ADDRESS.matcher(Input).matches()) {
            textInput.setError("Please enter a valid email address");
            return false;
        } else if (textInput == textInputPassword && !PASSWORD_REQ.matcher(Input).matches()) {
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

    public boolean signupInput() {
        return validate(textInputName) & validate(textInputUni) &
                validate(textInputEmail) & validate(textInputPassword);
    }

    private void saveName() {
        String name = Objects.requireNonNull(textInputName.getEditText()).getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("NAME", name);
        prefsEditor.apply();

    }
    private void saveUni() {
        String uni = Objects.requireNonNull(textInputUni.getEditText()).getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("UNIVERSITY", uni);
        prefsEditor.apply();

    }
    private void saveEmail() {
        String email = Objects.requireNonNull(textInputEmail.getEditText()).getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("EMAIL", email);
        prefsEditor.apply();

    }
    private void savePassword() {
        String password = Objects.requireNonNull(textInputPassword.getEditText()).getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("PASSWORD", password);
        prefsEditor.apply();

    }

    public String getName() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("NAME", null);
    }

    public String getUni() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("UNIVERSITY", null);
    }

    public String getEmail() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("EMAIL", null);
    }

    public String getPassword() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("PASSWORD", null);
    }

}