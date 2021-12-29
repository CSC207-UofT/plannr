package com.generic.plannr.UseCases;

/**
 * Sorts a list of events by priority
 */
public class EventPrioritySorter extends EventSorter {

    /**
     * Sets up EventPrioritySorter with its appropriate EventPriorityComparator
     * @param epc the Event date comparator
     */
    public EventPrioritySorter(EventPriorityComparator epc) { super(epc); }

}
