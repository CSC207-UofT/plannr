package com.generic.plannr;

import android.widget.ImageView;
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
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    private ArrayList<Expense> expensesList;
    private RecyclerView  rvExpenses;
    private DrawerLayout drawerLayout;
    private MainActivity activity;
    ImageView ivAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        activity = new MainActivity();
        expensesList = new ArrayList<>();
        rvExpenses = findViewById(R.id.rv_expenses); // expense list
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
        ivAddExpense = findViewById(R.id.iv_add_expense); // add expense button

        TapTargetView.showFor(this, TapTarget.forView(
                findViewById(R.id.iv_add_expense), "Add an Expense", "Add your expenses here!")
                .outerCircleColor(R.color.lavender).targetCircleColor(R.color.white)
                .titleTextColor(R.color.black).descriptionTextColor(R.color.black)
                .tintTarget(false)
                .cancelable(true),
                new TapTargetView.Listener() {
                    @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);
                                clickAddExpense(view);
                    }
                }
        );
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

    public void clickAddExpense(View view) {
        // clicking the check in order to add expense
        Intent intent = new Intent(this, AddExpensesActivity.class);
        startActivity(intent);
    }

    public void clickMenu(View view){ activity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { activity.redirectActivity(this, MainActivity.class);} // redirect activity to main

    public void clickSchool(View view) { activity.redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
//    public void clickLife(View view) { activity.redirectActivity(this, MainActivity.class); } // redirect activity to life

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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}