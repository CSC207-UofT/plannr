package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import com.generic.plannr.Entities.SchoolEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Adds a school event to a user's event lists
 */
public class AddEvent {

    /**
     * Creates and adds a school event to a user's list of events
     *
     * @param userManager manager for user whose list of events were adding a school event
     * @param eventType the type of event to be created and added ("deadline" or "assessment")
     * @param name the name of the event to be created
     * @param priority the priority of the event (0 = high, 1 = mid, 2 = low)
     * @param startDate the start date-time of the event
     * @param endDate the end date-time of the event
     * @param course the course the event is for
     */
    public static void addSchoolEvent (@NonNull UserManager userManager, String eventType,
                                       String name, int priority, LocalDateTime startDate,
                                       LocalDateTime endDate, String course) {
        userManager.addEventToUsersList(new SchoolEvent(eventType, name, priority, startDate,
                endDate, course));
    }

    /**
     * Creates and adds a school event to a user's list of events
     *
     * @param userManager manager for user whose list of events were adding a school event
     * @param eventType the type of event to be created and added ("class" or "study session")
     * @param name the name of the event to be created
     * @param priority the priority of the event (0 = high, 1 = mid, 2 = low)
     * @param startDate the start date-time of the event
     * @param endDate the end date-time of the event
     * @param course the course the event is for
     * @param location the location the event is taking place
     */
    public static void addSchoolEvent (@NonNull UserManager userManager, String eventType,
                                       String name, int priority, LocalDateTime startDate,
                                       LocalDateTime endDate, String course, String location) {
        userManager.addEventToUsersList(new SchoolEvent(eventType, name, priority, startDate,
                endDate, course, location));
    }
}
