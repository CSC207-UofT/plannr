package com.generic.ult;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(v -> openSignup());



    }
    //https://www.youtube.com/watch?v=bgIUdb-7Rqo
   private void openSignup() {
        Intent intent = new Intent(this, ExpensesLanding.class);
        startActivity(intent);
   }
}