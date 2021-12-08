/* Plannr by Generic Name
 *
 * This file contains methods for activity_school.xml.
 */
package com.generic.plannr;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.GetEventsOfDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.generic.plannr.CalendarUtil.daysInMonthArray;
import static com.generic.plannr.CalendarUtil.monthYearFromDate;

public class SchoolActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView rvCalendar;
    private DrawerLayout drawerLayout;
    private MainActivity activity;
    private ArrayList<SchoolEvent> eventsList;
    private ListEvents.RecyclerViewClickLister listener;
    private TextView dialogEventName, dialogEventCourse, dialogEventStartD, dialogEventStartT,
            dialogEventEndD, dialogEventEndT, dialogEventPriority, dialogEventLocation;
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
        setOnClickListener();
        ListEvents adapter = new ListEvents(eventsList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvEvents.setLayoutManager(layoutManager);
        rvEvents.setItemAnimator(new DefaultItemAnimator());
        rvEvents.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
            View dialogView;
            String startDate = eventsList.get(position).getStartDate().toLocalDate().format(dayFormatter);
            String startTime = eventsList.get(position).getStartDate().toLocalTime().format(timeFormatter);

            switch (eventsList.get(position).getEventType()) {
                case "Assessment":
                    dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.popup_view_event, null);
                    dialogEventName = dialogView.findViewById(R.id.tv_event_name_pop);
                    dialogEventCourse = dialogView.findViewById(R.id.tv_course);
                    dialogEventStartD = dialogView.findViewById(R.id.tv_start_date);
                    dialogEventStartT = dialogView.findViewById(R.id.tv_start_time);
                    dialogEventEndD = dialogView.findViewById(R.id.tv_end_date);
                    dialogEventEndT = dialogView.findViewById(R.id.tv_end_time);
                    dialogEventPriority = dialogView.findViewById(R.id.tv_priority);
                    dialogEventStartD.setText(startDate);
                    dialogEventStartT.setText(startTime);
                    break;
                case "Deadline":
                    dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.popup_deadline_event, null);
                    dialogEventName = dialogView.findViewById(R.id.tv_event_name_pop_d);
                    dialogEventCourse = dialogView.findViewById(R.id.tv_course_d);
                    dialogEventEndD = dialogView.findViewById(R.id.tv_end_date_d);
                    dialogEventEndT = dialogView.findViewById(R.id.tv_end_time_d);
                    dialogEventPriority = dialogView.findViewById(R.id.tv_priority_d);
                    break;
                default:
                    dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.popup_location_event, null);

                    dialogEventName = dialogView.findViewById(R.id.tv_event_name_pop_l);
                    dialogEventCourse = dialogView.findViewById(R.id.tv_course_l);
                    dialogEventStartD = dialogView.findViewById(R.id.tv_start_date_l);
                    dialogEventStartT = dialogView.findViewById(R.id.tv_start_time_l);
                    dialogEventEndD = dialogView.findViewById(R.id.tv_end_date_l);
                    dialogEventEndT = dialogView.findViewById(R.id.tv_end_time_l);
                    dialogEventPriority = dialogView.findViewById(R.id.tv_priority_l);
                    dialogEventLocation = dialogView.findViewById(R.id.tv_location);
                    dialogEventLocation.setText(eventsList.get(position).getLocation());
                    dialogEventStartD.setText(startDate);
                    dialogEventStartT.setText(startTime);
            }

            String endDate = eventsList.get(position).getEndDate().toLocalDate().format(dayFormatter);
            String endTime = eventsList.get(position).getEndDate().toLocalTime().format(timeFormatter);
            int priority = eventsList.get(position).getPriority();
            String priorityS = "";
            switch (priority) {
                case 0:
                    priorityS = "High";
                    break;
                case 1:
                    priorityS = "Medium";
                    break;
                case 2:
                    priorityS = "Low";
                    break;
            }

            dialogEventName.setText(eventsList.get(position).getName());
            dialogEventCourse.setText(eventsList.get(position).getCourse());
            dialogEventEndD.setText(endDate);
            dialogEventEndT.setText(endTime);
            dialogEventPriority.setText(priorityS);

            builder.setView(dialogView);
            builder.setCancelable(true);
            builder.show();
        };
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
        activity.redirectActivity(this, AddAssessmentEventActivity.class);
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
