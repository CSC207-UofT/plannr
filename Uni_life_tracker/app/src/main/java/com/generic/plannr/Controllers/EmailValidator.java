package com.generic.plannr.Controllers;

import android.util.Patterns;

import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class EmailValidator implements Validator {


    public boolean validateEntry(TextInputLayout userInput, UserGateway ug, TextInputLayout tiEmail,
                                 TextInputLayout tiPassword, boolean isSignup) {
        String input = Objects.requireNonNull(userInput.getEditText()).getText().toString().trim();
        String email = Objects.requireNonNull(tiEmail.getEditText()).getText().toString();

        if (input.isEmpty()) {
            userInput.setError("Field cannot be empty");
            return false;
        } else if (isSignup && userInput == tiEmail && !Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            userInput.setError("Please enter a valid email address");
            return false;
        } else if(isSignup && userInput == tiEmail && !ug.uniqueEmail(input)){
            userInput.setError("This email is already being used");return false;

        } else if (!isSignup && userInput == tiEmail && ug.uniqueEmail(email)) {
            userInput.setError("The email you entered does not belong to any account");
            return false;
        } else {
            userInput.setError(null);
            ug.updateLoggedInUser(email);
            return true;
        }
    }
}
