public class UniLifeTracker {

    public static void main (String[] args) {
        // Onboard user
        UserManager userManager = SystemInOut.onBoardUser();
        // run the program
        SystemInOut.run(userManager);
    }
}


