package com.generic.ult;

import Database.UserInfoDatabaseHelper;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {
    // initialize variable
    DrawerLayout drawerLayout;

    private TextInputEditText textName;
    private TextInputEditText textUni;
    private TextInputLayout testName;
    private TextInputLayout testUni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
        textName = findViewById(R.id.et_name);
        textUni = findViewById(R.id.et_uni);
        testName = findViewById(R.id.ti_name);
        testUni = findViewById(R.id.ti_university);

        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(SettingsActivity.this);
        user.openDatabase();

        textName.setText(user.getName());
        textUni.setText(user.getUni());
    }

    public void clickMenu(View view) { MainPageActivity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { MainPageActivity.closeDrawer(drawerLayout); } // close drawer

    public void clickSchool(View view) { MainPageActivity.redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { MainPageActivity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { MainPageActivity.redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { recreate(); } // recreate activity

    public void clickSave(View view) {
        String name = Objects.requireNonNull(testName.getEditText()).getText().toString();
        //String uni = Objects.requireNonNull(testUni.getEditText()).getText().toString();
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(SettingsActivity.this);
        user.openDatabase();
        //user.updateName(name);
        //user.updateUni(uni);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainPageActivity.closeDrawer(drawerLayout); // close drawer
    }

    public void clickEdit(View view) {
        textName.setEnabled(true);
        textUni.setEnabled(true);
    }
}