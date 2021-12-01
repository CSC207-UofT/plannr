package com.generic.plannr;

import Database.UserInfoDatabaseHelper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    // At least one number, uppercase letter, lowercase letter and special char. Min of 6 characters
    private static final Pattern PASSWORD_REQ =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^&+=]).{6,}$");

    // initialize variables
    private TextInputLayout tiName;
    private TextInputLayout tiUniversity;
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // connecting variables to UI features in activities
        tiName = findViewById(R.id.ti_name);
        tiUniversity = findViewById(R.id.ti_university);
        tiEmail = findViewById(R.id.ti_email);
        tiPassword = findViewById(R.id.ti_password);
        Button btnSignUp = findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(v -> {

            // If all signup credentials are correct, store the credentials
            // and go into the main page
            if (signupInput()) {
                String name = Objects.requireNonNull(tiName.getEditText()).getText().toString();
                String uni = Objects.requireNonNull(tiUniversity.getEditText()).getText().toString();
                String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();
                String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

                UserInfoDatabaseHelper user = createDatabase();
                // Adds all the user's info into the database
                user.insertUserInfo(name, uni, email, password);

                openMain();
            }
        });
    }

    private void openMain() {
        // Opens the main activity
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

    /**
     * Validates inputs and displays the different error messages for the user inpupts
     *
     * @param textInput The password that the user types into the textbox
     * @return whether the user input is valid and sets a error message if needed
     *
     */
    private boolean validate(TextInputLayout textInput) {
        String input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();
        UserInfoDatabaseHelper user = createDatabase();

        if (input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        } else if (textInput == tiEmail && !Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            textInput.setError("Please enter a valid email address");
            return false;
        } else if(textInput == tiEmail && !user.uniqueEmail(input)){
            textInput.setError("This email is already being used");
            return false;
        } else if (textInput == tiPassword && !PASSWORD_REQ.matcher(input).matches()) {
            StringBuilder str = passwordReq(input);
            textInput.setError(str.toString());
            return false;
        } else {
            textInput.setError(null);
            return true;
        }
    }

    /**
     * Uses regex to make sure the password inputted is secure
     *
     * @param input The password that the user types into the textbox
     * @return string that includes all the requirements that the password violates, if any
     *
     */
    private StringBuilder passwordReq(String input) {
        Pattern uppercase = Pattern.compile(getString(R.string.uppercase));
        Pattern lowercase = Pattern.compile(getString(R.string.lowercase));
        Pattern number = Pattern.compile(getString(R.string.number));
        Pattern specialChar = Pattern.compile(getString(R.string.specialChar));
        Pattern minChar = Pattern.compile(getString(R.string.minLength));
        StringBuilder str = new StringBuilder();
        str.append("Your password requires: \n");
        if (!minChar.matcher(input).find())
        {
            str.append("- A length of at least 6 characters \n");
        }
        if (!uppercase.matcher(input).find())
        {
            str.append("- At least 1 uppercase character \n");
        }
        if (!lowercase.matcher(input).find())
        {
            str.append("- At least 1 lowercase character \n");
        }
        if (!number.matcher(input).find())
        {
            str.append("- At least 1 number \n");
        }
        if (!specialChar.matcher(Input).find())
        {
            str.append("- At least 1 special character \n");
        }
        return str;
    }

    /**
     * Checks whether the information inputted matches the requirements
     *
     * @return whether all the information has been validated
     */
    public boolean signupInput() {
        return validate(tiName) & validate(tiUniversity) &
                validate(tiEmail) & validate(tiPassword);
    }

    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(SignUpActivity.this);
        user.openDatabase();
        return user;
    }

}