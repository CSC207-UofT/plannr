import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void createUser(String name, String school, ArrayList<String> courses) {
        User u = new User(name, courses, school);
        users.add(u);
    }

    @Override
    public String toString() {
        return "bob";
    }
}
