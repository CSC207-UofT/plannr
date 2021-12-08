package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Gateways.EventGatewayInterface;

/**
 * Loads the events from the database of the current user
 * To be used when a user logs in
 */
public class EventLoader {
    private EventGatewayInterface eg;
    private EventList eventList;

    /**
     * Sets up the EventLoader with the event gateway and event list
     * @param eventGateway the gateway to the event database table
     * @param events the list of events for the user
     */
    public EventLoader(EventGatewayInterface eventGateway, EventList events) {
        this.eg = eventGateway;
        this.eventList = events;
    }

    /**
     * Loads the events from the database of the current user
     * @param userID the ID of the current user
     */
    public void loadEvents(int userID) {
        for (Event e: eg.getAllEvents(userID)) {
            eventList.add(e);
        }
    }
}
