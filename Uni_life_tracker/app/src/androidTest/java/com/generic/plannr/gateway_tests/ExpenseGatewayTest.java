package com.generic.plannr.gateway_tests;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Entities.User;
import com.generic.plannr.Gateways.ExpenseGateway;
import com.generic.plannr.Gateways.UserGateway;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpenseGatewayTest {
    Context context;
    ExpenseGateway eg;
    UserGateway ug;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ug = new UserGateway(context);
        eg = new ExpenseGateway(context);
        ug.openDatabase();
        eg.openDatabase();
        // Saves the user to database
        ug.saveToDatabase(
                new User("test", "test@gmail.com", "Test@123")
        );
    }

    @After
    public void finishUp() {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void openDatabase() {
        assertEquals(0, eg.getAllExpenses(ug.getLoggedInUserID()).size());
    }

    @Test
    public void saveToDatabase() {
        Expense e = new Expense("test expense", 100.0);
        eg.saveToDatabase(e, ug.getLoggedInUserID());
        assertEquals(e.getName(), eg.getAllExpenses(ug.getLoggedInUserID()).get(0).getName());
    }

    @Test
    public void getByID() {
    }
}