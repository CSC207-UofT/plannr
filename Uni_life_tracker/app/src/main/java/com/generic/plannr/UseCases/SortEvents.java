package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import com.generic.plannr.Entities.SchoolEvent;

/**
 * Sorts the list of events taking place today by date or by priority.
 */
public class SortEvents {

    /**
     * Sorts list of today's events from user by date.
     *
     * @param events is the list of events to be sorted
     * @return the list of today's events sorted by date in descending order.
     */
    @NonNull
    public static ArrayList<SchoolEvent> sortByDate(ArrayList<SchoolEvent> events) {
        events.sort(new EventDateComparator());
        return events;
    }

    /**
     * Sorts list of today's events from user by priority.
     *
     * @param events is the list of events to be sorted
     * @return the list of today's events sorted by priority in descending order.
     */
    @NonNull
    public static ArrayList<SchoolEvent> sortByPriority(ArrayList<SchoolEvent> events) {
        events.sort(new EventPriorityComparator());
        return events;
    }

}
