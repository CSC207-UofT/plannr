import java.util.Date;

public class Work extends Event {

    private String location;

    /**
     * Construct a Work event, giving it the given name,
     * priority, start date, end date and location.
     *
     * @param name      The Work's name
     * @param priority  The Work's priority
     * @param startDate The Work's start date
     * @param endDate   The Work's end date
     * @param location  The Work's location
     */
    public Work(String name, int priority, Date startDate, Date endDate,
                 String course, String location, String note) {
        super(name, priority, startDate, endDate);
        this.location = location;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}