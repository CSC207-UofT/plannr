package com.generic.ult;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    TextView tvStartDate, tvStartTime, tvEndDate, tvEndTime, tvAssessment, tvDeadline, tvClassTime, tvStudySession;
    RadioGroup radioGroup;
    ImageView ivBack, ivSave;

    int priority, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartTime = findViewById(R.id.tv_start_time);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvEndTime = findViewById(R.id.tv_end_time);
        radioGroup = findViewById(R.id.rg_priorities);
        tvAssessment = findViewById(R.id.tv_assessment);
        tvDeadline = findViewById(R.id.tv_deadline);
        tvClassTime = findViewById(R.id.tv_class_time);
        tvStudySession = findViewById(R.id.tv_study_session);

        Spinner coursesList = findViewById(R.id.spn_courses);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        radioGroup.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this::ClickBack);
        tvAssessment.setOnClickListener(this::ClickAssessment);
        tvDeadline.setOnClickListener(this::ClickDeadline);
        tvClassTime.setOnClickListener(this::ClickClassTime);
        tvStudySession.setOnClickListener(this::ClickStudySession);

//        Start Date Selection
        tvStartDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEventActivity.this, (view, year1, month1, dayOfMonth) -> {
                        month1 = month1 + 1;
                        String date = dayOfMonth + "/" + month1 + "/" + year1;
                        tvStartDate.setText(date);
                    }, year, month, day);
            datePickerDialog.show();
        });

//        End Date Selection
        tvEndDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEventActivity.this, (view, year1, month1, dayOfMonth) -> {
                month1 = month1 + 1;
                String date = dayOfMonth + "/" + month1 + "/" + year1;
                tvEndDate.setText(date);
            }, year, month, day);
            datePickerDialog.show();
        });

//        Start Time Selection
        tvStartTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddEventActivity.this, (view, selectedHour, selectedMin) -> {
                        hour = selectedHour;
                        minute = selectedMin;
                        tvStartTime.setText(String.format(Locale.getDefault(),"%02d:%02d", hour, minute));
                    }, hour, minute, true);
            timePickerDialog.show();
        });

//        Start Time Selection
        tvEndTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddEventActivity.this, (view, selectedHour, selectedMin) -> {
                hour = selectedHour;
                minute = selectedMin;
                tvEndTime.setText(String.format(Locale.getDefault(),"%02d:%02d", hour, minute));
            }, hour, minute, true);
            timePickerDialog.show();
        });

//        Courses List
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddEventActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.courses));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesList.setAdapter(myAdapter);

        ivSave.setOnClickListener(view -> Toast.makeText(AddEventActivity.this, priority, Toast.LENGTH_SHORT).show());
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

    //    Save Button
    public void ClickSave(View view) {
    }

    public void ClickAssessment(View view) {
        Intent intent = new Intent(this, MainPageActivity.class); // TODO: direct to assessment page
        startActivity(intent);
    }

    public void ClickDeadline(View view) {
        Intent intent = new Intent(this, MainPageActivity.class); // TODO: direct to deadline page
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