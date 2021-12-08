/* Plannr by Generic Name
 *
 * This file contains methods for activity_sign_up.xml.
 */
package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Entities.User;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {


    // initialize variables
    private TextInputLayout tiName;
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private MainActivity activity;
    UserGateway ug = new UserGateway(SignUpActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        activity = new MainActivity();

        // connecting variables to UI features in activities by their id
        tiName = findViewById(R.id.ti_name);
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
        // Creates an instance of validator to access the methods
        Validator input = new Validator();
        // Returns whether info is validator and any error messages if it isn't
        // Need to pass in tiEmail and Password each time because cannot be accessed UI elements in Validator class
        // If we pass in the string instead of the TextInputLayout then will not be able to set the error messages
        // Although it is inconvenient to keep passing it in, there are android related errors that are stopping us
        return input.validateEntry(tiName, ug, tiEmail, tiPassword, true) &
                input.validateEntry(tiEmail, ug, tiEmail, tiPassword, true) &
                input.validateEntry(tiPassword, ug, tiEmail, tiPassword, true);
    }

    public void clickSignup(View view) {
        // If all signup credentials are correct, store the credentials
        // and go into the main page
        if (signupInput()) {
            String name = Objects.requireNonNull(tiName.getEditText()).getText().toString();
            String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();
            String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

            ug.saveToDatabase(new User(name, email, password));

            openMain();
            ug.updateLoggedInUser(email);
        }
    }

    public void clickLogin(View view) {
        activity.redirectActivity(this, LoginActivity.class);
    }
}