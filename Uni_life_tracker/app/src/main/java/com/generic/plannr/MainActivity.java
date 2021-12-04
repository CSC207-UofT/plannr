package com.generic.plannr;

import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.generic.plannr.Entities.Event;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
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

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    // initialize variable
    DrawerLayout drawerLayout;
    ToggleButton tbSort;
    private ArrayList<Event> eventsList;
    private RecyclerView rvEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets the Welcome Name message to the user's name
        TextView tv1 = findViewById(R.id.tv_welcome_name);
        UserInfoDatabaseHelper user = createDatabase();
        tv1.setText(user.getLoggedInName());

        // show today's date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView tvViewDate = findViewById(R.id.tv_date);
        tvViewDate.setText(currentDate);

        // side menu
        drawerLayout = findViewById(R.id.drawer_layout); // side menu

        // events list
        rvEvents = findViewById(R.id.rv_events);
        eventsList = new ArrayList<>(); 
        setEventInfo();
        setAdapter();

//        TODO: enable after
//        tbSort = findViewById(R.id.tb_sort);
//        tbSort.setOnCheckedChangeListener(this);
    }

    /**
     * Creates an userinfo database and opens it.
     *
     * @return user an instance of userinfo database
     */
    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(MainActivity.this);
        user.openDatabase();
        return user;
    }

    /**
     * Sets adapter to display user's event list.
     */
    private void setAdapter() {
        ListEvents adapter = new ListEvents(eventsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvEvents.setLayoutManager(layoutManager);
        rvEvents.setItemAnimator(new DefaultItemAnimator());
        rvEvents.setAdapter(adapter);
    }

    /**
     * TODO: remove later
     * Generates events to display.
     */
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

    /**
     * Opens drawer layout (navigation menu) to view.
     *
     * @param drawerLayout a DrawerLayout for the navigation menu.
     */
    public void openDrawer(DrawerLayout drawerLayout) { drawerLayout.openDrawer(GravityCompat.START); } // open drawer layout

    /**
     * Closes drawer layout (navigation menu) from view.
     *
     * @param drawerLayout a DrawerLayout for the navigation menu.
     */
    public void closeDrawer(DrawerLayout drawerLayout) {
        // close drawer layout

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // when drawer is open, close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout); // close drawer
    }


     @Override
     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
     if (isChecked)
     Toast.makeText(this, "Sorted by priority!", Toast.LENGTH_SHORT).show();
     else
     Toast.makeText(this, "Sorted by time!", Toast.LENGTH_SHORT).show();
     }

     @Override
     public void onBackPressed() {
     moveTaskToBack(true);
     }

     /**
      * Directs current activity to a different activity.
      * Generates an intent and starts the activity.
      *
      * @param activity a user's current activity.
     * @param aClass a Class of the new activity to be started.
     */
    public void redirectActivity(Activity activity, @SuppressWarnings("rawtypes") Class aClass) {
        // initialize intent
        Intent intent = new Intent(activity,aClass);
        // set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // start activity
        activity.startActivity(intent);
        finish();
    }

    /**
     * Prompts dialog for user to log out.
     * If user selects yes, direct current activity to Log In activity.
     *
     * @param activity a user's current activity.
     */
    public void logout (Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", (dialog, which) -> redirectActivity(activity, LoginActivity.class));
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    /**
     * Opens navigation menu on menu icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickMenu(View view){
        // open drawer
        openDrawer(drawerLayout);
    }

    /**
     * Opens navigation menu on logo click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogo(View view) { closeDrawer(drawerLayout); } // close drawer

    /**
     * Directs activity to the School activity on school icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSchool(View view) { redirectActivity(this, SchoolActivity.class); }

    /**
     * TODO: direct to life later!
     * Directs activity to the Life activity on life icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickLife(View view) { redirectActivity(this, MainActivity.class); }

    /**
     * Directs activity to the Expenses activity on expenses icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickExpenses(View view) { redirectActivity(this, ExpensesActivity.class); }

    /**
     * Directs activity to the Settings activity on settings icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSettings(View view) { redirectActivity(this, SettingsActivity.class); }

    /**
     * Prompts log out on a logout icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogOut(View view) {
        logout(this);
    }
}