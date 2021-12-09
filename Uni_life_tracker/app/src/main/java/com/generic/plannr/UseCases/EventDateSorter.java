package com.generic.plannr.UseCases;

/**
 * Sorts a list of events by date
 */
public class EventDateSorter extends EventSorter {

    /**
     * Sets up EventDateSorter with its appropriate EventDateComparator
     * @param edc the Event date comparator
     */
    public EventDateSorter(EventDateComparator edc) { super(edc); }
}
