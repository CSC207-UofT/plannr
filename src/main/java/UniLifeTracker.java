import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniLifeTracker {

    public static void main (String[] args) {
        //Setting things up
        Scanner in = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        // A little welcome
        System.out.println("Welcome to Uni Life Tracker! \nWe are here to help you track your disastrous life!");

        System.out.println("-------------------------");

        // Prompt user for their name + university + courses they attend
        System.out.print("What is your name? ");
        String userName = in.nextLine();

        System.out.print("What is the name of the school you attend? ");
        String userSchool = in.nextLine();

        System.out.print("How many classes are you attending? ");
        int userNumCourses = scan.nextInt();
        ArrayList<String> userCourses = new ArrayList<>();
        for (int i = 1; i <= userNumCourses; i++) {
            System.out.printf("Enter course #%d: ", i);
            userCourses.add(in.nextLine());
        }

        //Create User object using UserManager
        UserManager userManager = new UserManager(userName, userCourses, userSchool);

        System.out.println("-------------------------");


        //while loop stops when user wants to exit program
        boolean stay = true;
        while (stay) {

            // Prompt user to choose an action (add event or exit program)
            System.out.println("Actions: " +
                    "\n1. Add an event" +
                    "\n2. Exit program");
            System.out.print("Select an action (enter the corresponding number): ");
            int userAction = scan.nextInt();

            System.out.println();

            if (userAction == 1) { //add event

                //Prompt user for type of event they want to add
                System.out.println("Types of events:" +
                        "\n1. Deadline"); //only option for now
                System.out.print("Select the type of event you want to add (enter the corresponding number): ");
                int eventType = scan.nextInt();

                System.out.println();

                if (eventType == 1) {
                    //Prompt for details of event (name, end date, priority, course, etc.)
                    System.out.print("Enter the name of the deadline: ");
                    String eventName = in.nextLine();

                    System.out.print("Enter the due date (format: 'dd-mm-yyyy hh:mm AM/PM') : ");
                    String eventEndDate = in.nextLine();
                    LocalDateTime eventDeadline = LocalDateTime.parse(eventEndDate, customFormat);

                    //if date == date of an event in user's event list, print there's a conflict

                    System.out.print("Enter the priority of the deadline (0 = high, 1 = mid, 2 = low): ");
                    int eventPriority = scan.nextInt();

                    System.out.println("Your course list: ");
                    for (int i = 1; i <= userManager.viewCourses().size(); i++) {
                        System.out.printf("%d. %s\n", i, userManager.viewCourses().get(i-1));
                    }
                    System.out.print("Select the course this deadline is for (enter the corresponding number): ");
                    int eventCourseNum = scan.nextInt();
                    String eventCourseName = userManager.viewCourses().get(eventCourseNum - 1);

                    //Create + add Deadline event object using UserManager
                    userManager.addDeadlineEvent(eventName, eventPriority, eventDeadline, eventCourseName);
                }

                System.out.println();

                //Print that the event has been created with its details
                System.out.println("Your current events and deadlines: ");
                for (Event e : userManager.viewEventList()) {
                    System.out.println(e);
                }

                System.out.println("-------------------------");

            }
            else {
                stay = false;
            }
        }
    }
}

