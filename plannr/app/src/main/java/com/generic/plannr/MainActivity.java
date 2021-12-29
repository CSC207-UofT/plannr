/* Plannr by Generic Name
 *
 * This file contains methods for activity_main.xml.
 */
package com.generic.plannr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.EventList;
import com.generic.plannr.UseCases.EventLoader;
import com.generic.plannr.UseCases.GetEventsOfDate;
import com.generic.plannr.UseCases.SortEvents;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DrawerLayout drawerLayout;
    UserGateway ug = new UserGateway(MainActivity.this);
    EventGateway eg = new EventGateway(MainActivity.this);
    Dialog dialog;
    private ArrayList<SchoolEvent> eventsList;
    private RecyclerView rvEvents;
    private ListEvents.RecyclerViewClickLister listener;
    private String startDate, startTime, endDate, endTime;
    private TextView dialogEventName, dialogEventCourse, dialogEventPriority, dialogEventLocation;

    EventList events = new EventList();
    EventLoader el = new EventLoader(eg, events);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvViewDate = findViewById(R.id.tv_date); // Date
        TextView tvWelcome = findViewById(R.id.tv_welcome_name); // Welcome name
        Spinner spnSort = findViewById(R.id.spn_sort);
        drawerLayout = findViewById(R.id.drawer_layout); // Side menu
        rvEvents = findViewById(R.id.rv_events); // Events list
        dialog = new Dialog(this);

        // Sets the welcome name message to the user's name
        String welcome = "Welcome " + ug.getLoggedInName() + "!";
        tvWelcome.setText(welcome);

        // Show today's date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tvViewDate.setText(currentDate);

        // Side menu
        drawerLayout = findViewById(R.id.drawer_layout); // side menu

        // Shows events list
        eventsList = new ArrayList<>();
        setEventInfo();
        setAdapter();

        // Sort dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_by,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSort.setAdapter(adapter);
        spnSort.setOnItemSelectedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout); // close drawer
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sortType = parent.getItemAtPosition(position).toString(); // get sort type
        if (sortType.matches("Priority")) {
            SortEvents.sortByPriority(eventsList);
        } else {
            SortEvents.sortByDate(eventsList);
        }
        setAdapter();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
        el.loadEvents(userID);
        eventsList.addAll(GetEventsOfDate.getEventsOfDate(events, LocalDate.now()));
    }

    /**
     * Opens drawer layout (navigation menu) to view.
     *
     * @param drawerLayout A DrawerLayout for the navigation menu.
     */
    public void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    /**
     * Closes drawer layout (navigation menu) from view, if the drawer is open.
     *
     * @param drawerLayout A DrawerLayout for the navigation menu.
     */
    public void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Directs current activity to a different activity.
     * Generates an intent and starts the activity.
     *
     * @param activity A user's current activity.
     * @param aClass   A Class of the new activity to be started.
     */
    public void redirectActivity(Activity activity, @SuppressWarnings("rawtypes") Class aClass) {
        Intent intent = new Intent(activity, aClass); // Initialize intent
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Set flag
        activity.startActivity(intent); // Start activity
        finish();
    }

    /**
     * Prompts dialog for user to log out.
     * If user selects yes, direct current activity to Log In activity.
     *
     * @param activity a user's current activity.
     */
    public void logout(Activity activity) {
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
    public void clickMenu(View view) {
        openDrawer(drawerLayout);
    }

    /**
     * Directs activity to the Main activity on logo click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    /**
     * Directs activity to the School activity on school icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSchool(View view) {
        redirectActivity(this, SchoolActivity.class);
    }

    /**
     * Directs activity to the Expenses activity on expenses icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickExpenses(View view) {
        redirectActivity(this, ExpensesActivity.class);
    }

    /**
     * Directs activity to the Settings activity on settings icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSettings(View view) {
        redirectActivity(this, SettingsActivity.class);
    }

    /**
     * Prompts log out on a logout icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogOut(View view) {
        logout(this);
    }
}