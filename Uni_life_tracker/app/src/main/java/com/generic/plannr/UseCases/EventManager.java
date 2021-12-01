package com.generic.plannr.UseCases;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.User;

public class EventManager{

    /**
     * Add an com.generic.plannr.Entities.Event
     *
     * @param e    The com.generic.plannr.Entities.Event
     * @param user  The com.generic.plannr.Entities.User
     */
    public void addEvent(Event e, User user){
        user.getEventList().add(e);
    }

    /**
     * Delete an com.generic.plannr.Entities.Event
     *
     * @param e     The com.generic.plannr.Entities.Event
     * @param user  The com.generic.plannr.Entities.User
     */
    public boolean deleteEvent(Event e, User user){
        return user.getEventList().remove(e);
    }

}
