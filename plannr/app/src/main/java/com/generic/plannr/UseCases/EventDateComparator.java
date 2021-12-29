package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.util.Comparator;

import com.generic.plannr.Entities.SchoolEvent;

/**
 * A comparator to compare events by their date
 */
public class EventDateComparator implements Comparator<SchoolEvent> {
    /**
     * Compares two events by their date.
     *
     * @param e1 represents an Event
     * @param e2 represents another Event
     * @return -1 if e1 before after e2, 0 if e1 and e2 occur at the same date-time,
     * and 1 if e1 after e2.
     */
    public int compare(@NonNull SchoolEvent e1, @NonNull SchoolEvent e2) {
        return Integer.compare(e1.getStartDate().compareTo(e2.getStartDate()), 0);
    }
}
