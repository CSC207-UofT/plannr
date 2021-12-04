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
import com.generic.plannr.Database.EventDatabaseHelper;
import com.generic.plannr.Database.UserInfoDatabaseHelper;
import com.generic.plannr.Entities.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
//    Initialize Variables
    int startYear, startMonth, startDay, endYear, endMonth, endDay, startHour, startMinute, endHour, endMinute, priority;
    TextView tvStartDate, tvStartTime, tvEndDate, tvEndTime, tvAssessment, tvDeadline, tvClassTime, tvStudySession;
    RadioGroup rgPriorities;
    ImageView ivBack, ivSave;
    EditText etEventName, etCourse;
    private MainPageActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

//        Assign Variables
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

        activity = new MainPageActivity();

//       Initialize Calendar
        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);
        startHour = calendar.get(Calendar.HOUR_OF_DAY);
        startMinute = calendar.get(Calendar.MINUTE);

//       Start Date & Time Format (Current Date & Time)
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());

        tvStartDate.setText(date);
        tvStartTime.setText(time);

        rgPriorities.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this::clickBack);
        tvAssessment.setOnClickListener(this::clickAssessment);
        tvDeadline.setOnClickListener(this::clickDeadline);
        tvClassTime.setOnClickListener(this::clickClassTime);
        tvStudySession.setOnClickListener(this::clickStudySession);


//        Start Date Selection
        tvStartDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEventActivity.this, (view, year, month, dayOfMonth) -> {
                        startYear = year;
                        startMonth = month;
                        startDay = dayOfMonth;

                        @SuppressLint("DefaultLocale")
                        String startDate = String.format("%02d-%02d-%d", startDay, (startMonth+1), startYear);
                        tvStartDate.setText(startDate);
                    }, startYear, startMonth, startDay);
            datePickerDialog.updateDate(startYear, startMonth, startDay); // displays prev selected date
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); // disable past date selection
            datePickerDialog.show();
        });

//        End Date Selection
        tvEndDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEventActivity.this, (view, year, month, dayOfMonth) -> {
                endYear = year;
                endMonth = month;
                endDay = dayOfMonth;

                @SuppressLint("DefaultLocale")
                String endDate = String.format("%02d-%02d-%d", endDay, (endMonth+1), endYear);
                tvEndDate.setText(endDate);
            }, endYear, endMonth, endDay);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date eDate = null;
            try {
                eDate = formatter.parse(startDay + "-" + (startMonth+1) + "-" + startYear);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert eDate != null;
            long minDate = eDate.getTime();

            datePickerDialog.updateDate(endYear, endMonth, endDay); // displays prev selected date
            datePickerDialog.getDatePicker().setMinDate(minDate); // disable past date selection
            datePickerDialog.show();
        });

//        Start Time Selection
        tvStartTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddEventActivity.this, (view, hourOfDay, minute) -> {
                        startHour = hourOfDay;
                        startMinute = minute;

                        Calendar calendar12 = Calendar.getInstance(); // initialize calendar
                        String startDate = tvStartDate.getText().toString().trim();
                        String[] strings = startDate.split("-");
                        int sDay = Integer.parseInt(strings[0]);
                        calendar12.set(Calendar.DAY_OF_MONTH, sDay);
                        calendar12.set(Calendar.HOUR_OF_DAY, startHour);
                        calendar12.set(Calendar.MINUTE, startMinute);
                        tvStartTime.setText(DateFormat.format("hh:mm aa", calendar12));
                    }, startHour, startMinute, false);
            timePickerDialog.show();
        });

//        End Time Selection
        tvEndTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddEventActivity.this, (view, hourOfDay, minute) -> {
                        endHour = hourOfDay;
                        endMinute = minute;

                        Calendar calendar1 = Calendar.getInstance(); // initialize calendar
                        String endDate = tvEndDate.getText().toString().trim();
                        String[] strings = endDate.split("-");
                        int sDay = Integer.parseInt(strings[0]);
                        calendar1.set(Calendar.DAY_OF_MONTH, sDay);
                        calendar1.set(Calendar.HOUR_OF_DAY, endHour);
                        calendar1.set(Calendar.MINUTE, endMinute);
                        tvEndTime.setText(DateFormat.format("hh:mm aa", calendar1));
                    }, endHour, endMinute, false);
            timePickerDialog.show();
        });
    }

//    Back Button
    public void clickBack(View view) {
        activity.redirectActivity(this, SchoolActivity.class);
    }

//    Priority Selection
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_high_priority) {
            priority = 1;
        } else if (checkedId == R.id.rb_med_priority) {
            priority = 2;
        } else if (checkedId == R.id.rb_low_priority) {
            priority = 3;
        }
    }

    public void clickAssessment(View view) {
        activity.redirectActivity(this, MainPageActivity.class);  // TODO: direct to assessment page
    }

    public void clickDeadline(View view) {
        activity.redirectActivity(this, MainPageActivity.class);  //TODO: direct to deadline page
    }

    public void clickClassTime(View view) {
        activity.redirectActivity(this, MainPageActivity.class);  // TODO: direct to class page
    }

    public void clickStudySession(View view) {
        activity.redirectActivity(this, MainPageActivity.class);  // // TODO: direct to study session page
    }

    public void clickSaveEvent(View view) {
        if (addEventInput()) {
            EventDatabaseHelper eventdb = new EventDatabaseHelper(AddEventActivity.this);
            eventdb.openDatabase();

            UserInfoDatabaseHelper userdb = new UserInfoDatabaseHelper(AddEventActivity.this);
            userdb.openDatabase();

            String eventName = etEventName.getText().toString();
            String startDate = tvStartDate.getText().toString().trim();
            String endDate = tvEndDate.getText().toString().trim();
            String startTime = tvStartTime.getText().toString().trim();
            String endTime = tvEndTime.getText().toString().trim();


            DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

            LocalDateTime start = LocalDateTime.parse(startDate + " " + startTime, DATEFORMAT);
            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);

            String email = userdb.getLoggedInEmail();

            Event event = new Event(eventName, priority, start, end);

            eventdb.insertEvent(event, email);

            activity.redirectActivity(this, SchoolActivity.class);
        }
    }

    public boolean addEventInput() {
        Validator input = new Validator();

        UserInfoDatabaseHelper userdb = new UserInfoDatabaseHelper(AddEventActivity.this);
        userdb.openDatabase();

        String startDate = tvStartDate.getText().toString().trim();
        String endDate = tvEndDate.getText().toString().trim();
        String startTime = tvStartTime.getText().toString().trim();
        String endTime = tvEndTime.getText().toString().trim();


        DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        LocalDateTime start = LocalDateTime.parse(startDate + " " + startTime, DATEFORMAT);
        if (!endTime.isEmpty()) {
            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, DATEFORMAT);
            if (!end.isAfter(start)){
                Toast.makeText(this, "End date should be before start", Toast.LENGTH_LONG).show();
                tvEndDate.setTextColor(Color.RED);
                tvEndTime.setTextColor(Color.RED);
            }

        }
        return input.validateAddEvent(etEventName) & input.validateAddEvent(tvEndDate)
                & input.validateAddEvent(tvEndTime) & input.validateAddEvent(etCourse);
    }
}
