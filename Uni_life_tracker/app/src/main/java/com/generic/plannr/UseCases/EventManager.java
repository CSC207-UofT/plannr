package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Entities.User;

public class EventManager {

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
