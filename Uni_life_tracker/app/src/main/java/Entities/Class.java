package Entities;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
//Note for generic name: Change to LocalDate!

import Entities.Event;
import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Class extends Event {

    private String location;
    private String course;
    DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    /**
     * Construct a Entities.Class event, giving it the given name,
     * priority, start date, end date, the course code, and
     * the location
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
    @NonNull
    @Override
    public String toString() {
        String strPriority;
        if (this.getPriority() == 0) {
            strPriority = "high";
        }
        else if (this.getPriority() == 1) {
            strPriority = "mid";
        }
        else {
            strPriority = "low";
        }
        return String.format("Class (%s priority): The class %s (%s) in %s starts at %s and ends at %s",
                strPriority,
                this.getName(),
                this.getCourse(),
                this.getLocation(),
                this.getStartDate().format(customFormat),
                this.getEndDate().format(customFormat));

    }
}
