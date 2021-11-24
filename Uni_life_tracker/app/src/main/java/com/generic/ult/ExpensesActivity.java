package com.generic.ult;

import Entities.Expense;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    private ArrayList<Expense> expensesArrayList;
    private RecyclerView  rvExpenses;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        // expense list
        rvExpenses = findViewById(R.id.rv_expenses);
        expensesArrayList = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu

        setExpenseInfo();
        setAdapter();
    }

    private void setAdapter() {
        ListExpenses adapter = new ListExpenses(expensesArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvExpenses.setLayoutManager(layoutManager);
        rvExpenses.setItemAnimator(new DefaultItemAnimator());
        rvExpenses.setAdapter(adapter);
    }

//    TODO: generates expenses to display FOR NOW
    private void setExpenseInfo() {
        expensesArrayList.add(new Expense("Rent", 1500.50));
        expensesArrayList.add(new Expense("Food", 134.23));
        expensesArrayList.add(new Expense("Dinner", 54.67));
        expensesArrayList.add(new Expense("Clothing", 45.96));

    }

    public void clickAddExpense(View view) {
        Intent intent = new Intent(this, AddExpensesActivity.class);
        startActivity(intent);
    }

    public void clickMenu(View view){ MainPageActivity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { MainPageActivity.closeDrawer(drawerLayout); } // close drawer

    public void clickSchool(View view) { MainPageActivity.redirectActivity(this, SchoolMain.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { MainPageActivity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { recreate(); } // recreate activity

    public void clickSettings(View view) { MainPageActivity.redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    @Override
    protected void onPause() {
        super.onPause();
        MainPageActivity.closeDrawer(drawerLayout); // close drawer
    }
}