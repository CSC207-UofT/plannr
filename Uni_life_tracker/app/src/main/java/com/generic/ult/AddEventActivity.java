package com.generic.ult;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    TextView tvDate, tvTime;
    RadioGroup radioGroup;
    RadioGroup radioButton;
    ImageView ivSave;

    int priority, hour, minute;
//    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        tvDate = findViewById(R.id.editTextDate);
        tvTime = findViewById(R.id.editTextEndTime);
        radioGroup = findViewById(R.id.priorities);
        ivSave = findViewById(R.id.save);
        Spinner coursesList = findViewById(R.id.courses);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        radioGroup.setOnCheckedChangeListener(this);

//       Courses List
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddEventActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.courses));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesList.setAdapter(myAdapter);

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddEventActivity.this, priority, Toast.LENGTH_SHORT).show();
            }
        });

//        Date Selection
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tvDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

//        Time Selection
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMin) {
                        hour = selectedHour;
                        minute = selectedMin;
                        tvTime.setText(String.format(Locale.getDefault(),"%02d:%02d", hour, minute));
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

//    //        Time Selection
//    public void popTimePicker(View view) {
//        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int selectedHour, int selectedMin) {
//                hour = selectedHour;
//                minute = selectedMin;
//                tvTime.setText(String.format(Locale.getDefault(),"%02d:%02d", hour, minute));
//            }
//        };
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
//
////        timePickerDialog.setTitle("Select a Time");
//        timePickerDialog.show();
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.high_priority:
                priority = 1;
                break;

            case R.id.med_priority:
                priority = 2;
                break;

            case R.id.low_priority:
                priority = 3;
                break;
        }
    }
}