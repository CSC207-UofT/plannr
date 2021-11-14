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
    TextView tvStartDate, tvStartTime;
    RadioGroup radioGroup;
//    RadioGroup radioButton;
    ImageView ivBack, ivSave;

    int priority, hour, minute;
//    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartTime = findViewById(R.id.tv_start_time);
        radioGroup = findViewById(R.id.rg_priorities);
        ivBack = findViewById(R.id.iv_back);
        ivSave = findViewById(R.id.iv_save);
        Spinner coursesList = findViewById(R.id.spn_courses);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        radioGroup.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this::ClickBack);

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
        if (checkedId == R.id.high_priority) {
            priority = 1;
        } else if (checkedId == R.id.med_priority) {
            priority = 2;
        } else if (checkedId == R.id.low_priority) {
            priority = 3;
        }
    }

    //    Save Button
    public void ClickSave(View view) {
    }
}