import java.util.Date;
import java.time.LocalDateTime;

public class Deadline extends Event {
    private String course; //course the deadline is from

    /**
     * Construct Deadline event, giving them the given
     * name, priority, end date, and course name.
     *
     * @param name   This Deadline's name
     * @param priority  This Deadline's priority
     * @param endDate  This Deadline's due date
     * @param courseName  This Deadline's course which it's from
     */
    public Deadline(String name, int priority, Date endDate, String courseName) {
        super(name, priority, endDate);
        this.course = courseName;
    }

    public String getCourse() {
        return this.course;
    }

    /**
     * toString method
     * @return a String that describes this Deadline event.
     */
    @Override
    public String toString() {
        return "Deadline: The assignment " + this.getName() + " from " + this.course + " is due on "
                + this.getEndDate();
    }
}
