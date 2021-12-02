package com.generic.plannr;

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
import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    private ArrayList<Expense> expensesList;
    private RecyclerView  rvExpenses;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        // expense list
        rvExpenses = findViewById(R.id.rv_expenses);
        expensesList = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu

        setExpenseInfo();
        setAdapter();
    }

    private void setAdapter() {
        ListExpenses adapter = new ListExpenses(expensesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvExpenses.setLayoutManager(layoutManager);
        rvExpenses.setItemAnimator(new DefaultItemAnimator());
        rvExpenses.setAdapter(adapter);
    }

    private void setExpenseInfo() {
        ExpenseDatabaseHelper expense = createExpenseDatabase();
        UserInfoDatabaseHelper user = createDatabase();
        expensesList.addAll(expense.getAllExpenses(user.getLoggedInEmail()));

    }

    public ExpenseDatabaseHelper createExpenseDatabase() {
        // creates an instance and opens database
        ExpenseDatabaseHelper expense = new ExpenseDatabaseHelper(ExpensesActivity.this);
        expense.openDatabase();
        return expense;
    }

    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(ExpensesActivity.this);
        user.openDatabase();
        return user;
    }

    public void clickAddExpense(View view) {
        Intent intent = new Intent(this, AddExpensesActivity.class);
        startActivity(intent);
    }

    public void clickMenu(View view){ MainPageActivity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { MainPageActivity.redirectActivity(this, MainPageActivity.class);} // redirect activity to main

    public void clickSchool(View view) { MainPageActivity.redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { MainPageActivity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { recreate(); } // recreate activity

    public void clickSettings(View view) { MainPageActivity.redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    public void clickLogOut(View view) { MainPageActivity.logout(this); } // prompt logout

    @Override
    protected void onPause() {
        super.onPause();
        MainPageActivity.closeDrawer(drawerLayout); // close drawer
    }
}