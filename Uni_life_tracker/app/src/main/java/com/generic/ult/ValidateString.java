package com.generic.ult;

import Database.UserInfoDatabaseHelper;
import android.util.Patterns;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidateString {
    // At least one number, uppercase letter, lowercase letter and special char. Min of 6 characters
    private static final Pattern PASSWORD_REQ =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^/*_&+=]).{6,}$");

    /**
     * Validates inputs and displays the different error messages for the user inpupts
     *
     * @param textInput The password that the user types into the textbox
     * @return whether the user input is valid and sets a error message if needed
     *
     */
    public boolean validate(TextInputLayout textInput, UserInfoDatabaseHelper user, TextInputLayout tiEmail,
                             TextInputLayout tiPassword) {
        String input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();
        ValidateString info = new ValidateString();

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
            StringBuilder str = info.passwordReq(input);
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
    public StringBuilder passwordReq(String input) {
        Pattern uppercase = Pattern.compile(".*[A-Z].*");
        Pattern lowercase = Pattern.compile(".*[a-z].*");
        Pattern number = Pattern.compile(".*[0-9].*");
        Pattern specialChar = Pattern.compile(".*[!@#$%^&*()-_=+\\[\\]\\\\/].*");
        Pattern minChar = Pattern.compile(".{6,}");
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
        if (!specialChar.matcher(input).find())
        {
            str.append("- At least 1 special character !@#$%^&*()-_=+/[]\\ \n");
        }
        return str;
    }
}
