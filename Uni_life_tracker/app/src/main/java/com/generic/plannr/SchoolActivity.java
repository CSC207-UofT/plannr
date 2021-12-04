package com.generic.plannr;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Database.EventDatabaseHelper;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.GetEventsOfDate;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.generic.plannr.CalendarUtil.daysInMonthArray;
import static com.generic.plannr.CalendarUtil.monthYearFromDate;

public class SchoolActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView rvCalendar;
    private DrawerLayout drawerLayout;
    private MainPageActivity activity;
    private ArrayList<Event> eventsList;
    private RecyclerView rvEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        initWidgets();
        CalendarUtil.selectedDate = LocalDate.now();
        setMonthView();
        drawerLayout = findViewById(R.id.drawer_layout);
        activity = new MainPageActivity();

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

    private void setEventInfo() {
        EventDatabaseHelper event = new EventDatabaseHelper(SchoolActivity.this);
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(SchoolActivity.this);
        user.openDatabase();
        event.openDatabase();
        String email = user.getLoggedInEmail();


        eventsList.addAll(GetEventsOfDate.getEventsOfDate(event.getAllEvents(email), CalendarUtil.selectedDate));
    }

    private void initWidgets()
    {
        rvCalendar = findViewById(R.id.rv_calendar);
        monthYearText = findViewById(R.id.tv_month_year);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtil.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        rvCalendar.setLayoutManager(layoutManager);
        rvCalendar.setAdapter(calendarAdapter);
    }


    public void previousMonthAction(View view)
    {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
        setMonthView();
    }

    public void clickBack(View view) {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

    public void clickAddEvent(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(CalendarUtil.selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    public void clickMenu(View view) { activity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { activity.redirectActivity(this, MainPageActivity.class); } // redirect activity to main

    public void clickSchool(View view) { recreate(); } // recreate activity

    // TODO: change this to life later
    public void clickLife(View view) { activity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { activity.redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { activity.redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    public void clickLogOut(View view) {
        MainPageActivity activity = new MainPageActivity();
        activity.logout(this); } // prompt logout

    @Override
    protected void onPause() {
        super.onPause();
        activity.closeDrawer(drawerLayout); // close drawer
    }
}
