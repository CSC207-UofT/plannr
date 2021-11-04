package Entities;

import Entities.Event;
import androidx.annotation.NonNull;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


/**
 * Represents an event that is a deadline with a due date.
 */
public class Deadline extends Event {
    private String course; //course the deadline is from
    DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    /**
     * Construct Entities.Deadline event, giving them the given
     * name, priority, end date, and course name.
     *
     * @param name   This Entities.Deadline's name
     * @param priority  This Entities.Deadline's priority
     * @param endDate  This Entities.Deadline's due date
     * @param courseName  This Entities.Deadline's course which it's from
     */
    public Deadline(String name, int priority, LocalDateTime endDate, String courseName) {
        super(name, priority, endDate);
        this.course = courseName;
    }

    public String getCourse() {
        return this.course;
    }

    /**
     * toString method
     * @return a String that describes this Entities.Deadline event.
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
        return String.format("Deadline (%s priority): The assignment %s from %s is due on %s",
                strPriority, this.getName(), this.getCourse(), this.getEndDate().format(customFormat));
    }
}
