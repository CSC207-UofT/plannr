package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.SchoolEvent;

import java.time.LocalDate;
import java.util.List;

public interface EventGatewayInterface {
    /**
     * Open the database for reading or writing
     */
    void openDatabase();

    /**
     * Insert the given event into the database
     *
     * @param event  The SchoolEvent to be inserted
     * @param userID The ID of the user
     */
    void saveToDatabase(final SchoolEvent event, final int userID);

    /**
     * Get the SchoolEvent associated with id eventID currently stored
     * in the database
     *
     * @param eventID the id of the SchoolEvent we want to return
     *
     * @return the SchoolEvent with id eventID
     */
    SchoolEvent getByID(final int eventID);

    /**
     * Get the list of SchoolEvents currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     *
     * @return a list of all events stored in the database for the user
     */
    List<SchoolEvent> getAllEvents(int userID);

    /**
     * Get the list of SchoolEvents that start at date currently stored in the
     * database for user with user id userID
     *
     * @param userID the user's id
     * @param date the start date of the events to be returned
     *
     * @return a list of all events that start at date stored in the
     * database for the user
     */
    List<SchoolEvent> getEventsByDate(LocalDate date, int userID);
}
