package com.generic.plannr.androidtests.entity;

import static org.junit.Assert.*;
import static com.generic.plannr.UseCases.UserCreator.signUp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Gateways.UserGateway;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpenseTest {
    Context context;
    UserGateway UG;
    Expense exp;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UG = new UserGateway(context);
        UG.openDatabase();
        signUp(
                UG,
                "test",
                "test@gmail.com",
                "Test@123"
        );
        exp = new Expense(UG.getLoggedInUserID(), "test expense", 9);
    }

    @After
    public void tearDown() {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void getUserID() {
        assertEquals(exp.getUserID(), ((int) UG.getLoggedInUserID()));
    }

    @Test
    public void getName() {
        assertEquals("test expense", exp.getName());
    }

    @Test
    public void getValue() {
        assertEquals(9, exp.getValue(), 0.0);
    }

    @Test
    public void setName() {
        exp.setName("test 2");
        assertEquals("test 2", exp.getName());
    }

    @Test
    public void setValue() {
        exp.setValue(100.0);
        assertEquals(100, exp.getValue(), 0.0);
    }
}
