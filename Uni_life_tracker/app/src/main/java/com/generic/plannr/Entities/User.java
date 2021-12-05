package com.generic.plannr.Entities;

import java.util.ArrayList;

public class User {
    private String name;
    private final ArrayList<String> courses;
    private final ArrayList<Event> eventList;
    private final ArrayList<Expense> expenseList;
    private double income;

    /**
     * Construct a user, giving them the given name,
     * courses, and setting an empty events list.
     *
     * @param name    The user's name
     * @param courses The user's course list
     */
    public User(String name, ArrayList<String> courses) {
        this.name = name;
        this.courses = new ArrayList<>();
        this.courses.addAll(courses);
        this.eventList = new ArrayList<>();
        this.income = 0.0;
        this.expenseList = new ArrayList<>();
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
    public ArrayList<Event> getEventList() {
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
