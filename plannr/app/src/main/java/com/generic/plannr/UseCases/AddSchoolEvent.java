package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Gateways.EventGatewayInterface;

import java.time.LocalDateTime;

/**
 * Adds a school event to a user's event lists
 */
public class AddSchoolEvent {
    private static EventList eventList;
    private static EventGatewayInterface eg;

    /**
     * Sets up AddSchoolEvent with the current user's list of events
     *
     * @param events       the current user's list of events
     * @param eventGateway the gateway to the event database table
     */
    public AddSchoolEvent(EventList events, EventGatewayInterface eventGateway) {
        eventList = events;
        eg = eventGateway;
    }

    /**
     * Creates and adds a school event to a user's list of events
     *
     * @param userID    the ID of the user that has this event
     * @param eventType the type of event to be created and added ("deadline" or "assessment")
     * @param name      the name of the event to be created
     * @param priority  the priority of the event (0 = high, 1 = mid, 2 = low)
     * @param startDate the start date-time of the event
     * @param endDate   the end date-time of the event
     * @param course    the course the event is for
     */
    public static void addSchoolEvent(int userID, String eventType,
                                      String name, int priority, LocalDateTime startDate,
                                      LocalDateTime endDate, String course) {
        SchoolEvent event = new SchoolEvent(userID, eventType, name, priority, startDate,
                endDate, course);
        eventList.add(event);
        eg.saveToDatabase(event, userID);
    }

    /**
     * Creates and adds a school event to a user's list of events
     *
     * @param eventType the type of event to be created and added ("class" or "study session")
     * @param name      the name of the event to be created
     * @param priority  the priority of the event (0 = high, 1 = mid, 2 = low)
     * @param startDate the start date-time of the event
     * @param endDate   the end date-time of the event
     * @param course    the course the event is for
     * @param location  the location the event is taking place
     */
    public static void addSchoolEvent(int userID, String eventType,
                                      String name, int priority, LocalDateTime startDate,
                                      LocalDateTime endDate, String course, String location) {
        SchoolEvent event = new SchoolEvent(userID, eventType, name, priority, startDate,
                endDate, course, location);
        eventList.add(event);
        eg.saveToDatabase(event, userID);
    }
}
