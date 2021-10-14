import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;

public class DeadlineManager {
    private ArrayList<Deadline> deadlines;

    public DeadlineManager() {
        deadlines = new ArrayList<>();
    }

    public void addDeadline(String name, int priority, Date endDate, String courseName) {
        Deadline newDeadline = new Deadline(name, priority, endDate, courseName);
    }
}
