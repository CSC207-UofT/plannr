package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Expense;

/**
 * Calculates the total expense and balance of the current user
 */
public class CalculateExpenseBalance {
    private static ExpenseList expenseList;
    private static UserManager user;

    /**
     * Sets up CalculateExpenseBalance with the current user's list of expenses
     * @param expenses the current user's list of expenses
     * @param um the user manager of the current user
     */
    public CalculateExpenseBalance(ExpenseList expenses, UserManager um) {
        expenseList = expenses;
        user = um;
    }

    /**
     * Calculates the total expenses of the current user
     * @return the total expenses of the current user
     */
    public double getTotalExpense() {
        double totalExpense = 0;
        for (Expense e: expenseList.getExpensesList()) {
            totalExpense += e.getValue();
        }
        return totalExpense;
    }

    /**
     * Calculates the current balance of the current user
     * @return the current balance of the current user
     */
    public double getBalance() {
        return Double.parseDouble(user.getUsersIncome()) - getTotalExpense();
    }
}
