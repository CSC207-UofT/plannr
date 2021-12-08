package com.generic.plannr.UseCases;


import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.SchoolEvent;

import java.util.ArrayList;

/**
 * A data structure for the current user's list of events
 */
public class EventList {
    private final ArrayList<Event> eventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void add (Event e) {
        eventList.add(e);
    }


    public void clear() {
        eventList.clear();
    }
}
