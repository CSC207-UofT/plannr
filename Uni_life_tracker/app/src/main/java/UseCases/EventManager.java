package UseCases;/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
import Entities.Event;
import Entities.User;

public class EventManager{

    /**
     * Add an Entities.Event
     *
     * @param e    The Entities.Event
     * @param user  The Entities.User
     */
    public void addEvent(Event e, User user){
        user.getEventList().add(e);
    }

    /**
     * Delete an Entities.Event
     *
     * @param e     The Entities.Event
     * @param user  The Entities.User
     */
    public boolean deleteEvent(Event e, User user){
        return user.getEventList().remove(e);
    }


}
