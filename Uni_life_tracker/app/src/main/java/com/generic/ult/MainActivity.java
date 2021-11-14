package com.generic.ult;

import android.content.Intent;
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

        Button e_go_to_view_button = findViewById(R.id.e_go_to_view_button);
        e_go_to_view_button.setOnClickListener(v -> openExpensesView());

        Button e_submit = findViewById(R.id.e_submit);
        e_submit.setOnClickListener(v -> openExpensesView());
    }
    //https://www.youtube.com/watch?v=bgIUdb-7Rqo
   private void openSignup() {
        Intent intent = new Intent(this, ExpensesLanding.class);
        startActivity(intent);
   }
    // change ExpensesLanding to ExpensesList when merged
    private void openExpensesView() {
        Intent intent = new Intent(this, ExpensesLanding.class);
        startActivity(intent);
    }
}