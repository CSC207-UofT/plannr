package com.generic.plannr;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventActivity extends AppCompatActivity {
    MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        BottomNavigationView navEvents = findViewById(R.id.nav_events);
        navEvents.setOnNavigationItemSelectedListener(navListener);
        replaceFragment(new AddAssessmentFragment());
    }

    @SuppressLint("NonConstantResourceId")
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_assessment:
                        selectedFragment = new AddAssessmentFragment();
                        break;
                    case R.id.nav_class:
                        selectedFragment = new AddClassTimeFragment();
                        break;
                    case R.id.nav_deadline:
                        selectedFragment = new AddDeadlineFragment();
                        break;
                    case R.id.nav_study_session:
                        selectedFragment = new AddStudySessionFragment();
                        break;
                }
                replaceFragment(selectedFragment);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
                return true;
            };

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Directs activity to the School activity on back arrow icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickBack(View view) {
        activity.redirectActivity(this, SchoolActivity.class);
    }

    /**
     * Saves event into the database.
     *
     * @param view a View for the device screen.
     */
    public void clickSaveEvent(View view) {
//        if (addEventInput()) {
//            String eventName = etEventName.getText().toString();
//            setDateTime();
//            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);
//
//            Event event = new Event(eventName, priority, start, end);
//            int userID = ug.getLoggedInUserID();
//
//            eg.saveToDatabase(event, userID);
//
//            activity.redirectActivity(this, SchoolActivity.class);
//        }
    }
}