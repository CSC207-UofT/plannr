package com.generic.plannr.Controllers;

import android.graphics.Color;
import android.widget.TextView;

import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class InputTextValidator implements Validator {
    /**
     * Validates inputs and displays the different error messages for the user inpupts
     *
     * @param userInput  the input from the main layout that will display the error
     * @param ug         the gateway connecting to the userinfo database
     * @param tiEmail    the user's email
     * @param tiPassword the user's password
     * @param isSignup   whether validation is for signup or login
     * @return whether the user input is valid and sets an error message if needed
     */
    public boolean validateEntry(TextInputLayout userInput, UserGateway ug, TextInputLayout tiEmail,
                                 TextInputLayout tiPassword, boolean isSignup) {
        String input = Objects.requireNonNull(userInput.getEditText()).getText().toString().trim();
        String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();

        if (input.isEmpty()) {
            userInput.setError("Field cannot be empty");
            return false;

        } else {
            userInput.setError(null);
            return true;
        }
    }

    /**
     * Validates inputs of events to make sure input is not empty
     *
     * @param userInput the input the user inputs into the textview
     * @return whether the user input is valid and sets an error message if needed
     */
    public boolean validateAddEvent(TextView userInput) {
        if (userInput.getText().toString().matches("")) {
            userInput.setHintTextColor(Color.RED);
            return false;
        } else {
            return true;
        }
    }
}
