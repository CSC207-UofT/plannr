package com.generic.plannr.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end date and priority.
 */
public class Event {
    private String name;
    private int priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Construct an com.generic.plannr.Entities.Event giving them the given
     * name, priority, start and end date.
     *
     * @param name This com.generic.plannr.Entities.Event's name
     * @param priority This com.generic.plannr.Entities.Event's priority (0 = high, 1 = mid, 2 = low)
     * @param startDate This com.generic.plannr.Entities.Event's start date
     * @param endDate This com.generic.plannr.Entities.Event's end date
     */
    public Event(String name, int priority, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String name, int priority, LocalDateTime endDate) {
        this.name = name;
        this.priority = priority;
        this.endDate = endDate;
    }

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
     * Changes the name of the event
     * @param name is the new name of the event
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the priority (0 = high, 1 = mid, 2 = low) of the event
     * @param priority is the new priority of the event
     */
    public void setPriority(int priority) { this.priority = priority; }

    /**
     * Changes the start date of the event
     * @param startDate is the new start date of the event
     */
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    /**
     * Changes the end date of the event
     * @param endDate is the new end date of the event
     */
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

}