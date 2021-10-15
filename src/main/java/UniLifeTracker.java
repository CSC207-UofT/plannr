
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

        // Onboard user
        UserManager userManager = SystemInOut.onBoardUser();
        // run the program
        SystemInOut.run(userManager);
    }
}


