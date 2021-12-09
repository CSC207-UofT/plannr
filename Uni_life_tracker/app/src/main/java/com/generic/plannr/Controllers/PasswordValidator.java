/* Plannr by Generic Name
 *
 * This file represents a Validator class
 * which is used to validate a user's input.
 */
package com.generic.plannr.Controllers;

import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class PasswordValidator implements Validator {

    // At least one number, uppercase letter, lowercase letter and special char. Min of 6 characters
    private static final Pattern PASSWORD_REQ =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–\\[\\]?/\\\\*_$^+=]).{6,}$");

    /**
     * Validates inputs and displays the different error messages for the user inputs.
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
        String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

        if (input.isEmpty()) {
            userInput.setError("Field cannot be empty");
            return false;
        } else if (isSignup && userInput == tiPassword && !PASSWORD_REQ.matcher(input).matches()) {
            StringBuilder str = passwordReq(input);
            userInput.setError(str.toString());
            return false;
        } else if (!isSignup && userInput == tiPassword && !ug.getPassword(email).equals(password)
                && !ug.uniqueEmail(email)) {
            userInput.setError("The password you entered is incorrect");
            return false;
        } else {
            userInput.setError(null);
            return true;
        }
    }

    /**
     * Uses Regex to make sure the password inputted is secure
     *
     * @param input The password that the user types into the textbox
     * @return a string that includes all the requirements that the password violates, if any
     */
    public StringBuilder passwordReq(String input) {
        Pattern uppercase = Pattern.compile(".*[A-Z].*");
        Pattern lowercase = Pattern.compile(".*[a-z].*");
        Pattern number = Pattern.compile(".*[0-9].*");
        Pattern specialChar = Pattern.compile(".*[!@#&()–\\[\\]?/\\\\*_$^+=].*");
        Pattern minChar = Pattern.compile(".{6,}");
        StringBuilder str = new StringBuilder();
        str.append("Your password requires: \n");
        if (!minChar.matcher(input).find()) {
            str.append("- A length of at least 6 characters \n");
        }
        if (!uppercase.matcher(input).find()) {
            str.append("- At least 1 uppercase character \n");
        }
        if (!lowercase.matcher(input).find()) {
            str.append("- At least 1 lowercase character \n");
        }
        if (!number.matcher(input).find()) {
            str.append("- At least 1 number \n");
        }
        if (!specialChar.matcher(input).find()) {
            str.append("- At least 1 special character !@#$%^&*()-_=+/[]\\ \n");
        }
        return str;
    }
}
