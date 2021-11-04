package Entities;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/


import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Assessment extends Event {
    private long duration; //duration of the assessment
    private String course; //course the assessment is from

    /**
     * Constructs a Entities.Assessment event, giving it the given name, priority,
     * start date, end date, and course
     *
     * @param name  This Entities.Assessment's name
     * @param priority  This Entities.Assessment's priority
     * @param startDate  This Entities.Assessment's start date
     * @param endDate  This Entities.Assessment's end date
     * @param course  This Entities.Assessment's course from which its from.
     */
    public Assessment(String name, int priority, LocalDateTime startDate, LocalDateTime endDate, String course) {
        super(name, priority, startDate, endDate);
        
        //figure out duration
        long tempDuration = endDate.getMinute() - startDate.getMinute();
        this.duration = TimeUnit.MILLISECONDS.toHours(tempDuration);
        
        this.course = course;
    }

    /**
     * Get duration of this Entities.Assessment
     * @return duration of this Entities.Assessment
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * toString method
     * @return a description of this Entities.Assessment event.
     */
    @NonNull
    @Override
    public String toString() {
        return "This assessment from " + this.course + " starts on " + this.getStartDate()
        + " and ends on " + this.getEndDate() + " and lasts " + this.duration + " hours.";
    }
}
