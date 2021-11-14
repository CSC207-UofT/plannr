package UseCases;

import androidx.annotation.NonNull;

import java.util.Comparator;

import Entities.Event;

public class EventPriorityComparator implements Comparator<Event> {
    public int compare(@NonNull Event e1, @NonNull Event e2) {
        return Integer.compare(e1.getPriority(), e2.getPriority());
    }
}
