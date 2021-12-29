package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Gateways.ExpenseGatewayInterface;

/**
 * Loads the events from the database of the current user
 * To be used when a user logs in
 */
@SuppressWarnings("ALL")
public class ExpenseLoader {
    private ExpenseGatewayInterface eg;
    private ExpenseList expenseList;

    /**
     * Sets up the EventLoader with the event gateway and event list
     *
     * @param expenseGateway the gateway to the expense database table
     * @param expenses       the list of expenses for the user
     */
    public ExpenseLoader(ExpenseGatewayInterface expenseGateway, ExpenseList expenses) {
        this.eg = expenseGateway;
        this.expenseList = expenses;
    }

    /**
     * Loads the expenses from the database of the current user
     *
     * @param userID the ID of the current user
     */
    public void loadExpenses(int userID) {
        for (Expense e : eg.getAllExpenses(userID)) {
            expenseList.add(e);
        }
    }
}
