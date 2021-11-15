package UseCases;

import Entities.Deadline;
import Entities.Event;
import Entities.Expenses;
import Entities.User;
import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserManager {
    private final User u;
    private final ExpensesManager expManager;

    public UserManager(String name, ArrayList<String> courses, String school) {
        u = createUser(name, courses, school);
        this.expManager = new ExpensesManager();
    }

    public User createUser(String name, ArrayList<String> courses, String school) {
        return new User(name, courses, school);
    }

    public void addDeadlineEvent(String eventName, int priority, LocalDateTime endDate, String courseName) {
        u.getEventList().add(new Deadline(eventName, priority, endDate, courseName));
    }

    public ArrayList<Event> viewEventList() {
        return u.getEventList();
    }

    public ArrayList<String> viewCourses() {
        return u.getCourses();
    }

    /**
     * Gets the user's expenses
     * @return an ArrayList of Expenses objects
     */
    public ArrayList<Expenses> getExpenses() {
        return u.getExpensesList();
    }

    /**
     * Calculates and returns the user's current balance
     * @return the current balance of user
     */
    public double calculateBalance() {
        double balance = this.u.getIncome();

        for (Expenses e : this.u.getExpensesList()) {
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
        u.getExpensesList().add(expManager.createExpense(name, value));
    }

    @NonNull
    @Override
    public String toString() {
        return "bob";
    }
}
