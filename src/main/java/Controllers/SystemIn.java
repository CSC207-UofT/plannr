package Controllers;

import Gateways.SystemOut;
import UseCases.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class SystemIn {
    // Create a static scanner
    private static final Scanner sc = new Scanner(System.in);

    public static UserManager onBoardUser() {
        // Get user's information by invoking the getUserInfoDialog
        ArrayList<String> info = SystemOut.getUserInfoDialog();
        // Get user's courses by invoking the getUserCoursesDialog
        ArrayList<String> courses = SystemOut.getUserCoursesDialog();

        return new UserManager(info.get(0), courses, info.get(1));
    }

    /**
     * This method will get String inputs from the user
     * @return returns the input, else returns "Invalid Input"
     */
    public static String getStrInput() {
        try {
            return sc.nextLine();
        } catch(Exception e) {
            return "Invalid";
        }
    }

    /**
     * This method will get String inputs from the user
     * @return Returns the input, else returns -1 for invalid inputs
     */
    public static int getIntInput() {
        try {
            return sc.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }
}
