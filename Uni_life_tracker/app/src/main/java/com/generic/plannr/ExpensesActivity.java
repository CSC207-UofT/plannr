package com.generic.plannr;

import android.graphics.Color;
import android.widget.TextView;
import com.generic.plannr.Database.ExpenseDatabaseHelper;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.generic.plannr.Entities.Expense;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

public class ExpensesActivity extends AppCompatActivity {

    private ArrayList<Expense> expensesList;
    private RecyclerView  rvExpenses;
    private DrawerLayout drawerLayout;
    private MainPageActivity activity;
    private TextInputLayout textInputIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        // expense list
        rvExpenses = findViewById(R.id.rv_expenses);
        expensesList = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
        activity = new MainPageActivity();

        setExpenseInfo();
        setAdapter();
    }
    /**
     * Sets up the recycler view  for expenses list
     */
    private void setAdapter() {
        ListExpenses adapter = new ListExpenses(expensesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvExpenses.setLayoutManager(layoutManager);
        rvExpenses.setItemAnimator(new DefaultItemAnimator());
        rvExpenses.setAdapter(adapter);
    }
    /**
     * Creates an instance of expense database and an instance of userinfo database
     * and adds all expenses to expense list
     */
    private void setExpenseInfo() {
        ExpenseDatabaseHelper expense = createExpenseDatabase();
        UserInfoDatabaseHelper user = createDatabase();
        expensesList.addAll(expense.getAllExpenses(user.getLoggedInEmail()));

    }
    /**
     * Creates an expense database and opens it
     * @return expense an instance of expense database
     */
    public ExpenseDatabaseHelper createExpenseDatabase() {
        // creates an instance and opens database
        ExpenseDatabaseHelper expense = new ExpenseDatabaseHelper(ExpensesActivity.this);
        expense.openDatabase();
        return expense;
    }

    /**
     * Creates a userinfo database and opens it
     * @return user an instance of userinfo database
     */
    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(ExpensesActivity.this);
        user.openDatabase();
        return user;
    }


    public void calculateExpense(){
        // TODO: CALCULATE TOTAL EXPENSES
        UserInfoDatabaseHelper user = createDatabase();
        ExpenseDatabaseHelper expense = createExpenseDatabase();
        String income = Objects.requireNonNull(textInputIncome.getEditText()).getText().toString();

        //double total_sum = Double.parseDouble(income) - the sum of expense values
    }


    public void clickAddExpense(View view) {
        // clicking the check in order to add expense
        Intent intent = new Intent(this, AddExpensesActivity.class);
        startActivity(intent);

    }

    public void clickSaveIncome(View view) {
        // TODO: TO BE PLACED WITH THE INCOME AND EXPENSES FROM THE DATABASE
        TextView total = findViewById(R.id.tv_total);
        String income = Objects.requireNonNull(textInputIncome.getEditText()).getText().toString();
        // FILLER
        double total_expenses = 100.0;
        if (Double.parseDouble(income) > total_expenses) {
            total.setTextColor(Color.GREEN);

        }else if (Double.parseDouble(income) < total_expenses){
            total.setTextColor(Color.RED);

        }else{total.setTextColor(Color.YELLOW);}


    }

    public void clickMenu(View view){ activity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { activity.redirectActivity(this, MainPageActivity.class);} // redirect activity to main

    public void clickSchool(View view) { activity.redirectActivity(this, SchoolActivity.class); } // redirect activity to school


    public void clickExpenses(View view) { recreate(); } // recreate activity

    public void clickSettings(View view) { activity.redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    public void clickLogOut(View view) {
        activity.logout(this);
    } // prompt logout

    @Override
    protected void onPause() {
        super.onPause();
        activity.closeDrawer(drawerLayout); // close drawer
    }
}