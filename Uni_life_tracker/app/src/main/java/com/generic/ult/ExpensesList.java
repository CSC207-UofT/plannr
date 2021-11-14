package com.generic.ult;

import Entities.Expenses;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ExpensesList extends AppCompatActivity {
    // initialize variable
    DrawerLayout drawerLayout;
    private ArrayList<Expenses> expensesArrayList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);
        // show today's date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.expense_month);
        textViewDate.setText(currentDate);


        // events list
        recyclerView = findViewById(R.id.expense);
        expensesArrayList= new ArrayList<>();
        setExpenseInfo();
        setAdapter();

    }


    private void setAdapter() {
        ExpensesRecyclerView adapter = new ExpensesRecyclerView(expensesArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setExpenseInfo() {
        expensesArrayList.add(new Expenses("Rent", 1500.50));
        expensesArrayList.add(new Expenses("Food", 134.23));
        expensesArrayList.add(new Expenses("Dinner", 54.67));
        expensesArrayList.add(new Expenses("Clothing", 45.96));

    }


}