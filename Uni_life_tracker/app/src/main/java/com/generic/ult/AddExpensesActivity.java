package com.generic.ult;

import Database.ExpenseDatabaseHelper;
import Database.UserInfoDatabaseHelper;
import Entities.Expense;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class AddExpensesActivity extends AppCompatActivity {

   // private AppBarConfiguration appBarConfiguration;

    private TextInputLayout textInputName, textInputAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        textInputName = findViewById(R.id.add_expense_name);
        textInputAmount = findViewById(R.id.add_expense_amount);

                String name = Objects.requireNonNull(textInputName.getEditText()).getText().toString();
                String amount = Objects.requireNonNull(textInputAmount.getEditText()).getText().toString();



                ExpenseDatabaseHelper expense = createExpenseDatabase();
                UserInfoDatabaseHelper user = createDatabase();
                // Adds all the user's info into the database
                expense.insertExpense(new Expense(name, Double.parseDouble(amount)), user.getLoggedInEmail());

                openAddExpensesView();
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

    public ExpenseDatabaseHelper createExpenseDatabase() {
        // creates an instance and opens database
        ExpenseDatabaseHelper expense = new ExpenseDatabaseHelper(AddExpensesActivity.this);
        expense.openDatabase();
        return expense;
    }


    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(AddExpensesActivity.this);
        user.openDatabase();
        return user;
    }

    public void AddExpensesInput(View v) {
        if (!(validate(textInputName) & validate(textInputAmount))) {
            // Here we can get all the info we need
            // For example to get the email you can do textInputEmail.getEditTest().getText().toString()
        }else{openAddExpensesView();}
    }

    public void ClickBack(View view) {
        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);
    }
}
