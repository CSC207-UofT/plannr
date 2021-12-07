package com.generic.plannr.Entities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Represents an assessment event with a start date, end date and duration.
 */
public class Assessment extends Event {
    private Duration duration; //duration of the assessment
    private String course; //course the assessment is from

    /**
     * Constructs a com.generic.plannr.Entities.Assessment event, giving it the given name, priority,
     * start date, end date, and course
     *
     * @param name      This com.generic.plannr.Entities.Assessment's name
     * @param priority  This com.generic.plannr.Entities.Assessment's priority
     * @param startDate This com.generic.plannr.Entities.Assessment's start date
     * @param endDate   This com.generic.plannr.Entities.Assessment's end date
     * @param course    This com.generic.plannr.Entities.Assessment's course from which its from.
     */
    public Assessment(String name, int priority, LocalDateTime startDate,
                      LocalDateTime endDate, String course) {
        super(name, priority, startDate, endDate);

        //gets the duration between the start and end date
        this.duration = Duration.between(startDate, endDate);

        this.course = course;
    }

    /**
     * Get duration of this com.generic.plannr.Entities.Assessment
     *
     * @return duration of this com.generic.plannr.Entities.Assessment
     */
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Get the course this assessment is for
     *
     * @return course of this com.generic.plannr.Entities.Assessment event
     */
    public String getCourse() {
        return this.course;
    }

    /**
     * Changes the course of this assessment
     *
     * @param course is the new course name
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Changes the start date, thus also changing the duration, of the assessment
     *
     * @param startDate is the new start date of the assessment
     */
    @Override
    public void setStartDate(LocalDateTime startDate) {
        super.setStartDate(startDate);
        this.duration = Duration.between(startDate, this.getEndDate());
    }

    /**
     * Changes the end date, thus also changing the duration, of the assessment
     *
     * @param endDate is the new end date of the assessment
     */
    @Override
    public void setEndDate(LocalDateTime endDate) {
        super.setEndDate(endDate);
        this.duration = Duration.between(this.getStartDate(), endDate);
    }

    /**
     * toString method
     *
     * @return a description of this com.generic.plannr.Entities.Assessment event.
     */
    @SuppressLint("DefaultLocale")
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
        return String.format("Assessment (%s priority): %s from %s starts " +
                        "on %s and ends on %s with a duration of %d hours and %d minutes",
                strPriority,
                this.getName(),
                this.getCourse(),
                this.getStartDate().format(DATEFORMAT),
                this.getEndDate().format(DATEFORMAT),
                this.getDuration().toHours(),
                this.getDuration().toMinutes() % 60);
    }
}
