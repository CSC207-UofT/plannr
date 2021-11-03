import Controllers.EventController;
import Controllers.SystemIn;
import Gateways.SystemOut;
import UseCases.UserManager;

import java.util.ArrayList;

public class UniLifeTracker {

    public static void main (String[] args) {
        // Print welcome message
        SystemOut.welcomeMessage();
        // Get user info
        ArrayList<String> userInfo = SystemOut.getUserInfoDialog();
        UserManager user = new UserManager(userInfo.get(0), SystemOut.getUserCoursesDialog(), userInfo.get(1));
        // Set default value for user input
        int input;
        // Run the program
        do {
            // Print the menu of the program
            SystemOut.printMenu();
            SystemOut.promptMenuInput();
            input = SystemIn.getIntInput();

            // Check for errors
            if (input == -1) {
                SystemOut.printErrorMessage();
                input = 0;
            } else if (input == 1) {
                EventController.getEvent(user);
                input = 0;
            }

        } while (input == 0);
    }
}


