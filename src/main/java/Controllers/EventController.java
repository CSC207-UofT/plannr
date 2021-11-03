package Controllers;

import Gateways.EventOutput;
import Gateways.SystemOut;
import UseCases.UserManager;

import java.time.LocalDateTime;

import static Controllers.SystemIn.DATEFORMAT;
import static Controllers.SystemIn.getIntInput;

public class EventController {

    public static void getEvent(UserManager user) {
        // Print menu options
        EventOutput.printEventOptions();
        // Get user input
        int input = SystemIn.getIntInput();

        if (input != 1) {
            // Prompt for event name
            String eventName = "";
            while (eventName.equals("") || eventName.equals("Invalid")) {
                EventOutput.promptEventName();
                eventName = SystemIn.getStrInput();
            }
            // Prompt for event end date
            EventOutput.promptEventDate();
            LocalDateTime endDate = SystemIn.getDate();
            while (endDate.equals(LocalDateTime.parse("01-01-1900 00:00", DATEFORMAT))) {
                SystemOut.printErrorMessage();
                EventOutput.promptEventDate();
                endDate = SystemIn.getDate();
            }

            // Prompt for event priority
            EventOutput.promptEventPriority();
            int eventPriority = getIntInput();

            // Asks for Deadline specific properties
            if (input == 2) {
                EventOutput.promptCourseOption(user);
                // Get user input
                int ddlCourseNum = getIntInput();
                // Get course name
                String ddlCourseName = user.viewCourses().get(ddlCourseNum-1);
                // Create and add deadline event object using UseCases.UserManager
                user.addDeadlineEvent(eventName, eventPriority, endDate, ddlCourseName);
            }
            EventOutput.printUserEvents(user);
        }
    }
}
