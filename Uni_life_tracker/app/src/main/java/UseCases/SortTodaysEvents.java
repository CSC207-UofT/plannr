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
     * @param userManager is the user manager of the user who's events we're getting.
     * @return the list of today's events sorted by date in descending order.
     */
    @NonNull
    public static ArrayList<Event> sortByDate(UserManager userManager) {
        ArrayList<Event> eventsByDate = GetTodaysEvents.getTodaysEvents(userManager);
        eventsByDate.sort(new EventDateComparator());
        return eventsByDate;
    }

    /**
     * Sorts list of today's events from user by priority.
     * @param userManager is the user manager of the user who's events we're getting.
     * @return the list of today's events sorted by priority in descending order.
     */
    @NonNull
    public static ArrayList<Event> sortByPriority(UserManager userManager) {
        ArrayList<Event> eventsByDate = GetTodaysEvents.getTodaysEvents(userManager);
        eventsByDate.sort(new EventPriorityComparator());
        return eventsByDate;
    }
}
