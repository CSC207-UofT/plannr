/* Plannr by Generic Name
 *
 * This file contains methods for activity_main.xml.
 */
package com.generic.plannr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.GetEventsOfDate;
import com.generic.plannr.UseCases.SortEvents;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    // initialize variable
    DrawerLayout drawerLayout;
    private ArrayList<Event> eventsList;
    private RecyclerView rvEvents;
    private Button testing;
    private ListEvents.RecyclerViewClickLister listener;
    UserGateway ug = new UserGateway(MainActivity.this);
    EventGateway eg = new EventGateway(MainActivity.this);
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvViewDate = findViewById(R.id.tv_date); // Date
        TextView tvWelcome = findViewById(R.id.tv_welcome_name); // Welcome name
        Spinner spnSort = findViewById(R.id.spn_sort);
        drawerLayout = findViewById(R.id.drawer_layout); // Side menu
        rvEvents = findViewById(R.id.rv_events); // Events list
        testing = findViewById(R.id.testing);
        dialog = new Dialog(this);


        // sets the Welcome Name message to the user's name
        String welcome = "Welcome " + ug.getLoggedInName() + "!";
        tvWelcome.setText(welcome);

        // Show today's date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tvViewDate.setText(currentDate);

        // side menu
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

    private void setOnClickListener() {
        listener = (v, position) -> {
            viewEvent(v);
            testing.setText(eventsList.get(position).getName());
        };
    }

    /**
     * TODO: remove later
     * Sets the events to be displayed.
     */
    private void setEventInfo() {
        int userID = ug.getLoggedInUserID();
        eventsList.addAll(GetEventsOfDate.getEventsOfDate(eg.getAllEvents(userID), LocalDate.now()));
    }

    /**
     * Opens drawer layout (navigation menu) to view.
     *
     * @param drawerLayout  A DrawerLayout for the navigation menu.
     */
    public void openDrawer(DrawerLayout drawerLayout) { drawerLayout.openDrawer(GravityCompat.START); }

    /**
     * Closes drawer layout (navigation menu) from view, if the drawer is open.
     *
     * @param drawerLayout  A DrawerLayout for the navigation menu.
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
     * @param activity  A user's current activity.
     * @param aClass    A Class of the new activity to be started.
     */
    public void redirectActivity(Activity activity, @SuppressWarnings("rawtypes") Class aClass) {
        Intent intent = new Intent(activity,aClass); // Initialize intent
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
     * @param view  a View for the device screen.
     */
    public void clickMenu(View view){
        openDrawer(drawerLayout);
    }

    /**
     * Directs activity to the Main activity on logo click.
     *
     * @param view  a View for the device screen.
     */
    public void clickLogo(View view) { closeDrawer(drawerLayout); }

    /**
     * Directs activity to the School activity on school icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickSchool(View view) { redirectActivity(this, SchoolActivity.class); }

//    /**
//     * Directs activity to the Life activity on life icon click.
//     *
//     * @param view  a View for the device screen.
//     */
//    public void clickLife(View view) { redirectActivity(this, MainActivity.class); }

    /**
     * Directs activity to the Expenses activity on expenses icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickExpenses(View view) { redirectActivity(this, ExpensesActivity.class); }

    /**
     * Directs activity to the Settings activity on settings icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickSettings(View view) { redirectActivity(this, SettingsActivity.class); }

    /**
     * Prompts log out on a logout icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickLogOut(View view) {
        logout(this);
    }

    public void viewEvent(View view) {
        dialog.setContentView(R.layout.popup_view_event);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}