package com.generic.plannr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.generic.plannr.AddEventActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDeadlineEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //    Initialize Variables
    int eventYear, eventMonth, eventDay, eventHour, eventMinute, priority;
    TextView tvDate, tvTime;
    RadioGroup radioGroup;
    ImageView ivBack, ivSave;
    EditText etEventName;
    private MainActivity activity;

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

        BottomNavigationView navEvents = findViewById(R.id.nav_events);
        navEvents.setSelectedItemId(R.id.nav_deadline);
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
