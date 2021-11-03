package Entities;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private int priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, int priority, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Construct an Entities.Event, giving them the given name,
     * priority, and end date.
     *
     * This constructor does not need startDate
     *
     * @param name      The Entities.Event's name
     * @param priority  The Entities.Event's priority
     * @param endDate   The Entities.Event's end date
     */
    public Event(String name, int priority, LocalDateTime endDate) {
        this.name = name;
        this.priority = priority;
        this.endDate = endDate;
    }

    /**
     * Gets the name of the Entities.Event
     * @return the name of the Entities.Event as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the priority of event
     * @return the priority of event as an int
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Gets the start date of event
     * @return the start date of event as a Date object
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    /**
     * Gets the end date of event
     * @return the end date of event as a Date object
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }
}