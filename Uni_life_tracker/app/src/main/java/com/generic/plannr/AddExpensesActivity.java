package com.generic.plannr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Gateways.ExpenseGateway;
import com.google.android.material.textfield.TextInputLayout;


import java.util.Objects;


public class AddExpensesActivity extends AppCompatActivity {

    private TextInputLayout textInputName, textInputAmount;
    ExpenseGateway eg = new ExpenseGateway(AddExpensesActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        // connecting variables to UI features in activities
        textInputName = findViewById(R.id.add_expense_name);
        textInputAmount = findViewById(R.id.add_expense_amount);

    }


    private void openAddExpensesView() {
        // Opens the expense list activity (ExpensesActivity)
        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);
    }

    /**
     * Validates inputs and displays the different error messages for the user inputs
     *
     * @param textInput The password that the user types into the textbox
     * @return whether the user input is valid and sets an error message if needed
     *
     */
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



    /**
     * Checks whether the information inputted matches the requirements and opens the expense and user info database
     * in order to insert an expense into the database, then opens the expense list view
     * @param v The current view
     *
     */
    public void AddExpensesInput(View v) {
        if (validate(textInputName) & validate(textInputAmount)) {
            String name = Objects.requireNonNull(textInputName.getEditText()).getText().toString();
            String amount = Objects.requireNonNull(textInputAmount.getEditText()).getText().toString();

            // Adds all the user's info into the database using the gateway
            eg.saveToDatabase(new Expense(name, Double.parseDouble(amount)));
            openAddExpensesView();

        }
    }

        public void ClickBack (View view){
        // clicking the arrow back button
            Intent intent = new Intent(this, ExpensesActivity.class);
            startActivity(intent);
        }
    }

