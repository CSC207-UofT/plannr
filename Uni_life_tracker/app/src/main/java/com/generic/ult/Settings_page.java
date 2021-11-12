package com.generic.ult;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class Settings_page extends AppCompatActivity {


    private TextInputLayout textInputName;
    private TextInputLayout textInputUni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        textInputName = findViewById(R.id.settings_name);
        textInputUni = findViewById(R.id.settings_uni);
    }

    private boolean validate(TextInputLayout textInput) {
        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();

        if (Input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        } else {
            textInput.setError(null);
            return true;
        }
    }

    public void settingsInput(View v) {
        if (!(validate(textInputName) | validate(textInputUni))) {
            // Here we can get all the info we need

        }


    }
}