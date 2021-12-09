package com.generic.plannr.Entities;

import java.time.LocalDateTime;

public abstract class Event {
    private final int userID;
    private final String name;
    private final int priority;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;


     /**
     * Construct an Event, giving them the given
     * name, priority, start date, end date, course.
     *
     * @param userID the userID of the user this event belongs to
     * @param name This Event's name
     * @param priority This Event's priority (0 = high, 1 = mid, 2 = low)
     * @param startDate This Event's start date
     * @param endDate This Event's end date
      */
    public Event(int userID, String name, int priority,
                 LocalDateTime startDate, LocalDateTime endDate) {
        this.userID = userID;
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    /**
     * Returns the userID of the user this event belongs to
     * @return the userID of the Event
     */
    public int getUserID() {return this.userID; }

    /**
     * Returns the name of this event
     * @return the name of the Event
     */
    public String getName() { return this.name; }

    /**
     * Returns the priority (0 = high, 1 = mid, 2 = low) of this event
     * @return the priority of the Event
     */
    public int getPriority() { return this.priority; }

    /**
     * Returns the start date of this event
     * @return the start date of the Event
     */
    public LocalDateTime getStartDate() { return this.startDate; }

    /**
     * Returns the end date of this event
     * @return the end date of the Event
     */
    public LocalDateTime getEndDate() { return this.endDate; }
}
