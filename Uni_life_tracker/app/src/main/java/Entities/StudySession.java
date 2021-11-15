package Entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Represents a study session of a course, with participants, location and a note.
 */
public class StudySession extends Event {
    private String course;
    private String location;
    private ArrayList<String> participants;

    /**
     * Constructs an Entities.StudySession event, giving them the given name,
     * location, notes, and participants.
     *
     * @param name           The Entities.StudySession's name
     * @param priority       The Entities.StudySession's priority (0 = high, 1 = mid, 2 = low)
     * @param startDate      The Entities.StudySession's start date
     * @param endDate        The Entities.StudySession's end date
     * @param course         The Entities.StudySession's course
     * @param location       The Entities.StudySession's location
     * @param participants   The Entities.StudySession's participants
     */

    public StudySession (String name, int priority, LocalDateTime startDate, LocalDateTime endDate,
                        String course, String location, ArrayList<String> participants) {
        super(name, priority, startDate, endDate);
        this.course = course;
        this.location = location;
        this.participants = participants;
    }

    /**
     * Gets the course name of the Study Session
     * @return the name of the course as a String
     */
    public String getCourse() {
        return this.course;
    }

    /**
     * Gets the location of the study session
     * @return the location of the study session as a string
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Gets the participants of the study session
     * @return the participants of the study session as an Array List
     */
    public ArrayList<String> getParticipants() {
        return this.participants;
    }

    /**
     * Changes the course of the study session
     * @param course is the new name of the course for this study session
     */
    public void setCourse(String course) { this.course = course; }

    /**
     * Changes the location of the study session
     * @param location is the new location of the study session
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Changes the list of participants of the study session
     * @param participants is the new list of participants
     */
    public void setParticipants (ArrayList<String> participants) {
        this.participants = participants;
    }

    /**
     * toString method
     * @return a string that represents the Entities.StudySession event
     */
    @Override
    @NonNull
    public String toString() {
        String strPriority;
        if (this.getPriority() == 0) {
            strPriority = "high";
        }
        else if (this.getPriority() == 1) {
            strPriority = "mid";
        }
        else {
            strPriority = "low";
        }

        StringBuilder result = new StringBuilder(
                String.format("Study session (%s priority): %s for %s starts on %s and " +
                "ends at %s at %s.\n\tThe expected participants are: ",
                        strPriority,
                        this.getName(),
                        this.getCourse(),
                        this.getStartDate().format(DATEFORMAT),
                        this.getEndDate().format(DATEFORMAT),
                        this.getLocation()));

        // Use a for loop to append the participants into the result String
        if (this.participants.size() > 0) {
            for (String p : this.participants) {
                result.append("\n\t\t").append(p);
            }
        }

        return result.toString();
    }
}
