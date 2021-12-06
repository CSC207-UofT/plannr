/* Plannr by Generic Name
 *
 * This file contains methods for activity_add_event.xml.
 */
package com.generic.plannr;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
//    Initialize Variables
    int yr, mth, day, hr, min, priority;
    String startDate, endDate, startTime, endTime;
    TextView tvStartDate, tvStartTime, tvEndDate, tvEndTime, tvAssessment, tvDeadline, tvClassTime, tvStudySession;
    RadioGroup rgPriorities;
    ImageView ivBack, ivSave;
    EditText etEventName, etCourse;
    Calendar calendar;
    DateTimeFormatter DATEFORMAT;
    LocalDateTime start;
    private MainActivity activity;
    UserGateway ug = new UserGateway(AddEventActivity.this);
    EventGateway eg = new EventGateway(AddEventActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        activity = new MainActivity();
        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);
        etEventName = findViewById(R.id.et_event_name);
        etCourse = findViewById(R.id.et_course);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartTime = findViewById(R.id.tv_start_time);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvEndTime = findViewById(R.id.tv_end_time);
        rgPriorities = findViewById(R.id.rg_priorities);
        tvAssessment = findViewById(R.id.tv_assessment);
        tvDeadline = findViewById(R.id.tv_deadline);
        tvClassTime = findViewById(R.id.tv_class_time);
        tvStudySession = findViewById(R.id.tv_study_session);

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
     * @return  whether the added event includes all its needed attributes
     */
    public boolean addEventInput() {
        Validator input = new Validator();
        setDateTime();

        boolean endTimeAfter = false;
        if (!endTime.isEmpty()) {
            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);
            // Checks if end date is after start date with its date time format
            if (!end.isAfter(start)){
                // Warns user that the format is incorrect
                Toast.makeText(this, "End date should be before start!", Toast.LENGTH_LONG).show();
                changeTextColor(255);
            } else {
                changeTextColor(0);
                endTimeAfter = true;
            }
        }

        return input.validateAddEvent(etEventName) & input.validateAddEvent(tvEndDate)
                & input.validateAddEvent(tvEndTime) & input.validateAddEvent(etCourse) & endTimeAfter;
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
     * @param textView  a TextView of the date.
     */
    public void setDate(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddEventActivity.this, (view, year, month, dayOfMonth) -> {
            yr = year;
            mth = month;
            day = dayOfMonth;

            @SuppressLint("DefaultLocale") String startDate = String.format("%02d-%02d-%d", day, (mth +1), yr);
            textView.setText(startDate);
        }, yr, mth, day);

        datePickerDialog.updateDate(yr, mth, day); // displays prev selected date
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); // disable past date selection
        datePickerDialog.show();
    }

    /**
     * Prompts user to select a time, and sets selected time in textView.
     *
     * @param textView  a TextView of the time.
     */
    public void setTime(TextView textView) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                AddEventActivity.this, (view, hourOfDay, minute) -> {
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
     * @param view  a View for the device screen.
     */
    public void clickBack(View view) {
        activity.redirectActivity(this, SchoolActivity.class);
    }

    /**
     * Saves event into the database.
     *
     * @param view  a View for the device screen.
     */
    public void clickSaveEvent(View view) {
        if (addEventInput()) {
            String eventName = etEventName.getText().toString();
            setDateTime();
            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);

            Event event = new Event(eventName, priority, start, end);
            int userID = ug.getLoggedInUserID();

            eg.saveToDatabase(event, userID);

            activity.redirectActivity(this, SchoolActivity.class);
        }
    }

    /**
     * Directs activity to the Add Assessment Event activity on assessment icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickAssessment(View view) {
          // TODO: direct to assessment page
    }

    /**
     * Directs activity to the Add Deadline Event activity on deadline icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickDeadline(View view) {
        activity.redirectActivity(this, AddDeadlineEventActivity.class);
    }

    /**
     * Directs activity to the Add Class Event activity on class icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickClassTime(View view) {
        activity.redirectActivity(this, AddClassEventActivity.class);
    }

    /**
     * Directs activity to the Add Study Session Event activity on study session icon click.
     *
     * @param view  a View for the device screen.
     */
    public void clickStudySession(View view) {
        activity.redirectActivity(this, AddStudySessionEventActivity.class);
    }

    /**
     * Opens dialog for user to select a start date.
     *
     * @param view  a View for the device screen.
     */
    public void clickStartDate(View view) {
        setDate(tvStartDate);
    }

    /**
     * Opens dialog for user to select an end date.
     *
     * @param view  a View for the device screen.
     */
    public void clickEndDate(View view) {
        setDate(tvEndDate);
    }

    /**
     * Opens dialog for user to select an end time.
     *
     * @param view  a View for the device screen.
     */
    public void clickStartTime(View view) {
        setTime(tvStartTime);
    }

    /**
     * Opens dialog for user to select an end time.
     *
     * @param view  a View for the device screen.
     */
    public void clickEndTime(View view) {
        setTime(tvEndTime);
    }
}