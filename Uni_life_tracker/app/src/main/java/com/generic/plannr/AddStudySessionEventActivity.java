/* Plannr by Generic Name
 *
 * This file contains methods for activity_add_study_session_event.xml.
 */
package com.generic.plannr;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Controllers.InputTextValidator;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("ALL")
public class AddStudySessionEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    int yr, mth, day, hr, min, priority;
    String startDate, endDate, startTime, endTime;
    TextView tvStartDate, tvStartTime, tvEndDate, tvEndTime;
    RadioGroup rgPriorities;
    ImageView ivBack, ivSave;
    EditText etEventName, etCourse, etLocation;
    Calendar calendar;
    DateTimeFormatter DATEFORMAT;
    LocalDateTime start;
    private MainActivity activity;
    UserGateway ug = new UserGateway(AddStudySessionEventActivity.this);
    EventGateway eg = new EventGateway(AddStudySessionEventActivity.this);

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_study_session_event);
        activity = new MainActivity();
        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);
        etEventName = findViewById(R.id.et_event_name);
        etCourse = findViewById(R.id.et_course);
        etLocation = findViewById(R.id.et_location);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartTime = findViewById(R.id.tv_start_time);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvEndTime = findViewById(R.id.tv_end_time);
        rgPriorities = findViewById(R.id.rg_priorities);

//        Event navigation bar
        BottomNavigationView navEvents = findViewById(R.id.nav_events);
        navEvents.setSelectedItemId(R.id.nav_study_session);
        navEvents.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_assessment:
                    startActivity(new Intent(getApplicationContext(), AddAssessmentEventActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.nav_class:
                    startActivity(new Intent(getApplicationContext(), AddClassEventActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.nav_deadline:
                    startActivity(new Intent(getApplicationContext(), AddDeadlineEventActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.nav_study_session:
                    startActivity(new Intent(getApplicationContext(), AddStudySessionEventActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            }
            return false;
        });

        calendar = Calendar.getInstance();
        yr = calendar.get(Calendar.YEAR);
        mth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hr = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

//        Set start date & time to current date & time
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
        tvStartDate.setText(date);
        tvStartTime.setText(time);

        rgPriorities.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) { // Priority selection
        if (checkedId == R.id.rb_high_priority) {
            priority = 0;
        } else if (checkedId == R.id.rb_med_priority) {
            priority = 1;
        } else if (checkedId == R.id.rb_low_priority) {
            priority = 2;
        }
    }

    /**
     * Highlights any TextView text that is in empty or in an incorrect format.
     *
     * @return whether the added event includes all its needed attributes
     */
    public boolean addEventInput() {
        InputTextValidator input = new InputTextValidator();
        setDateTime();

        boolean endTimeAfter = false;
        if (!endTime.isEmpty()) {
            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);
            // Checks if end date is after start date with its date time format
            if (!end.isAfter(start)) {
                // Warns user that the format is incorrect
                Toast.makeText(this, "End date should be before start!", Toast.LENGTH_LONG).show();
                changeTextColor(255);
            } else {
                changeTextColor(0);
                endTimeAfter = true;
            }
        }

        return input.validateAddEvent(etEventName) & input.validateAddEvent(tvEndDate)
                & input.validateAddEvent(tvEndTime) & input.validateAddEvent(etCourse)
                & input.validateAddEvent(etLocation) & endTimeAfter;
    }

    /**
     * Change the text color of the TextView.
     *
     * @param color an int representing the R-value for the RGB color.
     */
    public void changeTextColor(int color) {
        tvEndDate.setTextColor(Color.rgb(color, 0, 0));
        tvEndTime.setTextColor(Color.rgb(color, 0, 0));
    }

    /**
     * Initializes values for start & end date, start & end time, and date format.
     */
    public void setDateTime() {
        startDate = tvStartDate.getText().toString().trim();
        endDate = tvEndDate.getText().toString().trim();
        startTime = tvStartTime.getText().toString().trim();
        endTime = tvEndTime.getText().toString().trim();
        DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        start = LocalDateTime.parse(startDate + " " + startTime, DATEFORMAT);
    }

    /**
     * Prompts user to select a date, and sets selected date in textView.
     * The date can only be from the current date, or after selected start date.
     *
     * @param textView a TextView of the date.
     */
    public void setDate(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddStudySessionEventActivity.this, (view, year, month, dayOfMonth) -> {
            yr = year;
            mth = month;
            day = dayOfMonth;

            @SuppressLint("DefaultLocale") String startDate = String.format("%02d-%02d-%d", day, (mth + 1), yr);
            textView.setText(startDate);
        }, yr, mth, day);

        datePickerDialog.updateDate(yr, mth, day); // displays prev selected date
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); // disable past date selection
        datePickerDialog.show();
    }

    /**
     * Prompts user to select a time, and sets selected time in textView.
     *
     * @param textView a TextView of the time.
     */
    public void setTime(TextView textView) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                AddStudySessionEventActivity.this, (view, hourOfDay, minute) -> {
            hr = hourOfDay;
            min = minute;

            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, hr);
            calendar.set(Calendar.MINUTE, min);
            textView.setText(DateFormat.format("hh:mm aa", calendar));
        }, hr, min, false);
        timePickerDialog.show();
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
        if (addEventInput()) {
            String eventName = etEventName.getText().toString();
            setDateTime();
            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);
            String course = etEventName.getText().toString();
            String location = etLocation.getText().toString();

            int userID = ug.getLoggedInUserID();
            SchoolEvent event = new SchoolEvent(userID, "Study Session", eventName, priority,
                    start, end, course, location);


            eg.saveToDatabase(event, userID);

            activity.redirectActivity(this, SchoolActivity.class);
        }
    }

    /**
     * Opens dialog for user to select a start date.
     *
     * @param view a View for the device screen.
     */
    public void clickStartDate(View view) {
        setDate(tvStartDate);
    }

    /**
     * Opens dialog for user to select an end date.
     *
     * @param view a View for the device screen.
     */
    public void clickEndDate(View view) {
        setDate(tvEndDate);
    }

    /**
     * Opens dialog for user to select an end time.
     *
     * @param view a View for the device screen.
     */
    public void clickStartTime(View view) {
        setTime(tvStartTime);
    }

    /**
     * Opens dialog for user to select an end time.
     *
     * @param view a View for the device screen.
     */
    public void clickEndTime(View view) {
        setTime(tvEndTime);
    }
}