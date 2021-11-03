package Gateways;

import Entities.Event;
import Entities.User;
import Presenters.EventPresenter;
import UseCases.UserManager;

import java.util.ArrayList;
import java.util.Arrays;

public class EventOutput {
    private static ArrayList<String> menuOptions = new ArrayList<>(Arrays.asList(
            "1. Return to main menu",
            "2. Deadline"
    ));

    public static void printEventOptions() {
        // Provide options
        System.out.println("Please choose the type of event you wish to create:");
        for (String o: menuOptions) {
            System.out.println(o);
        }
    }

    public static void promptEventName() {
        System.out.println("Enter the name of the event");
    }

    public static void promptEventDate() {
        System.out.println("Enter the due date (24-hour format: \"dd-mm-yyyy HH:mm\"): ");
    }

    public static void promptEventPriority() {
        System.out.println("Enter the priority of the event (0 = high, 1 = mid, 2 = low): ");
    }

    public static void promptCourseOption(UserManager user) {
        System.out.println(EventPresenter.constructUserEvents(user));
        System.out.println("Select the course this deadline is for (enter the corresponding number):");
    }

    public static void printUserEvents(UserManager user) {
        // Print all the user's events with their details
        System.out.println("--- YOUR CURRENT EVENTS AND DEADLINES: --- ");
        for (Event e : user.viewEventList()) {
            System.out.println(e);
        }
        System.out.println("-------------------------");
    }
}
