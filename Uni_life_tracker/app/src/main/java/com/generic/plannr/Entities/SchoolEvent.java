package com.generic.plannr.Entities;

import java.time.LocalDateTime;

/**
 * Represents a school event with a start and end date and priority.
 */
public class SchoolEvent {
    private String eventType;
    private String name;
    private int priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String course;
    private String location;


    /**
     * Construct an com.generic.plannr.Entities.SchoolEvent event, giving them the given
     * name, priority, start date, end date, course.
     *
     * @param eventType This com.generic.plannr.Entities.SchoolEvent's event type ("assessment",
     *                  "deadline", "class", "study session")
     * @param name This com.generic.plannr.Entities.SchoolEvent's name
     * @param priority This com.generic.plannr.Entities.SchoolEvent's priority
     *                 (0 = high, 1 = mid, 2 = low)
     * @param startDate This com.generic.plannr.Entities.SchoolEvent's start date
     * @param endDate This com.generic.plannr.Entities.SchoolEvent's end date
     * @param course This com.generic.plannr.Entities.SchoolEvent's course
     */
    public SchoolEvent(int userID, String eventType, String name, int priority, LocalDateTime startDate,
                       LocalDateTime endDate, String course) {
        this.eventType = eventType;
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.course = course;
    }

    /**
     * Construct an com.generic.plannr.Entities.SchoolEvent event, giving them the given
     * name, priority, start date, end date, course.
     *
     * @param eventType This com.generic.plannr.Entities.SchoolEvent's event type ("assessment",
     *                  "deadline", "class", "study session")
     * @param name This com.generic.plannr.Entities.SchoolEvent's name
     * @param priority This com.generic.plannr.Entities.SchoolEvent's priority
     *                 (0 = high, 1 = mid, 2 = low)
     * @param startDate This com.generic.plannr.Entities.SchoolEvent's start date
     * @param endDate This com.generic.plannr.Entities.SchoolEvent's end date
     * @param course This com.generic.plannr.Entities.SchoolEvent's course
     * @param location This com.generic.plannr.Entities.SchoolEvent's location
     */
    public SchoolEvent(int userID, String eventType, String name, int priority, LocalDateTime startDate,
                       LocalDateTime endDate, String course, String location) {
        this (userID, eventType, name, priority, startDate, endDate, course);
        this.location = location;
    }

    /**
     * Gets the course of SchoolEvent
     * @return the name of the course of SchoolEvent
     */
    public String getCourse() { return this.course; }

    /**
     * Gets the location of SchoolEvent
     * @return the name of the location of SchoolEvent
     */
    public String getLocation() {
        if (this.location == null) {
            return "N/A";
        } else {
            return this.location;
        }

    }

}