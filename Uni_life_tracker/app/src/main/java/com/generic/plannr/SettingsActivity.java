/* Plannr by Generic Name
 *
 * This file contains methods for activity_settings.xml.
 */
package com.generic.plannr;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.generic.plannr.Controllers.InputTextValidator;
import com.generic.plannr.Controllers.PasswordValidator;
import com.generic.plannr.Gateways.UserGateway;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

@SuppressWarnings("ALL")
public class SettingsActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private TextInputEditText etName;
    private TextInputEditText etPassword;
    private TextInputLayout tiName;
    private TextInputLayout tiPassword;
    private MainActivity activity;
    UserGateway ug = new UserGateway(SettingsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        drawerLayout = findViewById(R.id.drawer_layout); // nav menu
        activity = new MainActivity();

        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        tiName = findViewById(R.id.ti_name);
        tiPassword = findViewById(R.id.ti_password);

        etName.setText(ug.getLoggedInName());
        etPassword.setText(ug.getPassword(ug.getLoggedInEmail()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity.closeDrawer(drawerLayout); // close drawer
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    /**
     * Enables EditText for text editing.
     */
    public void clickEdit(View view) {
        // enabled textboxes so they can be edited
        changeTextEdit(true);
    }

    /**
     * Enables/disables EditText for text editing.
     *
     * @param bool Whether to disable (false) or enable (true) EditText.
     */
    public void changeTextEdit(boolean bool) {
        etName.setEnabled(bool);
        etPassword.setEnabled(bool);
    }

    public void clickSave(View view) {
        // Gets user input from TextBox
        PasswordValidator passwordValidator = new PasswordValidator();
        InputTextValidator input = new InputTextValidator();
        if (passwordValidator.validateEntry(tiPassword, ug, tiName, tiPassword, true) &
                input.validateEntry(tiName, ug, tiName, tiPassword, true)) {
            String name = Objects.requireNonNull(tiName.getEditText()).getText().toString();
            String password = Objects.requireNonNull(tiPassword.getEditText()).getText().toString();

            // Replaces current data in database with user input
            ug.updateName(name);
            ug.updatePassword(password);
            changeTextEdit(false); // Disables TextBox so it becomes read only
        }

    }

    /**
     * Opens navigation menu on menu icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickMenu(View view) {
        activity.openDrawer(drawerLayout);
    } // open drawer

    /**
     * Directs activity to the Main activity on logo click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogo(View view) {
        activity.redirectActivity(this, MainActivity.class);
    } // redirect activity to main

    /**
     * Directs activity to the School activity on school icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSchool(View view) {
        activity.redirectActivity(this, SchoolActivity.class);
    } // redirect activity to school

    /**
     * Directs activity to the Expenses activity on expenses icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickExpenses(View view) {
        activity.redirectActivity(this, ExpensesActivity.class);
    }

    /**
     * Directs activity to the Settings activity on settings icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickSettings(View view) {
    } // recreate activity

    /**
     * Prompts log out on a logout icon click.
     *
     * @param view a View for the device screen.
     */
    public void clickLogOut(View view) {
        activity.logout(this);
    }
}