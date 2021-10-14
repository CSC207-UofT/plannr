import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserManager {
    //private ArrayList<User> users;
    private User u;
    private DeadlineManager deadlineManager;

    public UserManager(String name, ArrayList<String> courses, String school) {
        //users = new ArrayList<>();
        u = createUser(name, courses, school);
        deadlineManager = new DeadlineManager();
    }

    public User createUser(String name, ArrayList<String> courses, String school) {
        User u = new User(name, courses, school);
        //users.add(u);
        return u;
    }

    public void addDeadlineEvent(String eventName, int priority, LocalDateTime endDate, String courseName) {
        u.getEventList().add(deadlineManager.createDeadline(eventName, priority, endDate, courseName));
    }

    public ArrayList<Event> viewEventList() {
        return u.getEventList();
    }


    @Override
    public String toString() {
        return "bob";
    }
}
