package Entities;

import Entities.Event;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<String> courses;
    private String school;
    private ArrayList<Event> eventList;

    /**
     * Construct a Entities.User, giving them the given name,
     * courses, school, and setting an empty events list.
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
    }

    /**
     * getter method for name
     * @return a String that describes the Entities.User's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for courses
     * @return an ArrayList that describes the Entities.User's current course list
     */
    public ArrayList<String> getCourses() {
        return courses;
    }

    /**
     * getter method for eventList
     * @return an ArrayList that describes all the user's events
     */
    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}