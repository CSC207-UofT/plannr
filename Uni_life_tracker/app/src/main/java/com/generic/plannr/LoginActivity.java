/* Plannr by Generic Name
 *
 * This file contains methods for activity_login.xml.
 */
package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    // initialize variables
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private MainActivity activity;
    UserGateway ug = new UserGateway(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // connecting variables to UI features in activities by their id
        tiEmail = findViewById(R.id.ti_email_login);
        tiPassword = findViewById(R.id.ti_password_login);

        activity = new MainActivity();
    }

    /**
     * Directs activity to the Main activity.
     */
    private void openMain() {
        activity.redirectActivity(this, MainActivity.class);
        finish();
    }

    /**
     * Returns whether login input is valid.
     */
    public boolean loginInput() {
        Validator input = new Validator(); // Creates an instance of validator to be able to access its methods

        return input.validateEntry(tiEmail, ug, tiEmail, tiPassword, false) &
                input.validateEntry(tiPassword, ug, tiEmail, tiPassword, false);
    }

    /**
     * Directs activity to the Main activity on
     * button click, if login credentials are correct.
     *
     * @param view a View for the device screen.
     */
    public void clickLogin(View view) {
        // If all login credentials are correct, go into the main page
        if (loginInput()) {
            openMain();
            ug.updateLoggedInUser(Objects.requireNonNull(tiEmail.getEditText()).getText().toString());
        }
    }

    /**
     * Directs activity to the Sign-Up activity on button click.
     *
     * @param view a View for the device screen.
     */
    public void clickSignup(View view) {
        activity.redirectActivity(this, SignUpActivity.class);
    }
}


