package UseCases;

import Entities.Expenses;

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
    public void changeName(Expenses e, String name) {
        e.setName(name);
    }

    /**
     * Changes the value of the expenses
     * @param e Expenses object
     * @param value new value for the Expenses object
     */
    public void changeValue(Expenses e, double value) {
        e.setValue(value);
    }

}
