package com.generic.plannr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Database.EventDatabaseHelper;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.GetEventsOfDate;

import java.text.DateFormat;
import java.time.LocalDate;
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
        EventDatabaseHelper event = new EventDatabaseHelper(MainPageActivity.this);
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(MainPageActivity.this);
        user.openDatabase();
        event.openDatabase();
        String email = user.getLoggedInEmail();
        eventsList.addAll(GetEventsOfDate.getEventsOfDate(event.getAllEvents(email), LocalDate.now()));
    }

    public void clickMenu(View view){
        // open drawer
        openDrawer(drawerLayout);
    }

    public void openDrawer(DrawerLayout drawerLayout) { drawerLayout.openDrawer(GravityCompat.START); } // open drawer layout

    public void clickLogo(View view) { closeDrawer(drawerLayout); } // close drawer

    public void closeDrawer(DrawerLayout drawerLayout) {
        // close drawer layout

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // when drawer is open, close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickSchool(View view) { redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) {
        //redirectActivity(this, MainPageActivity.class);
       } // redirect activity to life

    public void clickExpenses(View view) { redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    public void clickLogOut(View view) { logout(this); } // redirect activity to settings

    public void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", (dialog, which) -> redirectActivity(activity, LoginActivity.class));

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public void redirectActivity(Activity activity, @SuppressWarnings("rawtypes") Class aClass) {
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