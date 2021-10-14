//import java.util.LocalDate;
//import java.util.concurrent.TimeUnit;
//
//public class Assessment extends Event {
//    private long duration; //duration of the assessment
//    private String course; //course the assessment is from
//
//    /**
//     * Constructs a Assessment event, giving it the given name, priority,
//     * start date, end date, and course
//     *
//     * @param name  This Assessment's name
//     * @param priority  This Assessment's priority
//     * @param startDate  This Assessment's start date
//     * @param endDate  This Assessment's end date
//     * @param course  This Assessment's course from which its from.
//     */
//    public Assessment(String name, int priority, Date startDate, Date endDate, String course) {
//        super(name, priority, startDate, endDate);
//
//        long tempDuration = endDate.getTime() - startDate.getTime();
//        this.duration = TimeUnit.MILLISECONDS.toHours(tempDuration);
//
//        this.course = course;
//    }
//
//    /**
//     * Get duration of this Assessment
//     * @return duration of this Assessment
//     */
//    public long getDuration() {
//        return this.duration;
//    }
//
//    /**
//     * toString method
//     * @return a description of this Assessment event.
//     */
//    @Override
//    public String toString() {
//        String result =
//                "This assessment from " + this.course + " starts on " + this.getStartDate()
//                + " and ends on " + this.getEndDate() + " and lasts " + this.duration + " hours.";
//        return result;
//    }
//}
