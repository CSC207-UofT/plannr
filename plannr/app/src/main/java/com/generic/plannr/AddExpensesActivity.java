/* Plannr by Generic Name
 *
 * This file contains methods for activity_add_expenses.xml.
 */
package com.generic.plannr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Gateways.ExpenseGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;


import java.util.Objects;

@SuppressWarnings("ALL")
public class AddExpensesActivity extends AppCompatActivity {

    private TextInputLayout textInputName, textInputAmount;
    ExpenseGateway eg = new ExpenseGateway(AddExpensesActivity.this);
    UserGateway ug = new UserGateway(AddExpensesActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        textInputName = findViewById(R.id.add_expense_name);
        textInputAmount = findViewById(R.id.add_expense_amount);
    }

    /**
     * Validates inputs and displays the different error messages for the user inputs
     *
     * @param textInput The password that the user types into the TextBox
     * @return whether the user input is valid and sets an error message if needed
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
     * Directs activity to the Expenses activity on back arrow icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickBack(View view) {
        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);
    }

    /**
     * Checks whether the information inputted matches the requirements and opens the expense and user info database
     * in order to insert an expense into the database, then opens the expense list view
     *
     * @param v The current view
     */
    public void clickSaveExpense(View v) {
        if (validate(textInputName) & validate(textInputAmount)) {
            String name = Objects.requireNonNull(textInputName.getEditText()).getText().toString();
            String amount = Objects.requireNonNull(textInputAmount.getEditText()).getText().toString();
            int userID = ug.getLoggedInUserID();

            // Adds all the user's info into the database using the gateway
            eg.saveToDatabase(new Expense(userID, name, Double.parseDouble(amount)), userID);
            Intent intent = new Intent(this, ExpensesActivity.class);
            startActivity(intent);
        }
    }
}

