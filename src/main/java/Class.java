import java.util.Date;

public class Class extends Event {

    private String course;
    private String location;
    private String note;

    /**
     * Construct a Class event, giving it the given name,
     * priority, start date, end date, the course code,
     * the location, and a note.
     *
     * @param name      The Class's name
     * @param priority  The Class's priority
     * @param startDate The Class's start date
     * @param endDate   The Class's end date
     * @param course    The Class's course code
     * @param location  The Class's location
     * @param note      The Class's note
     */
    public Class(String name, int priority, Date startDate, Date endDate,
                 String course, String location, String note) {
        super(name, priority, startDate, endDate);
        this.course = course;
        this.location = location;
        this.note = note;
    }


    @Override
    public String toString() {
        return super.getName() + ": " + this.course;
    }
}