/* Plannr by Generic Name
 *
 * This file contains methods for activity_school.xml.
 */
package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.GetEventsOfDate;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.generic.plannr.CalendarUtil.daysInMonthArray;
import static com.generic.plannr.CalendarUtil.monthYearFromDate;

public class SchoolActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView rvCalendar;
    private DrawerLayout drawerLayout;
    private MainActivity activity;
    private ArrayList<Event> eventsList;
    private RecyclerView rvEvents;
    UserGateway ug = new UserGateway(SchoolActivity.this);
    EventGateway eg = new EventGateway(SchoolActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        activity = new MainActivity();
        rvCalendar = findViewById(R.id.rv_calendar);
        monthYearText = findViewById(R.id.tv_month_year);
        drawerLayout = findViewById(R.id.drawer_layout);
        rvEvents = findViewById(R.id.rv_events);

        CalendarUtil.selectedDate = LocalDate.now();
        setMonthView();

        eventsList = new ArrayList<>();
        setEventInfo();
        setAdapter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity.closeDrawer(drawerLayout); // close drawer
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        eventsList.clear();

        if (date != null) {
            CalendarUtil.selectedDate = date;
            setEventInfo();
            setAdapter();
            setMonthView();
        }
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
     * Sets the events to be displayed.
     */
    private void setEventInfo() {
        int userID = ug.getLoggedInUserID();
        eventsList.addAll(GetEventsOfDate.getEventsOfDate(eg.getAllEvents(userID), CalendarUtil.selectedDate));
    }

    /**
     * Sets monthly calendar view.
     */
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtil.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        rvCalendar.setLayoutManager(layoutManager);
        rvCalendar.setAdapter(calendarAdapter);
    }

    /**
     * Displays the previous month and sets month view.
     */
    public void clickPreviousMonth(View view) {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
        setMonthView();
    }

    /**
     * Displays the next month and sets month view.
     */
    public void clickNextMonth(View view) {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
        setMonthView();
    }

    /**
     * Directs activity to the Add Event activity on plus icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickAddEvent(View view) {
        activity.redirectActivity(this, AddEventActivity.class);
    }

    /**
     * Opens navigation menu on menu icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickMenu(View view) {
        activity.openDrawer(drawerLayout);
    }

    /**
     * Directs activity to the Main activity on logo click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogo(View view) {
        activity.redirectActivity(this, MainActivity.class);
    }

    /**
     * Directs activity to the School activity on school icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSchool(View view) {
    }

//    /**
//     * Directs activity to the Life activity on life icon click.
//     *
//     * @param view  a View for the device screen.
//     */
//    public void clickLife(View view) { activity.redirectActivity(this, MainActivity.class); } // redirect activity to life

    /**
     * Directs activity to the Expenses activity on expenses icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickExpenses(View view) {
        activity.redirectActivity(this, ExpensesActivity.class);
    }

    /**
     * Directs activity to the Settings activity on settings icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSettings(View view) {
        activity.redirectActivity(this, SettingsActivity.class);
    }

    /**
     * Prompts log out on a logout icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogOut(View view) {
        activity.logout(this);
    }

}
