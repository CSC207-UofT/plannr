package UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

import Entities.Event;
import Entities.User;

/**
 * Gets the list of events of the current day
 */
public class GetTodaysEvents {

    private final ArrayList<Event> todaysEvents = new ArrayList<>();

    /**
     * Gets a list of events of the current day from the user's list of events
     * @param user is the user who's list of events we're looking through
     * @return the list of today's events of the user
     */
    public ArrayList<Event> getTodaysEvents(@NonNull User user) {
        for (Event e: user.getEventList()) { //look through user's events
            //if the event's date is the current day, then add it to list of today's events
            if (e.getStartDate().toLocalDate().isEqual(LocalDate.now())) {
                todaysEvents.add(e);
            }
        }
        return todaysEvents;
    }

}
