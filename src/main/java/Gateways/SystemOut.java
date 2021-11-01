package Gateways;

import Controllers.SystemIn;

import java.util.ArrayList;

public class SystemOut {
    /**
     * This method will print the welcome message of this program
     */
    public static void welcomeMessage() {
        // Welcome message
        System.out.println("--- WELCOME TO UNI LIFE TRACKER! ---" +
                "\nWe are here to help you track your disastrous life!");
        System.out.println("-------------------------");
    }

    /**
     * This will print out the dialog for the user to input personal info
     * @return returns an ArrayList that contains the user's name and school name, in this respective order
     */
    public static ArrayList<String> getUserInfoDialog() {
        ArrayList<String> result = new ArrayList<>();
        // Empty variable to store user's name
        String name = "";
        // While loop to keep asking for input if invalid or empty
        while (name.length() == 0 || name.equals("Invalid")) {
            System.out.println("What's your name?");
            name = SystemIn.getStrInput();
            if (name.equals("Invalid")) {
                System.out.println("Invalid input, please try again");
            }
        }
        result.add(name);

        // Empty variable to store user's school information
        String school = "";
        // Same logic as before
        while (school.length() == 0 || school.equals("Invalid")) {
            System.out.println("What's the name of the school you are attending?");
            school = SystemIn.getStrInput();
            if (school.equals("Invalid")) {
                System.out.println("Invalid input, please try again");
            }
        }
        result.add(school);

        return result;
    }

    /**
     * This will print out the dialog for the user to input courses
     * @return an ArrayList<String> list of courses
     */
    public static ArrayList<String> getUserCoursesDialog() {
        // Empty ArrayList to store user's courses
        ArrayList<String> courses = new ArrayList<>();
        // Empty String to store user's input
        String input = "";
        // Prompt user
        System.out.print("What classes are you attending? If you don't have any courses at the moment, please " +
                "type n.\n" + "Please enter the course ID: ");
        while (!input.equals("n")) {
            // Get user's input
            input = SystemIn.getStrInput();
            // Check if String is invalid or empty
            if (input.equals("Invalid") || input.length() == 0) {
                System.out.println("Invalid input, please try again.");
            } else if (courses.contains(input)) { // check if course already added
                System.out.println("Course already added, please try another one.");
            } else {
                courses.add(input);
                System.out.println("Course \"" + input + "\" added!");
            }
            System.out.println("Please enter the course ID or type n to stop.");
        }

        return courses;
    }

}
