package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {


    // initialize variables
    private TextInputLayout tiName;
    private TextInputLayout tiUniversity;
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        activity = new MainActivity();

        // connecting variables to UI features in activities by their id
        tiName = findViewById(R.id.ti_name);
        tiUniversity = findViewById(R.id.ti_university);
        tiEmail = findViewById(R.id.ti_email);
        tiPassword = findViewById(R.id.ti_password);
    }

    private void openMain() {
        // Opens the main activity
        activity.redirectActivity(this, MainActivity.class);
        finish();
    }

    /**
     * Checks whether the information inputted matches the requirements
     *
     * @return whether all the information has been validated
     */
    public boolean signupInput() {
        // Opens the database and passes in all the info
        UserInfoDatabaseHelper user = createDatabase();
        // Creates an instance of validator to access the methods
        Validator input = new Validator();
        // Returns whether info is validator and any error messages if it isn't
        // Need to pass in tiEmail and Password each time because cannot be accessed UI elements in Validator class
        // If we pass in the string instead of the TextInputLayout then will not be able to set the error messages
        // Although it is inconvenient to keep passing it in, there are android related errors that are stopping us
        return input.validate(tiName, user, tiEmail, tiPassword, true) &
                input.validate(tiUniversity,  user, tiEmail, tiPassword, true) &
                input.validate(tiEmail,  user, tiEmail, tiPassword, true) &
                input.validate(tiPassword, user, tiEmail, tiPassword, true);
    }

    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(SignUpActivity.this);
        user.openDatabase();
        return user;
    }

    public void clickSignup(View view) {
        // If all signup credentials are correct, store the credentials
        // and go into the main page
        if (signupInput()) {
            String name = Objects.requireNonNull(tiName.getEditText()).getText().toString();
            String uni = Objects.requireNonNull(tiUniversity.getEditText()).getText().toString();
            String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();
            String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

            UserInfoDatabaseHelper user = createDatabase();
            user.insertUserInfo(name, uni, email, password);

            openMain();
        }
    }

    public void clickLogin(View view) {
        activity.redirectActivity(this, LoginActivity.class);
    }
}