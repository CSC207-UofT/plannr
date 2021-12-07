package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.generic.plannr.Entities.Assessment;
import com.generic.plannr.Entities.Deadline;
import com.generic.plannr.Entities.StudySession;
import com.generic.plannr.Entities.Class;

/**
 * Adds an event to a user's event lists
 */
public class AddEvent {

    /**
     * Adds a Deadline event to a user's event list
     *
     * @param userManager is the user manager of the user
     * @param name        is the name of this deadline event
     * @param priority    is the priority of this deadline event
     * @param endDate     is the due date of this deadline event
     * @param course      is the course this deadline is for
     */
    public static void addDeadline(@NonNull UserManager userManager, String name, int priority,
                                   LocalDateTime endDate, String course) {

        userManager.addEventToUsersList(new Deadline(name, priority, endDate, course));
    }

    /**
     * Adds an Assessment event to a user's event list
     *
     * @param userManager is the user manager of the user
     * @param name        is the name of the assessment
     * @param priority    is the priority of the assessment
     * @param startDate   is the start date of the assessment
     * @param endDate     is the end date of the assessment
     * @param course      is the course this assessment is for
     */
    public static void addAssessment(@NonNull UserManager userManager, String name,
                                     int priority, LocalDateTime startDate,
                                     LocalDateTime endDate, String course) {

        userManager.addEventToUsersList(new Assessment(name, priority, startDate, endDate, course));
    }

    /**
     * Adds a StudySession event to a user's event list
     *
     * @param userManager  is the user manager of the user
     * @param name         is the name of the study session event
     * @param priority     is the priority of this study session event
     * @param startDate    is the start date of the study session
     * @param endDate      is the end date of the study session
     * @param course       is the course this study session is for
     * @param location     is the location, or where, this study session is taking place
     * @param participants is the list of participants for this study session
     */
    public static void addStudySession(@NonNull UserManager userManager, String name,
                                       int priority, LocalDateTime startDate, LocalDateTime endDate,
                                       String course, String location,
                                       ArrayList<String> participants) {

        userManager.addEventToUsersList(new StudySession(name, priority, startDate, endDate,
                course, location, participants));
    }

    /**
     * Adds a Class event to a user's event list
     *
     * @param userManager is the user manager of the user
     * @param name        is the name of this class event
     * @param priority    is the priority of this class event
     * @param startDate   is the start date of the class
     * @param endDate     is the end date of this class
     * @param course      is the course this class event is for
     * @param location    is the location, or where, this class is taking place
     */
    public static void addClass(@NonNull UserManager userManager, String name, int priority,
                                LocalDateTime startDate, LocalDateTime endDate, String course,
                                String location) {
        userManager.addEventToUsersList(new Class(name, priority, startDate,
                endDate, course, location));
    }
}
