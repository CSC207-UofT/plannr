package Entities;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
import Entities.Event;
import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Work extends Event {


    private String location;
    DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

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
        return String.format("Work (%s priority): Work (%s) in %s starts at %s and ends at %s",
                strPriority,
                this.getName(),
                this.getLocation(),
                this.getStartDate().format(customFormat),
                this.getEndDate().format(customFormat));
    }
}