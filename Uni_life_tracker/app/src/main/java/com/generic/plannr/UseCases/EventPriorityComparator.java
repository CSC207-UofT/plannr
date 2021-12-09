package com.generic.plannr.UseCases;

import androidx.annotation.NonNull;

import java.util.Comparator;

import com.generic.plannr.Entities.SchoolEvent;

/**
 * A comparator to compare events by their priority.
 */
public class EventPriorityComparator implements Comparator<SchoolEvent> {
    /**
     * Compares two events by their priority.
     *
     * @param e1 represents an event
     * @param e2 represents the other event
     * @return -1 if e1 has higher priority, 0 if e1 and e2 have the same priority, and
     * 1 if e1 has lower priority.
     */
    public int compare(@NonNull SchoolEvent e1, @NonNull SchoolEvent e2) {
        return Integer.compare(e1.getPriority(), e2.getPriority());
    }
}
