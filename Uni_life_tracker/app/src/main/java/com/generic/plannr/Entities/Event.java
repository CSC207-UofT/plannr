package com.generic.plannr.Entities;

import java.time.LocalDateTime;

public abstract class Event {
    private final int userID;
    private final String eventType;
    private final String name;
    private final int priority;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Event(int userID, String eventType, String name, int priority,
                 LocalDateTime startDate, LocalDateTime endDate) {
        this.userID = userID;
        this.eventType = eventType;
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public int getUserID() {return this.userID; }

    public String getEventType() { return this.eventType; }

    public String getName() { return this.name; }

    public int getPriority() { return this.priority; }

    public LocalDateTime getStartDate() { return this.startDate; }

    public LocalDateTime getEndDate() { return this.endDate; }
}
