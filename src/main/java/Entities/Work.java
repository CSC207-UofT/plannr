package Entities;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
import Entities.Event;

import java.time.LocalDateTime;

public class Work extends Event {


    private String location;

    /**
     * Construct a Entities.Work event, giving it the given name,
     * priority, start date, end date and location.
     *
     * @param name      The Entities.Work's name
     * @param priority  The Entities.Work's priority
     * @param startDate The Entities.Work's start date
     * @param endDate   The Entities.Work's end date
     * @param location  The Entities.Work's location
     */
    public Work(String name, int priority, LocalDateTime startDate, LocalDateTime endDate, String location) {
        super(name, priority, startDate, endDate);
        this.location = location;
    }

    /**
     * Gets the location of the Entities.Work event
     * @return the location of the Entities.Work event as a String object
     */
    public String getLocation() { return this.location; }


    /**
     * toString method
     * @return a String that describes the Entities.Work event
     */
    @Override
    public String toString() { return super.getName(); }
}