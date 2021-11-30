package com.generic.ult;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        handler.postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, WelcomeLoginSignupActivity.class);
            startActivity(intent);
            finish();
        }, 2500);
    }
}