package com.generic.ult;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Signup extends AppCompatActivity {

    private TextInputLayout textInputName;
    private TextInputLayout textInputUni;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputName = findViewById(R.id.text_name);
        textInputUni = findViewById(R.id.text_university);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_password);
    }

    private boolean validateNotEmpty(TextInputLayout textInput) {
        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();

        if (Input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        }
        else {
            textInput.setError(null);
            return true;
        }
    }

    public void signupInput(View v) {
        if (!(validateNotEmpty(textInputName) | validateNotEmpty(textInputUni) |
                validateNotEmpty(textInputEmail) | validateNotEmpty(textInputPassword))) {
            // Here we can get all the info we need
            // For example to get the email you can do textInputEmail.getEditTest().getText().toString()
        }


    }

}