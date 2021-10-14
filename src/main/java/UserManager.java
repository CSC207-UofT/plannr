import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserManager {
    private User u;

    public UserManager(String name, ArrayList<String> courses, String school) {
        u = createUser(name, courses, school);
    }

    public User createUser(String name, ArrayList<String> courses, String school) {
        return new User(name, courses, school);
    }

    public void addDeadlineEvent(String eventName, int priority, LocalDateTime endDate, String courseName) {
        u.getEventList().add(new Deadline(eventName, priority, endDate, courseName));
    }

    public ArrayList<Event> viewEventList() {
        return u.getEventList();
    }


    @Override
    public String toString() {
        return "bob";
    }
}
