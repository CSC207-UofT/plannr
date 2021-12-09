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

public class SchoolActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
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

    EventList events = new EventList();
    EventLoader el = new EventLoader(eg, events);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        initWidgets();
        CalendarUtil.selectedDate = LocalDate.now();
        setMonthView();
        drawerLayout = findViewById(R.id.drawer_layout);
        activity = new MainActivity();

        rvEvents = findViewById(R.id.rv_events);
        eventsList = new ArrayList<>();
        setEventInfo();
        setAdapter();
    }

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

    private void setEventInfo() {

        int userID = ug.getLoggedInUserID();

        eventsList.addAll(GetEventsOfDate.getEventsOfDate(el.loadEvents(userID), CalendarUtil.selectedDate));
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


    public void clickPreviousMonth(View view)
    {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void clickNextMonth(View view)
    {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
        setMonthView();
    }

    public void clickAddEvent(View view) {
        activity.redirectActivity(this, AddAssessmentEventActivity.class);
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        eventsList.clear();

        if (date != null) {
            CalendarUtil.selectedDate = date;
            setEventInfo();
            setAdapter();
            setMonthView();
        }
    }

    public void clickMenu(View view) { activity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { activity.redirectActivity(this, MainActivity.class); } // redirect activity to main

    public void clickSchool(View view) {} // recreate activity

    // TODO: change this to life later
//    public void clickLife(View view) { activity.redirectActivity(this, MainActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { activity.redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { activity.redirectActivity(this, SettingsActivity.class); } // redirect activity to settings

    public void clickLogOut(View view) {
        activity.logout(this);
    } // prompt logout

    @Override
    protected void onPause() {
        super.onPause();
        activity.closeDrawer(drawerLayout); // close drawer
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
