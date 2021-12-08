package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Expense;

public class ExpensesManager {
    private static ExpensesManager EM_INSTANCE;

    /**
     * This is a private constructor, implemented to disallow client to use the new keyword
     */
    private ExpensesManager() {
    }

    /**
     * Getter for an instance of ExpensesManager, implemented to make sure there is only one instance
     * of ExpensesManager in our program during runtime
     *
     * @return ExpensesManager object
     */
    public ExpensesManager getInstance() {
        if (EM_INSTANCE == null) {
            EM_INSTANCE = new ExpensesManager();
        }
        return EM_INSTANCE;
    }

    /**
     * Changes the name of the Expense
     *
     * @param e    Expenses object
     * @param name new name for the Expenses object
     */
    public void changeName(Expense e, String name) {
        e.setName(name);
    }

    /**
     * Returns the name of a given expense
     *
     * @param e Expenses object
     * @return a String variable containing the name of the Expense
     */
    public String getExpenseName(Expense e) {
        return e.getName();
    }

    /**
     * Changes the value of the expenses
     *
     * @param e     Expenses object
     * @param value new value for the Expenses object
     */
    public void changeValue(Expense e, double value) {
        e.setValue(value);
    }

    /**
     * Gets the value of the Expenses object
     *
     * @param e the Expenses object
     * @return the value of the expense
     */
    public double getValue(Expense e) {
        return e.getValue();
    }

    /**
     * This creates a new Expenses object
     *
     * @param name  the name of the Expenses object
     * @param value the value of the Expenses object
     * @return the new Expenses object created based on the given parameters
     */
    public Expense createExpense(String name, double value) {
        return new Expense(name, value);
    }

}
