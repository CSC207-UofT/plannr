package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Entities.User;

public class EventManager {
    private static EventManager EM_INSTANCE;

    /**
     * This is a private constructor, implemented to disallow client to use the new keyword
     */
    private EventManager() {
    }

    /**
     * Getter for an instance of EventManager, implemented to make sure there is only one instance
     * of EventManager in our program during runtime
     *
     * @return EventManager object
     */
    public static EventManager getInstance() {
        // Check if AM_INSTANCE is instantiated
        if (EM_INSTANCE == null) {
            EM_INSTANCE = new EventManager();
        }
        return EM_INSTANCE;
    }

    /**
     * Add an com.generic.plannr.Entities.Event
     *
     * @param e    The com.generic.plannr.Entities.Event
     * @param user The com.generic.plannr.Entities.User
     */
    public void addEvent(SchoolEvent e, User user) {
        user.getEventList().add(e);
    }

    /**
     * Delete an com.generic.plannr.Entities.Event
     *
     * @param e    The com.generic.plannr.Entities.Event
     * @param user The com.generic.plannr.Entities.User
     */
    public boolean deleteEvent(SchoolEvent e, User user) {
        return user.getEventList().remove(e);
    }

}
