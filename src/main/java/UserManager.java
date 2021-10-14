import java.lang.reflect.Array;
import java.util.ArrayList;
import java.time.LocalDate;

public class UserManager {
    /**
     * This method will create the user by taking in the necessary arguments and return
     * the created User object
     * @param name      User's name
     * @param courses   User's courses
     * @param school    User's school
     * @return  the created User object
     */
    public User createUser(String name, ArrayList<String> courses, String school) {
        return new User(name, courses, school);
    }

    /**
     * This method adds the event to the User
     * @param user      The user whom the course is added to
     * @param event     The event object that needs to be added to the user
     * @return  whether it has been successfully added, as a Boolean
     */
    public boolean addEvent(User user, Event event) {
        ArrayList<Event> eventList = user.getEventList();
//        try {
//      I'll continue working on this (Daniel)
//        }
        return true;
    }

}
