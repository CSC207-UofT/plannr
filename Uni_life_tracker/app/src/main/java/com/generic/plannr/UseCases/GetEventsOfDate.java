package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.SchoolEvent;

/**
 * Gets the events of the selected date
 */
public class GetEventsOfDate {
    private static EventList eventList;

    /**
     * Sets up GetEventsOfDate with the current user's list of events
     * @param events the current user's list of events
     */
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
    public static ArrayList<SchoolEvent> getEventsOfDate(LocalDate selectedDate) {
        ArrayList<SchoolEvent> eventsOfDate = new ArrayList<>();
        for (SchoolEvent e : eventList.getEventList()) {
            if (e.getStartDate().toLocalDate().isEqual(selectedDate)) {
                eventsOfDate.add(e);
            }
        }
        return eventsOfDate;
    }
}
