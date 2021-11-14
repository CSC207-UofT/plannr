package com.generic.ult;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    TextView tvDate, tvTime;
    RadioGroup radioGroup;
    RadioGroup radioButton;
    ImageView ivSave;

    int priority;
//    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        tvDate = findViewById(R.id.editTextDate);
        tvTime = findViewById(R.id.editTextEndTime);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        radioGroup = findViewById(R.id.priorities);
        ivSave = findViewById(R.id.save);

        radioGroup.setOnCheckedChangeListener(this);

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddEvent.this, priority, Toast.LENGTH_SHORT).show();
            }
        });

//        Date Selection
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tvDate.setText(date);
                    }
                }, year, month, day);
                tvDate.setText(null);
                datePickerDialog.show();
            }
        });

    }

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