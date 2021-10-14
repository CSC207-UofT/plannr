import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeadlineManager {
    private ArrayList<Deadline> deadlines;

    public DeadlineManager() {
        deadlines = new ArrayList<>();
    }

    public Deadline createDeadline(String name, int priority, LocalDateTime endDate, String courseName) {
        return new Deadline(name, priority, endDate, courseName);
    }
}
