/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
//Note for generic name: Change to LocalDate!

import java.time.LocalDateTime;

public class Class extends Event {

    private String location;
    private String course;

    /**
     * Construct a Class event, giving it the given name,
     * priority, start date, end date, the course code,
     * the location, and a note.
     *
     * @param name      The Class's name
     * @param priority  The Class's priority
     * @param startDate The Class's start date
     * @param endDate   The Class's end date
     * @param course    The Class's course code
     * @param location  The Class's location
     */
    public Class(String name, int priority, LocalDateTime startDate, LocalDateTime endDate,
                 String course, String location) {
        super(name, priority, startDate, endDate);
        this.location = location;
        this.course = course;
    }

    /**
     * Gets the course of the Class event
     * @return the course of the Class event as a String object
     */
    public String getCourse() { return this.course; }

    /**
     * Gets the location of the Class event
     * @return the location of the Class event as a String object
     */
    public String getLocation() { return this.location; }

    /**
     * toString method
     * @return a String that describes the Class event
     */
    @Override
    public String toString() { return super.getName() + ": " + this.getCourse(); }
}