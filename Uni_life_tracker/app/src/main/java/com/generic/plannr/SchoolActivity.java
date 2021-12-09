/* Plannr by Generic Name
 *
 * This file contains methods for activity_school.xml.
 */
package com.generic.plannr;

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
import com.generic.plannr.UseCases.EventList;
import com.generic.plannr.UseCases.EventLoader;
import com.generic.plannr.UseCases.GetEventsOfDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.generic.plannr.CalendarUtil.daysInMonthArray;
import static com.generic.plannr.CalendarUtil.monthYearFromDate;

@SuppressWarnings("ALL")
public class SchoolActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText, dialogEventName, dialogEventCourse, dialogEventPriority, dialogEventLocation;
    private RecyclerView rvCalendar, rvEvents;
    private DrawerLayout drawerLayout;
    private MainActivity activity;
    private ArrayList<SchoolEvent> eventsList;
    private ListEvents.RecyclerViewClickLister listener;
    private String startDate, startTime, endDate, endTime;
    UserGateway ug = new UserGateway(SchoolActivity.this);
    EventGateway eg = new EventGateway(SchoolActivity.this);

    EventList events = new EventList();
    EventLoader el = new EventLoader(eg, events);

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
    public void onItemClick(LocalDate date) {
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

    /**
     * Create the dialog once a particular recyler view is choosen
     */
    private void setOnClickListener() {
        listener = (v, position) -> {
            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
            View dialogView;

            // Gets the information of event based of the event clicked
            startDate = eventsList.get(position).getStartDate().toLocalDate().format(dayFormatter);
            startTime = eventsList.get(position).getStartDate().toLocalTime().format(timeFormatter);
            endDate = eventsList.get(position).getEndDate().toLocalDate().format(dayFormatter);
            endTime = eventsList.get(position).getEndDate().toLocalTime().format(timeFormatter);

            // Gets all the info for the different types of evetns
            switch (eventsList.get(position).getEventType()) {
                case "Assessment":
                    dialogView = setView(v, R.layout.popup_view_event, R.id.tv_event_name_pop, R.id.tv_course,
                            R.id.tv_end_date, R.id.tv_end_time, R.id.tv_priority, R.id.tv_start_date,
                            R.id.tv_start_time);
                    break;
                case "Deadline":
                    dialogView = setView(v, R.layout.popup_deadline_event,  R.id.tv_event_name_pop_d, R.id.tv_course_d,
                            R.id.tv_end_date_d, R.id.tv_end_time_d, R.id.tv_priority_d, 0, 0);
                    break;
                default:
                    dialogView = setView(v, R.layout.popup_location_event,  R.id.tv_event_name_pop_l, R.id.tv_course_l,
                            R.id.tv_end_date_l, R.id.tv_end_time_l, R.id.tv_priority_l, R.id.tv_start_date_l,
                            R.id.tv_start_time_l);
                    dialogEventLocation = dialogView.findViewById(R.id.tv_location);
                    dialogEventLocation.setText(eventsList.get(position).getLocation());
            }
            dialogEventName.setText(eventsList.get(position).getName());
            dialogEventCourse.setText(eventsList.get(position).getCourse());
            dialogEventPriority.setText(setPriority(eventsList.get(position).getPriority()));

            // Sets the popup dialog created and displays it
            builder.setView(dialogView);
            builder.setCancelable(true);
            builder.show();
        };
    }

    /**
     * Sets the View Dialog in order to present the correct dialog with the correct information
     *
     * @param v a View for the device screen.
     * @param popupTypeID the id for the xml activity
     * @param nameID the ID for the event name
     * @param courseID the ID for the course name
     * @param endDateID the ID for the end date
     * @param endTimeID the ID for the end date
     * @param priorityID the ID for the end date
     * @param startDateID the ID for the end date
     * @param startTimeID the ID for the end date
     *
     * @return the view of the dialog
     */
    private View setView(View v, int popupTypeID, int nameID, int courseID, int endDateID, int endTimeID,
                         int priorityID, int startDateID, int startTimeID) {
        View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(popupTypeID, null);
        dialogEventName = dialogView.findViewById(nameID);
        dialogEventCourse = dialogView.findViewById(courseID);
        TextView dialogEventEndD = dialogView.findViewById(endDateID);
        TextView dialogEventEndT = dialogView.findViewById(endTimeID);
        dialogEventPriority = dialogView.findViewById(priorityID);
        dialogEventEndD.setText(endDate);
        dialogEventEndT.setText(endTime);
        if (startDateID != 0) {
            TextView dialogEventStartD = dialogView.findViewById(startDateID);
            TextView dialogEventStartT = dialogView.findViewById(startTimeID);
            dialogEventStartD.setText(startDate);
            dialogEventStartT.setText(startTime);
        }
        return dialogView;
    }
    /**
     * Sets the View Dialog in order to present the correct dialog with the correct information
     *
     * @param priority an in that represent a numerical representation of priority
     * @return a string representation of priority
     */
    private String setPriority(int priority){
        String priorityType = "";
        switch (priority) {
            case 0:
                priorityType = "High";
                break;
            case 1:
                priorityType = "Medium";
                break;
            case 2:
                priorityType = "Low";
                break;
        }
        return priorityType;
    }

    /**
     * Sets the events to be displayed.
     */
    private void setEventInfo() {
        int userID = ug.getLoggedInUserID();
        eventsList.addAll(GetEventsOfDate.getEventsOfDate(el.loadEvents(userID), CalendarUtil.selectedDate));
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
