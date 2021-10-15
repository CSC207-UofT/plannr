import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SystemInOut {
    private final Scanner sc;

    /* Constructor for the controller
    This constructor takes no argument and creates a scanner within the controller
     */
    public SystemInOut() {
        this.sc = new Scanner(System.in);
    }

    /* This method will take in user info to construct the user*/
    public void userOnBoard() {
        // Welcome message
        System.out.println("Welcome to Uni Life Tracker!\nWe are here to help you track your disastrous life!");
        System.out.println("-------------------------");
        // Get user's info
        ArrayList<String> userInfo = this.getUserInfo();
        // Get user's courses
    }

    /* This method will get the user's name, while catching for errors */
    private ArrayList<String> getUserInfo() {
        ArrayList<String> result = new ArrayList<>();
        // Get user's name
        String name = "";
        do {
            System.out.println("What's your name?");
            try {
                name = this.sc.nextLine();
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
            System.out.println("What's name of the school you attend?");
            try {
                uni = this.sc.nextLine();
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

}
