package com.generic.plannr.Entities;

@SuppressWarnings("ALL")
public class Expense {
    private int userID;
    private String name;
    private double value;

    /**
     * Construct an Expense, giving them the given name,
     * priority, start date, and end date.
     *
     * @param userID the userID of the user this Expense belongs to
     * @param name   The Expense's name
     * @param value  The Expense's expenditures
     */
    public Expense(int userID, String name, double value) {
        this.userID = userID;
        this.name = name;
        this.value = value;
    }

    /**
     * Returns the userID of the user this Expense belongs to
     *
     * @return the userID of the Expense
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * Getter method for name
     *
     * @return a String that describes the Expense's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for value
     *
     * @return a double that describes the Expense value
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter method for name
     *
     * @param name the new name of the Expense
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for value of Expense
     *
     * @param value the new value of the Expense
     */
    public void setValue(double value) {
        this.value = value;
    }
}
