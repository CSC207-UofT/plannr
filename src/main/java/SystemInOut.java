import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SystemInOut {
    private static final Scanner sc = new Scanner(System.in);
    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    /**
     * This method will create the User
     * @return an UserManager object
     */
    public static UserManager onBoardUser() {
        // Welcome message
        System.out.println("Welcome to Uni Life Tracker!\nWe are here to help you track your disastrous life!");
        System.out.println("-------------------------");
        // Get user's info
        ArrayList<String> userInfo = getUserInfoDialog();
        // Get user's courses
        ArrayList<String> userCourses = getUserCoursesDialog();

        System.out.println("-------------------------");

        return new UserManager(userInfo.get(0), userCourses, userInfo.get(1));
    }

    /**
     * This method will get the user's name, while catching for errors
     * helper of userOnBoard
     */
    private static ArrayList<String> getUserInfoDialog() {
        ArrayList<String> result = new ArrayList<>();
        // Get user's name
        String name = "";
        do {
            System.out.println("What's your name?");
            try {
                name = sc.nextLine();
                // Catch empty strings
                if (name.length() == 0) {
                    System.out.println("Please enter a name.");
                }
            } catch (Exception e) { // Catch for invalid input
                System.out.println("Invalid input, please try again.");
            }
        } while (name.length() == 0);
        result.add(name);
        // Get user's university
        String uni = "";
        do {
            System.out.println("Hi " + name + "! What's name of the school you attend?");
            try {
                uni = sc.nextLine();
                // Catch empty strings
                if (uni.length() == 0) {
                    System.out.println("Please enter a name.");
                }
            } catch (Exception e) { // Catch for invalid input
                System.out.println("Invalid input, please try again.");
            }
        } while (uni.length() == 0);
        result.add(uni);

        return result;
    }

    /**
     * This method will get user's courses and return an ArrayList
     * helper of userOnBoard
     */
    private static ArrayList<String> getUserCoursesDialog() {
        ArrayList<String> courses = new ArrayList<>();
        // Prompt user
        System.out.print("What classes are you attending? If you don't have any courses at the moment, please " +
                "type n.\n" + "Please enter the course id");
        // Get the next line first
        String nextLine = sc.nextLine();
        // Loop for user input
        while (!nextLine.equals("n")) {
            try {
                courses.add(nextLine);
                System.out.println("Course \"" + nextLine + "\" added");
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
            System.out.println("Please enter the course id or type n to stop.");
            nextLine = sc.nextLine();
        }

        return courses;
    }

    /**
     * This method will handle user input during runtime
     * @param userManager the manager of the current user
     */
    public static void run(UserManager userManager) {
        // Prompt valid options
        System.out.println("Actions:" +
                "\n1. Add an event" +
                "\n2. Exit program");
        // Default value for nextLine, used to detect user action and catch errors
        int nextLine = 0;
        // do while loop to get user action and run the program
        do {
            System.out.println("Please type the number corresponding to the options to choose an action");
            try {
                // Use nextInt to make sure the user only uses the number and nothing weird
                nextLine = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }

            // User chose to create new event, calls helper and set nextLine back to 0 to continue the loop
            // NOTE: Future actions will be dealt with here using the if statements
            if (nextLine == 1) {
                addEventDialog(userManager);
                nextLine = 0; // this allows us to loop until the user chooses to exit, which sets it to 2
            }

        } while (nextLine == 0);
    }

    /**
     * This deals with everything related to the inputs for creating events during runtime
     * @param userManager the manager of the current user
     */
    private static void addEventDialog(UserManager userManager) {
        // Condition for while loop
        boolean stay = true;
        // do-while loop to prompt user for input
        do {
            // Provide options
            System.out.println("Please choose the type of event you wish to create:");
            System.out.println("1. Return to main menu" +
                    "\n2. Deadline");
            // Get user input and catch for errors
            try {
                int nextLine = sc.nextInt();
                sc.nextLine(); // To consume the next line character so that our next "nextLine" can function properly
                // exit current menu if user wants to return
                if (nextLine != 1) {
                    // Ask for general properties of an event
                    // Get name of event
                    System.out.println("Enter the name of the event: ");
                    String eventName = sc.nextLine();

                    // Get endDate of event
                    System.out.println("Enter the due date (format: \"dd-mm-yyyy HH:mm\")");
                    String eventDDL = sc.nextLine();
                    LocalDateTime eventEndDate = LocalDateTime.parse(eventDDL, DATEFORMAT);
                    // Get priority of event
                    System.out.println("Enter the priority of the event (0 = high, 1 = mid, 2 = low): ");
                    int eventPriority = sc.nextInt();

                    // Ask for deadline specific properties
                    if (nextLine == 2) {
                        // Print out all the courses the user currently has
                        for (int i = 0; i < userManager.viewCourses().size(); i++) {
                            System.out.println((i+1) + ". " + userManager.viewCourses().get(i));
                        }
                        System.out.println("Select the course this deadline is for (enter the corresponding number");
                        int ddlCourseNum = sc.nextInt();
                        // Get course name
                        String ddlCourseName = userManager.viewCourses().get(ddlCourseNum-1);
                        // Create and add deadline event object using UserManager
                        userManager.addDeadlineEvent(eventName, eventPriority, eventEndDate, ddlCourseName);
                    }
                } else{ stay = false;}
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
            // Print all the user's events with their details
            System.out.println("Your current events and deadlines: ");
            for (Event e : userManager.viewEventList()) {
                System.out.println(e);
            }
            System.out.println("-------------------------");
        } while (stay);
    }
}
