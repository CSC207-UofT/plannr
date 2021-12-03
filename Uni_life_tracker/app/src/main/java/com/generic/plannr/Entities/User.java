package com.generic.plannr.Entities;

import java.util.ArrayList;

public class User {
    private String name;
    private final ArrayList<String> courses;
    private String school;
    private final ArrayList<Event> eventList;
    private final ArrayList<Expense> expenseList;
    private double income;

    /**
     * Construct a user, giving them the given name,
     * courses, school, and setting an empty events list.
     *
     * @param name    The user's name
     * @param courses The user's course list
     * @param school  The user's school
     */
    public User(String name, ArrayList<String> courses, String school) {
        this.name = name;
        this.courses = new ArrayList<>();
        this.courses.addAll(courses);
        this.school = school;
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
     * getter method for school
     *
     * @return a String that contains the user's name
     */
    public String getSchool() {
        return this.school;
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

    public void setSchool(String school) {
        this.school = school;
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
