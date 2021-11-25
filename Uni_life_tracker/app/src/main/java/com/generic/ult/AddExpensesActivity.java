package com.generic.ult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class AddExpensesActivity extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;

    private TextInputLayout textInputName, textInputAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        textInputName = findViewById(R.id.add_expense_name);
        textInputAmount = findViewById(R.id.add_expense_amount);
    }

    // change ExpensesLandingActivity to ExpensesActivity when merged
    private void openAddExpensesView() {
        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);
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
    public void AddExpensesInput(View b) {
        if (!(validate(textInputName) & validate( textInputAmount))) {
            // Here we can get all the info we need
            // For example to get the email you can do textInputEmail.getEditTest().getText().toString()
        }else{openAddExpensesView();}
    }

    public void ClickBack(View view) {
        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);
    }
}
