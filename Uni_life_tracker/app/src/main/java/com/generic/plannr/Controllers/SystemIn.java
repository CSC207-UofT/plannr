package com.generic.plannr.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SystemIn {
    // Create a static scanner
    private static final Scanner sc = new Scanner(System.in);
    // Date format for input
    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

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
            int input = sc.nextInt();
            sc.nextLine();
            return input;
        } catch (Exception e) {
            sc.nextLine();
            return -1;
        }
    }

    /**
     * This method will get date from user
     * @return a LocalDateTime object
     */
    public static LocalDateTime getDate() {
        String dateInput = getStrInput();
        LocalDateTime result = LocalDateTime.parse("01-01-1900 00:00", DATEFORMAT);
        // Try parse it as a LocalDateTime
        try {
            result = LocalDateTime.parse(dateInput, DATEFORMAT);
        } catch (Exception e) {
            return result;
        }
        return result;
    }
}
