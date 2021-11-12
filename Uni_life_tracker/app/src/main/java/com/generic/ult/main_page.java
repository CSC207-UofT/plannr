package com.generic.ult;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DateFormat;
import java.util.Calendar;

public class main_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
    }
}