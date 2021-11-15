package UseCases;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import Entities.Event;
import Entities.User;

/**
 * Sorts the list of events taking place today by date or by priority.
 */
public class SortTodaysEvents {

    /**
     * Sorts list of today's events from user by date.
     * @param user is the user who's list we'll access to get today's events.
     * @return the list of today's events sorted by date in descending order.
     */
    @NonNull
    public static ArrayList<Event> sortByDate(User user) {
        ArrayList<Event> eventsByDate = GetTodaysEvents.getTodaysEvents(user);
        eventsByDate.sort(new EventDateComparator());
        return eventsByDate;
    }

    /**
     * Sorts list of today's events from user by priority.
     * @param user is the user who's list we'll access to get today's events.
     * @return the list of today's events sorted by priority in descending order.
     */
    @NonNull
    public static ArrayList<Event> sortByPriority(User user) {
        ArrayList<Event> eventsByDate = GetTodaysEvents.getTodaysEvents(user);
        eventsByDate.sort(new EventPriorityComparator());
        return eventsByDate;
    }
}
