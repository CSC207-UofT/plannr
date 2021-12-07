package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import com.generic.plannr.Entities.SchoolEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Adds an event to a user's event lists
 */
public class AddEvent {

    public static void addSchoolEvent (@NonNull UserManager userManager, String eventType, String name, int priority,
                                       LocalDateTime startDate,
                                       LocalDateTime endDate, String course) {
        userManager.addEventToUsersList(new SchoolEvent(eventType, name, priority, startDate,
                endDate, course));
    }

    public static void addSchoolEvent (@NonNull UserManager userManager, String eventType,
                                       String name, int priority, LocalDateTime startDate,
                                       LocalDateTime endDate, String course, String location) {
        userManager.addEventToUsersList(new SchoolEvent(eventType, name, priority, startDate,
                endDate, course, location));
    }
}
