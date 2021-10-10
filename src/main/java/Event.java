import java.util.Date;

public abstract class Event {
    private String name;
    private int priority;
    private Date startDate;
    private Date endDate;

    /**
     * Construct an Event, giving them the given name,
     * priority, start date, and end date.
     *
     * @param name      The Event's name
     * @param priority  The Event's priority
     * @param startDate The Event's start date
     * @param endDate   The Event's end date
     */
    public Event(String name, int priority, Date startDate, Date endDate) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String name, int priority, Date endDate) {
        this.name = name;
        this.priority = priority;
        this.endDate = endDate;
    }

    public Event(String name, Date endDate) {
        this.name = name;
        this.priority = 0;
        this.endDate = endDate;
    }

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
