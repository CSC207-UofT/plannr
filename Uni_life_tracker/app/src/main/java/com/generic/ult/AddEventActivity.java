package com.generic.ult;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    TextView tvDate, tvTime;
    RadioGroup radioGroup;
//    RadioGroup radioButton;
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
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddEventActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.courses));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesList.setAdapter(myAdapter);

        ivSave.setOnClickListener(view -> Toast.makeText(AddEventActivity.this, priority, Toast.LENGTH_SHORT).show());

//        Date Selection
        tvDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEventActivity.this, (view, year1, month1, dayOfMonth) -> {
                        month1 = month1 + 1;
                        String date = dayOfMonth + "/" + month1 + "/" + year1;
                        tvDate.setText(date);
                    }, year, month, day);
            datePickerDialog.show();
        });

//        Time Selection
        tvTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddEventActivity.this, (view, selectedHour, selectedMin) -> {
                        hour = selectedHour;
                        minute = selectedMin;
                        tvTime.setText(String.format(Locale.getDefault(),"%02d:%02d", hour, minute));
                    }, hour, minute, true);
            timePickerDialog.show();
        });
    }

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
}