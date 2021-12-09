package com.generic.plannr.UseCases;
import com.generic.plannr.Entities.Expense;

import java.util.ArrayList;

/**
 * A data structure for the current user's list of expenses
 */
public class ExpenseList {
    private final ArrayList<Expense> expenseList = new ArrayList<>();

    /**
     * Returns a list of the current user's expenses
     *
     * @return an array list of the user's expenses
     */
    public ArrayList<Expense> getExpensesList() {
        return expenseList;
    }

    /**
     * Add an expense to expenseList
     *
     * @param e The expense to be added
     */
    public void add (Expense e) {
        expenseList.add(e);
    }

    /**
     * Clears the list of expenses for the next user to log in/sign up
     * To be used whenever a user logs out
     */
    public void clear() {
        expenseList.clear();
    }
}
