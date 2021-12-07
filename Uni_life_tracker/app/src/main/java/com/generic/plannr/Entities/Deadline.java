package com.generic.plannr.Entities;

import com.generic.plannr.Entities.Event;
import androidx.annotation.NonNull;

import java.time.LocalDateTime;


/**
 * Represents an event that is a deadline with a due date.
 */
public class Deadline extends Event {
    private String name;
    private int priority;
    private LocalDateTime endDate;
    private String course; //course the deadline is from

    /**
     * Construct com.generic.plannr.Entities.Deadline event, giving them the given
     * name, priority, end date, and course name.
     *
     * @param name       This com.generic.plannr.Entities.Deadline's name
     * @param priority   This com.generic.plannr.Entities.Deadline's priority
     * @param endDate    This com.generic.plannr.Entities.Deadline's due date
     * @param courseName This com.generic.plannr.Entities.Deadline's course which it's from
     */
    public Deadline(String name, int priority, LocalDateTime endDate, String courseName) {
        super(name, priority, endDate, endDate);
        this.course = courseName;
    }

    /**
     * Gets the course this deadline is for
     *
     * @return the course of this deadline event
     */
    public String getCourse() {
        return this.course;
    }

    /**
     * Changes the course name this deadline is for
     *
     * @param course is the new name of the course for this deadline event
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * toString method
     *
     * @return a String that describes this com.generic.plannr.Entities.Deadline event.
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

        return String.format("Deadline (%s priority): The assignment %s from %s is due on %s",
                strPriority, this.getName(), this.getCourse(), this.getEndDate().format(DATEFORMAT));
    }
}
