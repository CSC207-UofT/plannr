package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.User;

import java.time.LocalDate;
import java.util.List;

public interface EventGatewayInterface {
    void openDatabase();
    void saveToDatabase(final Event event);
    Event getByID(final int userID);
    List<Event> getAllEvents(int userID);
    List<Event> getEventsByDate(LocalDate date, int userID);
}
