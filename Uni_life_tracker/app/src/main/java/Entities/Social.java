package Entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Social extends Event {
    private int duration;
    private String location;
    private ArrayList<String> participants;

    /**
     * Social events class constructor, which calls its super class Event
     * This constructor LETS THE USER PROVIDE PARTICIPANTS
     * @param name          Name of event
     * @param priority      Priority of event
     * @param startDate     Start date of event
     * @param endDate       End date of event
     * @param duration      Duration of event
     * @param location      Location of event
     * @param participants  Participants of event
     */
    public Social(String name, int priority, LocalDateTime startDate, LocalDateTime endDate,
                  int duration, String location, ArrayList<String> participants) {
        // Calls the super method
        super(name, priority, startDate, endDate);
        // Assigns instance variables unique to Entities.Social
        this.duration = duration;
        this.location = location;
        this.participants = participants;
    }

    /**
     * Social events class constructor, which calls its super class Event
     * This constructor DOES NOT REQUIRE USER TO INPUT LIST OF PARTICIPANTS
     * @param name          Name of event
     * @param priority      Priority of event
     * @param startDate     Start date of event
     * @param endDate       End date of event
     * @param duration      Duration of event
     * @param location      Location of event
     */
    public Social(String name, int priority, LocalDateTime startDate, LocalDateTime endDate,
                  int duration, String location) {
        // Calls the super method
        super(name, priority, startDate, endDate);
        // Assigns instance variables unique to Entities.Social
        this.duration = duration;
        this.location = location;
        this.participants = new ArrayList<>();
    }

    /**
     * Gets the duration of this Social event
     * @return the duration of this Entities.Social event
     */
    public int getDuration() {
        return this.duration;
    }

    /** ========== SUBJECT TO CHANGE ============
     * toString method for Social, used for printing to screen
     * @return a String that describes this Social event
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder((
                "The event "
                        + this.getName()
                        + " is scheduled to start on "
                        + this.getStartDate()
                        + " and end on "
                        + this.getEndDate()
                        + ". It would last "
                        + this.getDuration()
                        + " hours. \n The expected participants are: "
        ));

        // Use a for loop to append the participants into the result String
        if (this.participants.size() > 0) {
            for (String p : this.participants) {
                result.append(p).append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Changes the duration of the social event
     * @param duration the new duration of the event
     */
    public void changeDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Changes the location of the social event
     * @param location the new location of the event
     */
    public void changeLocation(String location) {
        this.location = location;
    }

    /**
     * This method adds one participant to the participants
     * @param person the new participant the user wish to add
     */
    public void addParticipant(String person) {
        this.participants.add(person);
    }

    /**
     * This method adds one participant to the participants
     * @param person the new participant the user wish to add
     */
    public void removeParticipant(String person) {
        this.participants.remove(person);
    }
}
