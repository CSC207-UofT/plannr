package com.generic.plannr.Entities;

import java.time.LocalDateTime;

/**
 * Represents a school event with a start and end date and priority.
 */
public class SchoolEvent extends Event {
    private final String eventType;
    private final String course;
    private String location;


    /**
     * Construct a com.generic.plannr.Entities.SchoolEvent event, giving them the given
     * name, priority, start date, end date, course.
     *
     * @param userID the userID of the user whose events belong to
     * @param eventType This SchoolEvent's event type ("assessment",
     *                  "deadline", "class", "study session")
     * @param name This SchoolEvent's name
     * @param priority This SchoolEvent's priority (0 = high, 1 = mid, 2 = low)
     * @param startDate This SchoolEvent's start date
     * @param endDate This SchoolEvent's end date
     * @param course This SchoolEvent's course
     */
    public SchoolEvent(int userID, String eventType, String name, int priority, LocalDateTime startDate,
                       LocalDateTime endDate, String course) {
        super(userID, name, priority, startDate, endDate);
        this.eventType = eventType;
        this.course = course;
    }

    /**
     * Construct a com.generic.plannr.Entities.SchoolEvent event, giving them the given
     * name, priority, start date, end date, course.
     *
     * @param eventType This SchoolEvent's event type ("assessment",
     *                  "deadline", "class", "study session")
     * @param name This SchoolEvent's name
     * @param priority This SchoolEvent's priority
     *                 (0 = high, 1 = mid, 2 = low)
     * @param startDate This SchoolEvent's start date
     * @param endDate This SchoolEvent's end date
     * @param course This SchoolEvent's course
     * @param location This SchoolEvent's location
     */
    public SchoolEvent(int userID, String eventType, String name, int priority, LocalDateTime startDate,
                       LocalDateTime endDate, String course, String location) {
        this (userID, eventType, name, priority, startDate, endDate, course);
        this.location = location;
    }

    /**
     * Returns the event type of this event
     * @return the event type of the Event
     */
    public String getEventType() { return this.eventType; }

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