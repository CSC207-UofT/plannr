package Entities;

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
    public Assessment(String name, int priority, LocalDateTime startDate,
                      LocalDateTime endDate, String course) {
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
     * Get the course this assessment is for
     * @return course of this Entities.Assessment event
     */
    public String getCourse() { return this.course; }

    /**
     * Changes the course of this assessment
     * @param course is the new course name
     */
    public void setCourse(String course) { this.course = course; }

    /**
     * toString method
     * @return a description of this Entities.Assessment event.
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
        return String.format("Assessment (%s priority): %s from %s starts " +
                "on %s and ends on %s with a duration of %s",
                strPriority, this.getName(), this.getCourse(), this.getStartDate(),
                this.getEndDate(), this.getDuration());
    }
}
