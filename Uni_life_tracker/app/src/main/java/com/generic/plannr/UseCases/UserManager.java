package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.Expense;
import com.generic.plannr.Entities.User;

import java.util.ArrayList;

/**
 * Represents a user manager
 */
public class UserManager {
    private final User u;
    private final ExpensesManager expManager;

    /**
     * Constructs a user manager and creates a new user
     *
     * @param name      is the user's name
     * @param email     is the user's email
     * @param password  is the user's password
     */
    public UserManager(String name, String email, String password) {
        u = createUser(name, email, password);
        this.expManager = new ExpensesManager();
    }

    /**
     * Creates a user
     *
     * @param name      is the user's name
     * @param email     is the user's email
     * @param password  is the user's password
     * @return the created user
     */
    public User createUser(String name, String email, String password) {
        return new User(name, email, password);
    }

    /**
     * Gets the User's list of events
     *
     * @return the user's list of events
     */
    public ArrayList<Event> viewEventList() {
        return u.getEventList();
    }

    /**
     * Gets the User's list of courses
     *
     * @return the User's list of courses
     */
    public ArrayList<String> viewCourses() {
        return u.getCourses();
    }

    /**
     * Gets the user's expenses
     *
     * @return an ArrayList of Expenses objects
     */
    public ArrayList<Expense> getExpenses() {
        return u.getExpenseList();
    }

    /**
     * Changes the User's name
     *
     * @param name is the new name of User
     */
    public void changeUsersName(String name) {
        u.setName(name);
    }

    /**
     * Adds an event to User's event list
     *
     * @param event is the event to be added to the user's list
     */
    public void addEventToUsersList(Event event) {
        u.getEventList().add(event);
    }

    /**
     * Adds a course to User's course list
     *
     * @param course is the String of the course's name to be added to the user's list
     */
    public void addCourseToUsersList(String course) {
        u.getCourses().add(course);
    }

    /**
     * Calculates and returns the user's current balance
     *
     * @return the current balance of user
     */
    public double calculateBalance() {
        double balance = this.u.getIncome();

        for (Expense e : this.u.getExpenseList()) {
            balance -= expManager.getValue(e);
        }

        return balance;
    }

    /**
     * This method will return the User
     *
     * @return the User object
     */
    public User getUser() {
        return u;
    }

    /**
     * This method will return user's name
     *
     * @return a String variable containing user's name
     */
    public String getUsersName() {
        return u.getName();
    }

    /**
     * Adds a new expense to the user's expensesList
     *
     * @param value value of the new expense
     * @param name  name of the new expense
     */
    public void addExpense(double value, String name) {
        u.getExpenseList().add(expManager.createExpense(name, value));
    }
}
