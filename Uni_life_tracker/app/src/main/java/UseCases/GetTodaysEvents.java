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

    /**
     * Gets a list of events of the current day from the user's list of events
     * @param userManager is the user manager of the user who's list of events we're looking through
     * @return the list of today's events of the user
     */
    public static ArrayList<Event> getTodaysEvents(@NonNull UserManager userManager) {
        ArrayList<Event> todaysEvents = new ArrayList<>();
        for (Event e: userManager.viewEventList()) { //look through user's events
            //if the event's date is the current day, then add it to list of today's events
            if (e.getStartDate().toLocalDate().isEqual(LocalDate.now())) {
                todaysEvents.add(e);
            }
        }
        return todaysEvents;
    }

}
