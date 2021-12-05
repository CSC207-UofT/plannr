package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.User;

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
     * @param event  The Event to be inserted
     * @param userID The ID of the user
     */
    void saveToDatabase(final Event event, final int userID);

    /**
     * Get the Event associated with id eventID currently stored
     * in the database
     *
     * @param eventID the id of the Event we want to return
     *
     * @return the Event with id eventID
     */
    Event getByID(final int eventID);

    /**
     * Get the list of Events currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     *
     * @return a list of all events stored in the database for the user
     */
    List<Event> getAllEvents(int userID);

    /**
     * Get the list of Events that start at date currently stored in the
     * database for user with user id userID
     *
     * @param userID the user's id
     * @param date the start date of the events to be returned
     *
     * @return a list of all events that start at date stored in the
     * database for the user
     */
    List<Event> getEventsByDate(LocalDate date, int userID);
}
