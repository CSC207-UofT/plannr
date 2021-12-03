package com.generic.plannr.UseCaseTests;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.UseCases.ExpensesManager;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ExpensesManagerTest {
    ExpensesManager expManager;

    @Before
    public void setUp() {
        expManager = new ExpensesManager();
    }

    @Test
    public void TestCreateExpense() {
        Expense exp = expManager.createExpense("test", 100.0);
        assertEquals("test", expManager.getExpenseName(exp));
        assertEquals(100, expManager.getValue(exp), 0.0);
    }

    @Test
    public void TestChangeValue() {
        Expense exp = expManager.createExpense("test", 100.0);
        expManager.changeValue(exp, 1.0);
        assertEquals(1, expManager.getValue(exp), 0.0);
    }
}