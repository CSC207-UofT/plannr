package com.generic.ult;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;

public class SettingsActivity extends AppCompatActivity {
    // initialize variable
    DrawerLayout drawerLayout;

    private TextInputEditText textName;
    private TextInputEditText textUni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
    }

    public void clickMenu(View view) { MainPageActivity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { MainPageActivity.closeDrawer(drawerLayout); } // close drawer
        textName = findViewById(R.id.et_name);
        textUni = findViewById(R.id.et_uni);

    public void clickSchool(View view) { MainPageActivity.redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { MainPageActivity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { MainPageActivity.redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { recreate(); } // recreate activity
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

    @Override
    protected void onPause() {
        super.onPause();
        MainPageActivity.closeDrawer(drawerLayout); // close drawer
    }
}