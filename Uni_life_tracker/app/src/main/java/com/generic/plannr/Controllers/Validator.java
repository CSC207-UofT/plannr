package com.generic.plannr.Controllers;

import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

public interface Validator {
    /**
     * Validates inputs and displays the different error messages for the user inputs.
     * This method varies based on the type of Validator needed which is an example
     * usage of the Strategy Design Pattern
     *
     * @param userInput the input from the main layout that will display the error
     * @param ug the gateway connecting to the app database
     * @param tiEmail the user's email
     * @param tiPassword the user's password
     * @param isSignup whether validation is for signup or login
     *
     * @return whether the user input is valid and sets an error message if needed
     *
     */
     boolean validateEntry(TextInputLayout userInput, UserGateway ug, TextInputLayout tiEmail,
                                 TextInputLayout tiPassword, boolean isSignup);
}
