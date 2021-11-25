package com.generic.ult;

import Entities.Event;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class MainPageActivity extends AppCompatActivity {
    // initialize variable
    DrawerLayout drawerLayout;

    private ArrayList<Event> eventsList;
    private RecyclerView rvEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // show today's date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView tvViewDate = findViewById(R.id.tv_date);
        tvViewDate.setText(currentDate);

        // side menu
        drawerLayout = findViewById(R.id.drawer_layout);
        
        // events list
        rvEvents = findViewById(R.id.rv_events);
        eventsList = new ArrayList<>(); 
        setEventInfo();
        setAdapter();
    }

    private void setAdapter() {
        ListEvents adapter = new ListEvents(eventsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvEvents.setLayoutManager(layoutManager);
        rvEvents.setItemAnimator(new DefaultItemAnimator());
        rvEvents.setAdapter(adapter);
    }

//    TODO: generates events to display FOR NOW
    private void setEventInfo() {
        eventsList.add(new Event("Event 1", 1, LocalDateTime.of(2019, 3, 28, 14, 33, 48)));
        eventsList.add(new Event("Assignment 2", 2, LocalDateTime.of(2021, 12, 13, 12, 20, 48)));
        eventsList.add(new Event("Project Phase 2", 0, LocalDateTime.of(2021, 12, 19, 12, 20, 48)));
        eventsList.add(new Event("Exercise 100", 2, LocalDateTime.of(2021, 11, 19, 12, 20, 48)));
        eventsList.add(new Event("Quiz 34", 2, LocalDateTime.of(2021, 11, 20, 12, 20, 48)));
        eventsList.add(new Event("Test 2", 2, LocalDateTime.of(2021, 11, 16, 12, 20, 48)));
        eventsList.add(new Event("Quiz 3", 2, LocalDateTime.of(2021, 11, 29, 12, 20, 48)));
        eventsList.add(new Event("Project 432", 2, LocalDateTime.of(2021, 11, 13, 12, 20, 48)));
    }
//    public Event(String name, int priority, LocalDateTime startDate, LocalDateTime endDate) {
    public void ClickMenu(View view){
        // open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) { drawerLayout.openDrawer(GravityCompat.START); } // open drawer layout

    public void clickLogo(View view) { closeDrawer(drawerLayout); } // close drawer

    public static void closeDrawer(DrawerLayout drawerLayout) {
        // close drawer layout

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // when drawer is open, close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickSchool(View view) { redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    public static void redirectActivity(Activity activity, Class aClass) {
        // initialize intent
        Intent intent = new Intent(activity,aClass);
        // set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout); // close drawer
    }
}