package UseCases;

import Entities.Expense;

public class ExpensesManager {

    /**
     * Constructor of ExpensesManager
     */
    public ExpensesManager() {}

    /**
     * Changes the name of the Expense
     * @param e Expenses object
     * @param name new name for the Expenses object
     */
    public void changeName(Expense e, String name) {
        e.setName(name);
    }

    /**
     * Changes the value of the expenses
     * @param e Expenses object
     * @param value new value for the Expenses object
     */
    public void changeValue(Expense e, double value) {
        e.setValue(value);
    }

    /**
     * Gets the value of the Expenses object
     * @param e the Expenses object
     * @return the value of the expense
     */
    public double getValue(Expense e) {
        return e.getValue();
    }

    /**
     * This creates a new Expenses object
     * @param name the name of the Expenses object
     * @param value the value of the Expenses object
     * @return the new Expenses object created based on the given parameters
     */
    public Expense createExpense(String name, double value) {
        return new Expense(name, value);
    }

}
