package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

import com.generic.plannr.Entities.Event;

/**
 * Gets the events of the selected date
 */
public class GetEventsOfDate {

    /**
     * Gets a list of events taking place on a selected date from the list of events
     *
     * @param events       is the list of events to look through
     * @param selectedDate is the selected date we want all the events for
     * @return a list of the events taking place during the selected date
     */
    @NonNull
    public static ArrayList<Event> getEventsOfDate(ArrayList<Event> events, LocalDate selectedDate) {
        ArrayList<Event> selectedDatesEvents = new ArrayList<>();
        for (Event e : events) {
            if (e.getStartDate().toLocalDate().isEqual(selectedDate)) {
                selectedDatesEvents.add(e);
            }
        }
        return selectedDatesEvents;
    }
}
