package Entities;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
//Note for generic name: Change to LocalDate!

import Entities.Event;

import java.time.LocalDateTime;

public class Class extends Event {

    private String location;
    private String course;

    /**
     * Construct a Entities.Class event, giving it the given name,
     * priority, start date, end date, the course code,
     * the location, and a note.
     *
     * @param name      The Entities.Class's name
     * @param priority  The Entities.Class's priority
     * @param startDate The Entities.Class's start date
     * @param endDate   The Entities.Class's end date
     * @param course    The Entities.Class's course code
     * @param location  The Entities.Class's location
     */
    public Class(String name, int priority, LocalDateTime startDate, LocalDateTime endDate,
                 String course, String location) {
        super(name, priority, startDate, endDate);
        this.location = location;
        this.course = course;
    }

    /**
     * Gets the course of the Entities.Class event
     * @return the course of the Entities.Class event as a String object
     */
    public String getCourse() { return this.course; }

    /**
     * Gets the location of the Entities.Class event
     * @return the location of the Entities.Class event as a String object
     */
    public String getLocation() { return this.location; }

    /**
     * toString method
     * @return a String that describes the Entities.Class event
     */
    @Override
    public String toString() { return super.getName() + ": " + this.getCourse(); }
}