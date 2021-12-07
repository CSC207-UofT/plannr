package com.generic.plannr.Entities;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

/**
 * Represents a class (lecture/tutorial) with its course and location.
 */
public class Class extends Event {

    private String location;
    private String course;

    /**
     * Construct a com.generic.plannr.Entities.Class event, giving it the given name,
     * priority, start date, end date, the course code, and
     * the location
     *
     * @param name      The com.generic.plannr.Entities.Class's name
     * @param priority  The com.generic.plannr.Entities.Class's priority
     * @param startDate The com.generic.plannr.Entities.Class's start date
     * @param endDate   The com.generic.plannr.Entities.Class's end date
     * @param course    The com.generic.plannr.Entities.Class's course code
     * @param location  The com.generic.plannr.Entities.Class's location
     */
    public Class(String name, int priority, LocalDateTime startDate, LocalDateTime endDate,
                 String course, String location) {
        super(name, priority, startDate, endDate);
        this.location = location;
        this.course = course;
    }

    /**
     * Gets the course of the com.generic.plannr.Entities.Class event
     *
     * @return the course of the com.generic.plannr.Entities.Class event as a String object
     */
    public String getCourse() {
        return this.course;
    }

    /**
     * Gets the location of the com.generic.plannr.Entities.Class event
     *
     * @return the location of the com.generic.plannr.Entities.Class event as a String object
     */
    public String getLocation() {
        return this.location;
    }


    /**
     * Sets the course of the com.generic.plannr.Entities.Class event
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Sets the location of the com.generic.plannr.Entities.Class event
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * toString method
     *
     * @return a String that describes the com.generic.plannr.Entities.Class event
     */
    @NonNull
    @Override
    public String toString() {
        String strPriority;
        if (this.getPriority() == 0) {
            strPriority = "high";
        } else if (this.getPriority() == 1) {
            strPriority = "mid";
        } else {
            strPriority = "low";
        }
        return String.format("Class (%s priority): The class %s (%s) in %s starts at %s and ends at %s",
                strPriority,
                this.getName(),
                this.getCourse(),
                this.getLocation(),
                this.getStartDate().format(DATEFORMAT),
                this.getEndDate().format(DATEFORMAT));

    }
}
