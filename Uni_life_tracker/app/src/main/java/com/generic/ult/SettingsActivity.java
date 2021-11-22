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

        // side menu
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void clickMenu(View view){
        // open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        // open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void clickLogo(View view) {
        // close drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        // close drawer layout

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // when drawer is open, close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}