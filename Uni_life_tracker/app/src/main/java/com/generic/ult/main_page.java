package com.generic.ult;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.text.DateFormat;
import java.util.Calendar;

public class main_page extends AppCompatActivity {
    // initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // show today's date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_date);
        textViewDate.setText(currentDate);

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view){
        // open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        // open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
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

    public void ClickSchool(View view) {
        // recreate activity
        recreate();
    }

//    public void ClickDashboard(View view) {
//        // redirect activity to dashboard
//        redirectActivity(this,);
//    }
//
////    public void ClickLife(View view) {
////        // redirect activity to dashboard
////        redirectActivity(this,);
//    }

    public static void redirectActivity(Activity activity, Class aClass) {
        // initialize intent
        Intent intent = new Intent(activity,aClass);
        // set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // close drawer
        closeDrawer(drawerLayout);
    }
}