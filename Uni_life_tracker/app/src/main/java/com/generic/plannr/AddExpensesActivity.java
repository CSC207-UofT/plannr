package com.generic.plannr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Database.ExpenseDatabaseHelper;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.generic.plannr.Entities.Expense;
import com.google.android.material.textfield.TextInputLayout;


import java.util.Objects;


public class AddExpensesActivity extends AppCompatActivity {

    private TextInputLayout textInputName, textInputAmount;


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
     * Creates an expense database and opens it
     * @return expense an instance of expense database
     */
    public ExpenseDatabaseHelper createExpenseDatabase() {
        // creates an instance and opens database
        ExpenseDatabaseHelper expense = new ExpenseDatabaseHelper(AddExpensesActivity.this);
        expense.openDatabase();
        return expense;
    }

    /**
     * Creates a userinfo database and opens it
     * @return user an instance of userinfo database
     */
    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(AddExpensesActivity.this);
        user.openDatabase();
        return user;
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
            ExpenseDatabaseHelper expense = createExpenseDatabase();
            UserInfoDatabaseHelper user = createDatabase();
            // Adds all the user's info into the database
            expense.insertExpense(new Expense(name, Double.parseDouble(amount)), user.getLoggedInEmail());

            openAddExpensesView();

        }
    }

        public void ClickBack (View view){
        // clicking the arrow back button
            Intent intent = new Intent(this, ExpensesActivity.class);
            startActivity(intent);
        }
    }

