package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Gateways.ExpenseGatewayInterface;

/**
 * Adds a school event to a user's expense list
 */
public class AddExpense {
    private static ExpenseList expenseList;
    private static ExpenseGatewayInterface eg;

    /**
     * Sets up AddSchoolEvent with the current user's list of expenses
     *
     * @param expenses       the current user's list of expenses
     * @param expenseGateway the gateway to the expense database table
     */
    public AddExpense(ExpenseList expenses, ExpenseGatewayInterface expenseGateway) {
        expenseList = expenses;
        eg = expenseGateway;
    }


    /**
     * Creates and adds an expense to a user's list of expenses
     *
     * @param userID the ID of the user that has this expense
     * @param name   the name of the expense to be created
     * @param amount the value of the expense to be created
     */
    public static void addExpense(int userID, String name, double amount) {
        Expense expense = new Expense(userID, name, amount);
        expenseList.add(expense);
        eg.saveToDatabase(expense, userID);
    }
}
