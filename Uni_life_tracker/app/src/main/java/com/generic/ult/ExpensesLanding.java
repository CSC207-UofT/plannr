package com.generic.ult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class ExpensesLanding extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private TextInputLayout textInputName;
    private TextInputLayout textInputAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_landing);
        textInputName = findViewById(R.id.expense_name);
        textInputAmount = findViewById(R.id.expense_amount);
        Button e_go_to_view_button = findViewById(R.id.e_go_to_view_button);
        e_go_to_view_button.setOnClickListener(this::ExpensesViewInput);

    }
    // change ExpensesLanding to ExpensesList when merged
    private void openExpensesView() {
        Intent intent = new Intent(this, AddExpenses.class);
        startActivity(intent);
    }


    private boolean validate(TextInputLayout textInput) {
        String Input = Objects.requireNonNull(textInput.getEditText()).getText().toString().trim();

        if (Input.isEmpty()) {
            textInput.setError("Field cannot be empty");
            return false;
        } else {
            textInput.setError(null);
            return true;
        }
    }
    public void ExpensesViewInput(View e) {
        if (!(validate(textInputName) | validate( textInputAmount))) {
            // Here we can get all the info we need
            // For example to get the email you can do textInputEmail.getEditTest().getText().toString()
        }else {openExpensesView();}


    }

}