package com.generic.plannr.tests.usecase;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.UseCases.ExpensesManager;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ExpensesManagerTest {
    ExpensesManager expManager;

    @Before
    public void setUp() {
        expManager = ExpensesManager.getInstance();
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

    @Test
    public void TestChangeName() {
        Expense exp = expManager.createExpense("test", 100.0);
        expManager.changeName(exp, "tears");
        assertEquals("tears", expManager.getExpenseName(exp));
    }
}