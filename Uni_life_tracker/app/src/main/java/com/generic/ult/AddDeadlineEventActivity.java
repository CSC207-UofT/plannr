package com.generic.ult;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDeadlineEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //    Initialize Variables
    int eventYear, eventMonth, eventDay, eventHour, eventMinute, priority;
    TextView tvDate, tvTime, tvAssessment, tvDeadline, tvClassTime, tvStudySession;
    RadioGroup radioGroup;
    ImageView ivBack, ivSave;
    EditText etEventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deadline_event);

//        Assign Variables
        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);
        etEventName = findViewById(R.id.et_event_name);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        radioGroup = findViewById(R.id.rg_priorities);
        tvAssessment = findViewById(R.id.tv_assessment);
        tvDeadline = findViewById(R.id.tv_deadline);
        tvClassTime = findViewById(R.id.tv_class_time);
        tvStudySession = findViewById(R.id.tv_study_session);

//        Spinner coursesList = findViewById(R.id.spn_courses); // TO BE IMPLEMENTED IN PHASE 2

//       Initialize Calendar
        Calendar calendar = Calendar.getInstance();
        eventYear = calendar.get(Calendar.YEAR);
        eventMonth = calendar.get(Calendar.MONTH);
        eventDay = calendar.get(Calendar.DAY_OF_MONTH);
        eventHour = calendar.get(Calendar.HOUR_OF_DAY);
        eventMinute = calendar.get(Calendar.MINUTE);

//      Date & Time Format
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());

        tvDate.setText(date);
        tvTime.setText(time);

        radioGroup.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this::ClickBack);
        tvAssessment.setOnClickListener(this::ClickAssessment);
        tvClassTime.setOnClickListener(this::ClickClassTime);
        tvStudySession.setOnClickListener(this::ClickStudySession);

        ivSave.setOnClickListener(v -> {
            String eventName = etEventName.getText().toString();
            Toast.makeText(AddDeadlineEventActivity.this, eventName, Toast.LENGTH_SHORT).show(); // TODO: remove
        });

//        Date Selection
        tvDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddDeadlineEventActivity.this, (view, year, month, dayOfMonth) -> {
                eventYear = year;
                eventMonth = month;
                eventDay = dayOfMonth;

                String eventDate = eventDay + "-" + (eventMonth+1) + "-" + eventYear;
                tvDate.setText(eventDate);
            }, eventYear, eventMonth, eventDay);
            datePickerDialog.updateDate(eventYear, eventMonth, eventDay); // displays prev selected date
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); // disable past date selection
            datePickerDialog.show();
        });

//        Time Selection
        tvTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddDeadlineEventActivity.this, (view, hourOfDay, minute) -> {
                eventHour = hourOfDay;
                eventMinute = minute;

                Calendar calendar12 = Calendar.getInstance(); // initialize calendar
                String eventDate = tvDate.getText().toString().trim();
                String[] strings = eventDate.split("-");
                int sDay = Integer.parseInt(strings[0]);
                calendar12.set(Calendar.DAY_OF_MONTH, sDay);
                calendar12.set(Calendar.HOUR_OF_DAY, eventHour);
                calendar12.set(Calendar.MINUTE, eventMinute);
                tvTime.setText(DateFormat.format("hh:mm aa", calendar12));
            }, eventHour, eventMinute, false);
            timePickerDialog.show();
        });

//        TO BE IMPLEMENTED IN PHASE 2: Courses List
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddDeadlineEventActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.courses));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        coursesList.setAdapter(myAdapter);
//
//        ivSave.setOnClickListener(view -> Toast.makeText(AddDeadlineEventActivity.this, priority, Toast.LENGTH_SHORT).show());
    }

    //    Back Button
    public void ClickBack(View view) {
        Intent intent = new Intent(this, MainPageActivity.class); // TODO: direct to school/life page
        startActivity(intent);
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

    public void ClickAssessment(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    public void ClickClassTime(View view) {
        Intent intent = new Intent(this, MainPageActivity.class); // TODO: direct to class page
        startActivity(intent);
    }

    public void ClickStudySession(View view) {
        Intent intent = new Intent(this, MainPageActivity.class); // TODO: direct to study session page
        startActivity(intent);
    }
}
