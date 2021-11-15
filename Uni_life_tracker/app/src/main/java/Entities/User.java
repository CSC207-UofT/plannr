package Entities;

import java.util.ArrayList;

/**
 * Represents a user with a name, their school, list of courses, events list and expenses list
 */
public class User {
    private String name;
    private final ArrayList<String> courses;
    private String school;
    private final ArrayList<Event> eventList; //list of events
    private final ArrayList<Expense> expenseList; //list of expenses

    /**
     * Construct a Entities.User, giving them the given name,
     * courses, school, and setting an empty events list and expenses list.
     *
     * @param name      The Entities.User's name
     * @param courses   The Entities.User's course list
     * @param school    The Entities.User's school
     */
    public User(String name, ArrayList<String> courses, String school) {
        this.name = name;
        this.courses = courses;
        this.school = school;
        this.eventList = new ArrayList<>();
        this.expenseList = new ArrayList<>();
    }

    /**
     * getter method for name
     * @return a String that describes the Entities.User's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the school name of the user
     * @return a String of the user's school's name
     */
    public String getSchool() { return this.school; }

    /**
     * Gets the list of courses from the user
     * @return an ArrayList that describes the Entities.User's current course list
     */
    public ArrayList<String> getCourses() {
        return this.courses;
    }

    /**
     * Gets the list of events from the user
     * @return an ArrayList that describes all the user's events
     */
    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    /**
     * Gets the list of expenses of the user
     * @return an ArrayList of all the user's expenses
     */
    public ArrayList<Expense> getExpenseList() { return this.expenseList; }

    /**
     * Changes the name of the user
     * @param name is the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the name of the school of the user
     * @param school is the new school name of the user
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Adds a course to the user's list of courses
     * @param course is the course to be added to the user's list of courses
     */
    public void addCourse (String course) { this.courses.add(course); }

    /**
     * Adds an event to the user's list of events
     * @param event is the event to be added to the user's list of events
     */
    public void addEvent (Event event) { this.eventList.add(event); }

    /**
     * Adds an expense to the user's list of expenses
     * @param expense is the event to be added to the user's list of expenses
     */
    public void addExpense (Expense expense) { this.expenseList.add(expense); }
}
