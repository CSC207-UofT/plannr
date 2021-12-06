package com.generic.plannr;

import android.graphics.Color;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.ImageView;
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
import com.google.android.material.textfield.TextInputEditText;
import com.generic.plannr.Gateways.ExpenseGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

public class ExpensesActivity extends AppCompatActivity {

    private ArrayList<Expense> expensesList;
    private RecyclerView  rvExpenses;
    private DrawerLayout drawerLayout;
    private MainActivity activity;
    private TextInputEditText etIncome;
    private TextInputLayout tiIncome;
    ImageView ivAddExpense;
    UserGateway ug = new UserGateway(ExpensesActivity.this);
    ExpenseGateway eg = new ExpenseGateway(ExpensesActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        activity = new MainActivity();
        expensesList = new ArrayList<>();
        rvExpenses = findViewById(R.id.rv_expenses); // expense list
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
        ivAddExpense = findViewById(R.id.iv_add_expense); // add expense button

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart", true);

        if (firstStart) {
            showTargetView();
        }

        setExpenseInfo();
        setAdapter();
    }

    /**
     * Sets up the recycler view for expenses list.
     */
    private void setAdapter() {
        ListExpenses adapter = new ListExpenses(expensesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvExpenses.setLayoutManager(layoutManager);
        rvExpenses.setItemAnimator(new DefaultItemAnimator());
        rvExpenses.setAdapter(adapter);
    }

    /**
     * Displays target view upon first launch. Target view prompts user to add expense.
     */
    private void showTargetView() {
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
                });

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    /**
     * Creates an instance of expense database and an instance of userinfo database
     * and adds all expenses to expense list
     */
    private void setExpenseInfo() {
        expensesList.addAll(eg.getAllExpenses(ug.getLoggedInUserID()));
    }

    /**
     * Calculates the total expenses
     *
     */
    public void calculateExpense(){
        // TODO: CALCULATE TOTAL EXPENSES
        String income = Objects.requireNonNull(tiIncome.getEditText()).getText().toString();
        //double total_sum = Double.parseDouble(income) - the sum of expense values
    }

    public void clickAddExpense(View view) {
        // clicking the check in order to add expense
        Intent intent = new Intent(this, AddExpensesActivity.class);
        startActivity(intent);
    }

    /**
     * Checks if the income is greater or less than the total
     * and changes colour of the text accordingly
     *
     */
    public void clickSaveIncome(View view) {
        // TODO: TO BE PLACED WITH THE INCOME AND EXPENSES FROM THE DATABASE
        TextView total = findViewById(R.id.tv_total);
        String income = Objects.requireNonNull(tiIncome.getEditText()).getText().toString();
        // FILLER
        double total_expenses = 100.0;
        if (Double.parseDouble(income) > total_expenses) {
            total.setTextColor(Color.GREEN);

        }else if (Double.parseDouble(income) < total_expenses){
            total.setTextColor(Color.RED);

        }else{total.setTextColor(Color.YELLOW);}
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