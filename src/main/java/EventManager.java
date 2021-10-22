/*
NOTE: TO BE IMPLEMENTED LATER IN PHASE 1!!
*/
import java.util.ArrayList;
import java.util.Date;

public class EventManager{

    /**
     * Add an Event
     *
     * @param e    The Event
     * @param user  The User
     */
    public void addEvent(Event e, User user){
        user.getEventList().add(e);
    }

    /**
     * Delete an Event
     *
     * @param e     The Event
     * @param user  The User
     */
    public boolean deleteEvent(Event e, User user){
        return user.getEventList().remove(e);
    }


}
