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
        tiName = findViewById(R.id.ti_name);
        tiEmail = findViewById(R.id.ti_email);
        tiPassword = findViewById(R.id.ti_password);
    }

    /**
     * Directs activity to the Main activity on Sign-Up button click.
     */
    private void openMain() {
        activity.redirectActivity(this, MainActivity.class);
        finish();
    }

    /**
     * Returns whether the information inputted matches the requirements.
     *
     * @return whether all the information has been validated
     */
    public boolean signupInput() {
        Validator input = new Validator(); // Creates an instance of validator to access the methods
        // Need to pass in tiEmail and Password each time because cannot be accessed UI elements in Validator class
        // If we pass in the string instead of the TextInputLayout then will not be able to set the error messages
        // Although it is inconvenient to keep passing it in, there are android related errors that are stopping us
        return input.validateEntry(tiName, ug, tiEmail, tiPassword, true) &
                input.validateEntry(tiEmail, ug, tiEmail, tiPassword, true) &
                input.validateEntry(tiPassword, ug, tiEmail, tiPassword, true);
    }

    /**
     * Stores sign-up credentials if correct, and direct activity to Main activity.
     *
     * @param view a View for the device screen.
     */
    public void clickSignup(View view) {
        if (signupInput()) {
            String name = Objects.requireNonNull(tiName.getEditText()).getText().toString();
            String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();
            String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

            ug.saveToDatabase(new User(name, email, password));

            openMain();
            ug.updateLoggedInUser(email);
        }
    }

    /**
     * Directs activity to the Log-In activity on Log-In button click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogin(View view) {
        activity.redirectActivity(this, LoginActivity.class);
    }
}