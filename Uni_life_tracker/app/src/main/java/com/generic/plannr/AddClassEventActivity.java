package com.generic.plannr;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddClassEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //    Initialize Variables
    int startYear, startMonth, startDay, endYear, endMonth, endDay, startHour, startMinute, endHour, endMinute, priority;
    TextView tvStartDate, tvStartTime, tvEndDate, tvEndTime;
    RadioGroup radioGroup;
    ImageView ivBack, ivSave;
    EditText etEventName;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_event);

//        Assign Variables
        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);
        etEventName = findViewById(R.id.et_event_name);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartTime = findViewById(R.id.tv_start_time);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvEndTime = findViewById(R.id.tv_end_time);
        radioGroup = findViewById(R.id.rg_priorities);

        BottomNavigationView navEvents = findViewById(R.id.nav_events);
        navEvents.setSelectedItemId(R.id.nav_class);
        navEvents.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_assessment:
                    startActivity(new Intent(getApplicationContext(), AddEventActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_class:
                    startActivity(new Intent(getApplicationContext(), AddClassEventActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_deadline:
                    startActivity(new Intent(getApplicationContext(), AddDeadlineEventActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_study_session:
                    startActivity(new Intent(getApplicationContext(), AddStudySessionEventActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });

//        Spinner coursesList = findViewById(R.id.spn_courses); // TO BE IMPLEMENTED IN PHASE 2

//       Initialize Calendar
        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);
        startHour = calendar.get(Calendar.HOUR_OF_DAY);
        startMinute = calendar.get(Calendar.MINUTE);

//       Start Date & Time Format
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());

        tvStartDate.setText(date);
        tvStartTime.setText(time);

        radioGroup.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this::ClickBack);

        ivSave.setOnClickListener(v -> {
            String eventName = etEventName.getText().toString();
            Toast.makeText(AddClassEventActivity.this, eventName, Toast.LENGTH_SHORT).show(); // TODO: remove
        });

//        Start Date Selection
        tvStartDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddClassEventActivity.this, (view, year, month, dayOfMonth) -> {
                startYear = year;
                startMonth = month;
                startDay = dayOfMonth;

                String startDate = startDay + "-" + (startMonth+1) + "-" + startYear;
                tvStartDate.setText(startDate);
            }, startYear, startMonth, startDay);
            datePickerDialog.updateDate(startYear, startMonth, startDay); // displays prev selected date
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); // disable past date selection
            datePickerDialog.show();
        });

//        End Date Selection
        tvEndDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddClassEventActivity.this, (view, year, month, dayOfMonth) -> {
                endYear = year;
                endMonth = month + 1;
                endDay = dayOfMonth;
                String endDate = endDay + "-" + endMonth + "-" + endYear;
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
                    AddClassEventActivity.this, (view, hourOfDay, minute) -> {
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
                    AddClassEventActivity.this, (view, hourOfDay, minute) -> {
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

//        TO BE IMPLEMENTED IN PHASE 2: Courses List
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddClassEventActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.courses));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        coursesList.setAdapter(myAdapter);
//
//        ivSave.setOnClickListener(view -> Toast.makeText(AddClassEventActivity.this, priority, Toast.LENGTH_SHORT).show());
    }


    //    Back Button
    public void ClickBack(View view) {
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
}