package com.generic.ult;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.Objects;


public class AddExpenses extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

//    private TextInputLayout textInputName;
//    private TextInputLayout textInputAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
//        textInputName = findViewById(R.id.add_e_name);
//        textInputAmount = findViewById(R.id.add_e_amount);

    }

//    private boolean validate(TextInputLayout textInput) {
//        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();
//
//        if (Input.isEmpty()) {
//            textInput.setError("Field cannot be empty");
//            return false;
//        } else {
//            textInput.setError(null);
//            return true;
//        }
//    }
//    public void ExpensesViewInput(View v) {
//        if (!(validate(textInputName) | validate( textInputAmount))) {
//            // Here we can get all the info we need
//            // For example to get the email you can do textInputEmail.getEditTest().getText().toString()
//        }
//
//
//    }

}
