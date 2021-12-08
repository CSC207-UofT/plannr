package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

import com.generic.plannr.Entities.Event;

/**
 * Gets the events of the selected date
 */
public class GetEventsOfDate {
    private static EventList eventList;

    public GetEventsOfDate (EventList events) {
        eventList = events;
    }

    /**
     * Gets a list of events taking place on a selected date from the list of events
     *
     * @param selectedDate is the selected date we want all the events for
     * @return a list of the events taking place during the selected date
     */
    @NonNull
    public static ArrayList<Event> getEventsOfDate(LocalDate selectedDate) {
        ArrayList<Event> eventsOfDate = new ArrayList<>();
        for (Event e : eventList.getEventList()) {
            if (e.getStartDate().toLocalDate().isEqual(selectedDate)) {
                eventsOfDate.add(e);
            }
        }
        return eventsOfDate;
    }
}
