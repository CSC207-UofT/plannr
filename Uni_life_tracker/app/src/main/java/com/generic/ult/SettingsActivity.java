package com.generic.ult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class SettingsActivity extends AppCompatActivity {

    private TextInputEditText textName;
    private TextInputEditText textUni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        textName = findViewById(R.id.et_name);
        textUni = findViewById(R.id.et_uni);

        ImageView backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this::ClickBack);

        SignUpActivity userInfo = new SignUpActivity();

        String name = userInfo.getName();
        Toast.makeText(this, name,
                Toast.LENGTH_LONG).show();

        textName.setText("userInfo.getName()");
        textUni.setText("userInfo.getUni()");
    }

    public void ClickBack(View view) {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }


}