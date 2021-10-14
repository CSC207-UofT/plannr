import java.util.Scanner;

public class UniLifeTracker {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Prints welcome message
        System.out.println("Welcome!");

        // Asks for user's name
        System.out.println("Let's get to know you a bit. What's your name? ");
        String userName = in.nextLine();
        userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();
        System.out.println("Which school do you attend? ");
        String userSchool = in.nextLine();

        // Make a User object
        User u = new User(userName, userSchool);
        System.out.println(String.format("Welcome %s!", userName));

        //Prompt user to add an event or exit program
        boolean stay = true;
        while(stay)
        {
            System.out.println("Which action do you want to do? (Enter a number) " + "\n");
            System.out.println("1. Add an event" + "\n" + "2. Exit program");
            int action = in.nextInt();
            if (action == 1) {
                System.out.println("TEST: Add event"); // remove this
                stay = false;

            }
            else if (action == 2) {
                System.out.println("TEST: Exit program"); // remove this
                stay = false;
            }
            else {
                System.out.println("Incorrect input. Please try again!");
            }
        }


        //If "Add event" selected, prompt user for type of event they want to add

        //Prompt for details of the event (name, endDate, priority, course)

        //

    }
}
