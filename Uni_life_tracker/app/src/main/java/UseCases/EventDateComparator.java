package UseCases;

import androidx.annotation.NonNull;

import java.util.Comparator;

import Entities.Event;

/**
 * A comparator to compare events by their date
 */
public class EventDateComparator implements Comparator<Event> {
    /**
     * Compares two events by their date.
     * @param e1 represents an Event
     * @param e2 represents an other Event
     * @return -1 if e1 occurs after e2, 0 if e1 and e2 occur at the same date-time,
     * and 1 if e1 before after e2.
     */
    public int compare(@NonNull Event e1, @NonNull Event e2) {
        return e1.getStartDate().compareTo(e2.getStartDate());
    }
}
