package com.generic.plannr;

import com.generic.plannr.Database.UserInfoDatabaseHelper;
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

    private TextInputEditText etName;
    private TextInputEditText etPassword;
    private TextInputLayout tiName;
    private TextInputLayout tiPassword;
    private MainPageActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
        activity = new MainPageActivity();

        // accesses user info from text boxes
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        tiName = findViewById(R.id.ti_name);
        tiPassword = findViewById(R.id.ti_password);

        // opens the database to retrieve user's name and password
        UserInfoDatabaseHelper user = createDatabase();
        etName.setText(user.getLoggedInName());
        etPassword.setText(user.getPassword(user.getLoggedInEmail()));
    }

    public void clickMenu(View view) { activity.openDrawer(drawerLayout); } // open drawer

    public void clickLogo(View view) { activity.redirectActivity(this, MainPageActivity.class); } // redirect activity to main

    public void clickSchool(View view) { activity.redirectActivity(this, SchoolActivity.class); } // redirect activity to school

    // TODO: change this to life later
    public void clickLife(View view) { activity.redirectActivity(this, MainPageActivity.class); } // redirect activity to life

    public void clickExpenses(View view) { activity.redirectActivity(this, ExpensesActivity.class); } // redirect activity to expenses

    public void clickSettings(View view) { recreate(); } // recreate activity

    public void clickLogOut(View view) {
        MainPageActivity activity = new MainPageActivity();
        activity.logout(this); } // prompt logout

    public void clickSave(View view) {
        // opens database
        UserInfoDatabaseHelper user = createDatabase();
        // gets user input from textbox
        Validator input = new Validator();
        if (input.validate(tiPassword, user, tiName, tiPassword, true))
        {
            String name = Objects.requireNonNull(tiName.getEditText()).getText().toString();
            String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

            // replaces current data in database with user input
            user.updateName(name);
            user.updatePassword(password);
            // disables textbox so it becomes read only
            textboxEditability(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity.closeDrawer(drawerLayout); // close drawer
    }

    public void clickEdit(View view) {
        // enabled textboxes so they can be edited
        textboxEditability(true);
    }

    public UserInfoDatabaseHelper createDatabase() {
        // creates an instance and opens database
        UserInfoDatabaseHelper user = new UserInfoDatabaseHelper(SettingsActivity.this);
        user.openDatabase();
        return user;
    }

    public void textboxEditability(boolean bool) {
        etName.setEnabled(bool);
        etPassword.setEnabled(bool);
    }

}