package com.generic.ult;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class SettingsActivity extends AppCompatActivity {
    // initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
    }

    public void clickMenu(View view) { MainPageActivity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { MainPageActivity.closeDrawer(drawerLayout); } // close drawer

    public void clickSchool(View view) { MainPageActivity.redirectActivity(this, SchoolMain.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { MainPageActivity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { MainPageActivity.redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { recreate(); } // recreate activity

    @Override
    protected void onPause() {
        super.onPause();
        MainPageActivity.closeDrawer(drawerLayout); // close drawer
    }
}