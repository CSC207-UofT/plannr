package UseCases;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

import Entities.Event;

/**
 * Gets the events of the selected date
 */
public class GetEventsOfDate {

    /**
     * Gets a list of events taking place on a selected date from the user's list of events
     * @param userManager is the user manager of the user who's list of events we're looking through
     * @param selectedDate is the selected date we want all the events for
     * @return a list of the events taking place during the selected date
     */
    @NonNull
    public static ArrayList<Event> getEventsOfDate(UserManager userManager, LocalDate selectedDate) {
        ArrayList<Event> selectedDatesEvents = new ArrayList<>();
        for (Event e: userManager.viewEventList()) {
            if (e.getStartDate().toLocalDate().isEqual(selectedDate)) {
                selectedDatesEvents.add(e);
            }
        }
        return selectedDatesEvents;
    }
}
