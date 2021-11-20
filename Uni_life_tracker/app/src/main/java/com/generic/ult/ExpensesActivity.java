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
    private RecyclerView  recyclerView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        // expense list
        recyclerView = findViewById(R.id.rv_expenses);
        expensesArrayList = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout);

        setExpenseInfo();
        setAdapter();

    }

    private void setAdapter() {
        ListExpenses adapter = new ListExpenses(expensesArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setExpenseInfo() {
        expensesArrayList.add(new Expense("Rent", 1500.50));
        expensesArrayList.add(new Expense("Food", 134.23));
        expensesArrayList.add(new Expense("Dinner", 54.67));
        expensesArrayList.add(new Expense("Clothing", 45.96));

    }

    public void ClickBack(View view) {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

    public void ClickAddExpense(View view) {
        Intent intent = new Intent(this, AddExpensesActivity.class);
        startActivity(intent);
    }

    public void ClickMenu(View view){
        // open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        // open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        // close drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        // close drawer layout

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // when drawer is open, close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}