package com.generic.plannr;

import com.generic.plannr.Database.UserInfoDatabaseHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {


    // initialize variables
    private TextInputLayout tiName;
    private TextInputLayout tiUniversity;
    public TextInputLayout tiEmail;
    public TextInputLayout tiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // connecting variables to UI features in activities by their id
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
     * Checks whether the information inputted matches the requirements
     *
     * @return whether all the information has been validated
     */
    public boolean signupInput() {
        UserInfoDatabaseHelper user = createDatabase();

        Validator input = new Validator();
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
}