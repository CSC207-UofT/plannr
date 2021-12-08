package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.SchoolEvent;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Sorts a list of events by an SchoolEvent trait (name, date, priority, etc.)
 */
public abstract class EventSorter {
    private final Comparator<SchoolEvent> comparator;

    /**
     * Sets up the EventSorter with its appropriate Event trait comparator
     * @param comparator the SchoolEvent Comparator
     */
    public EventSorter(Comparator<SchoolEvent> comparator) {
        this.comparator = comparator;
    }

    /**
     * Sorts a list of events by a SchoolEvent trait
     * @param events is the list of SchoolEvent objects to be sorted
     * @return the sorted list of SchoolEvents
     */
    public ArrayList<SchoolEvent> sortEvents (ArrayList<SchoolEvent> events) {
        events.sort(this.comparator);
        return events;
    }
}
