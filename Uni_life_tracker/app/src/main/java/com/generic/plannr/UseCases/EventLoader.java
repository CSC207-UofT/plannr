package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Gateways.EventGatewayInterface;

public class EventLoader {
    private EventGatewayInterface eg;
    private EventList eventList;

    public EventLoader(EventGatewayInterface eventGateway, EventList events) {
        this.eg = eventGateway;
        this.eventList = events;
    }

    public void loadEvents(int userID) {
        for (Event e: eg.getAllEvents(userID)) {
            eventList.add(e);
        }
    }
}
