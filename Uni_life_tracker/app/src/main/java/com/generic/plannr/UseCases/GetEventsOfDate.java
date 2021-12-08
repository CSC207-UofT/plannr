package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

import com.generic.plannr.Entities.SchoolEvent;

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
    public static ArrayList<SchoolEvent> getEventsOfDate(ArrayList<SchoolEvent> events, LocalDate selectedDate) {
        ArrayList<SchoolEvent> selectedDatesEvents = new ArrayList<>();
        for (SchoolEvent e : events) {
            if (e.getStartDate().toLocalDate().isEqual(selectedDate)) {
                selectedDatesEvents.add(e);
            }
        }
        return selectedDatesEvents;
    }
}
