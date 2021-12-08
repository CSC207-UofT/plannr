package com.generic.plannr.Entities;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private double income;

    /**
     * Construct a user, giving them the given name, email,
     * password
     *
     * @param name      The user's name
     * @param email     The user's email
     * @param password  The user's password
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.income = 0.0;
    }

    /**
     * getter method for name
     *
     * @return a String that describes the user's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter method for email
     *
     * @return a String that describes the user's email
     */
    public String getEmail() { return this.email; }

    /**
     * getter method for password
     *
     * @return a String that describes the user's password
     */
    public String getPassword() { return this.password; }

    /**
     * getter method for courses
     *
     * @return an ArrayList that describes the user's current course list
     */
    public ArrayList<String> getCourses() {
        return this.courses;
    }

    /**
     * getter method for eventList
     *
     * @return an ArrayList that describes all the user's events
     */
    public ArrayList<SchoolEvent> getEventList() {
        return this.eventList;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the user's expenses list
     *
     * @return ArrayList of Expenses object
     */
    public ArrayList<Expense> getExpenseList() {
        return this.expenseList;
    }

    /**
     * Gets the user's income
     *
     * @return the user's income as a double object
     */
    public double getIncome() {
        return this.income;
    }

    /**
     * Setter method for user's income
     *
     * @param income the new income for the user
     */
    public void setIncome(double income) {
        this.income = income;
    }
}
