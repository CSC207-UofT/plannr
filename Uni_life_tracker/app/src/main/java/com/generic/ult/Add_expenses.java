package com.generic.ult;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class Add_expenses extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private TextInputLayout textInputName;
    private TextInputLayout textInputAmount;
    private TextInputLayout textInputDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        textInputName = findViewById(R.id.add_e_name);
        textInputAmount = findViewById(R.id.add_e_amount);
        textInputDate = findViewById(R.id.add_e_date);


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
    public void signupInput(View v) {
        if (!(validate(textInputName) | validate( textInputAmount) |
                validate(textInputDate))) {
            // Here we can get all the info we need
            // For example to get the email you can do textInputEmail.getEditTest().getText().toString()
        }


    }



}