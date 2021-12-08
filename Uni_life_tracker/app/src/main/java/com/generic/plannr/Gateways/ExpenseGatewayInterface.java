package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.Expense;

import java.util.List;

public interface ExpenseGatewayInterface {
    /**
     * Open the database for reading or writing
     */
    void openDatabase();

    /**
     * Insert the given expense into the database
     *
     * @param expense The Expense to be inserted
     * @param userID  The id of the User
     */
    void saveToDatabase(final Expense expense, final int userID);

    /**
     * Get the Expense associated with id expenseID currently stored
     * in the database
     *
     * @param expenseID the id of the Expense we want to return
     * @return the Expense with id expenseID
     */
    Expense getByID(final int expenseID);

    /**
     * Get the list of Expenses currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     * @return a list of all expenses stored in the database for the user
     */
    List<Expense> getAllExpenses(int userID);

}
