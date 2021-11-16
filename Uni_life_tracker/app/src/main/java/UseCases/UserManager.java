package UseCases;

import Entities.Deadline;
import Entities.Event;
import Entities.Expense;
import Entities.User;
import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a user manager
 */
public class UserManager {
    private final User u;
    private final ExpensesManager expManager;

    /**
     * Constructs a user manager and creates a new user
     * @param name is the user's name
     * @param courses is the user's list of courses
     * @param school is the user's school
     */
    public UserManager(String name, ArrayList<String> courses, String school) {
        u = createUser(name, courses, school);
        this.expManager = new ExpensesManager();
    }

    /**
     * Creates a user
     * @param name is the user's name
     * @param courses is the user's list of courses
     * @param school is the user's school's name
     * @return the created user
     */
    public User createUser(String name, ArrayList<String> courses, String school) {
        return new User(name, courses, school);
    }

    /**
     * Gets the User's list of events
     * @return the user's list of events
     */
    public ArrayList<Event> viewEventList() {
        return u.getEventList();
    }

    /**
     * Gets the User's list of courses
     * @return the User's list of courses
     */
    public ArrayList<String> viewCourses() {
        return u.getCourses();
    }

    /**
     * Gets the user's expenses
     * @return an ArrayList of Expenses objects
     */
    public ArrayList<Expense> getExpenses() {
        return u.getExpenseList();
    }

    /**
     * Changes the User's name
     * @param name is the new name of User
     */
    public void changeUsersName(String name) {
        u.setName(name);
    }

    /**
     * Changes the User's school
     * @param school is the new name of User's school
     */
    public void changeUsersSchool(String school) { u.setSchool(school);}

    /**
     * Adds an event to User's event list
     * @param event is the event to be added to the user's list
     */
    public void addEventToUsersList (Event event) { u.getEventList().add(event); }

    /**
     * Adds a course to User's course list
     * @param course is the String of the course's name to be added to the user's list
     */
    public void addCourseToUsersList (String course) { u.getCourses().add(course); }

    /**
     * Calculates and returns the user's current balance
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
     * Adds a new expense to the user's expensesList
     * @param value value of the new expense
     * @param name name of the new expense
     */
    public void addExpense(double value, String name) {
        u.getExpenseList().add(expManager.createExpense(name, value));
    }

}
