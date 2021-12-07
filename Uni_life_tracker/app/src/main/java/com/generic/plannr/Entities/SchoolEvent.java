package com.generic.plannr.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public SchoolEvent(String eventType, String name, int priority, LocalDateTime startDate,
                       LocalDateTime endDate, String course) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
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
    public SchoolEvent(String eventType, String name, int priority, LocalDateTime startDate,
                       LocalDateTime endDate, String course, String location) {
        this (eventType, name, priority, startDate, endDate, course);
        this.location = location;
    }

    /**
     * Gets the event type of SchoolEvent ("assessment", "deadline", "class", "study session")
     * @return the name of the event type of SchoolEvent
     */
    public String getEventType() { return this.eventType; }

    /**
     * Gets the name of the event
     * @return the name of the com.generic.plannr.Entities.Event as a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the priority of event
     * @return the priority of event as an int
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Gets the start date of event
     * @return the start date of event as a Date object
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }
    
    /**
     * Gets the end date of event
     * @return the end date of event as a Date object
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
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
    public String getLocation() { return this.location; }

}