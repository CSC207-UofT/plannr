package com.generic.plannr.tests;

import com.generic.plannr.Entities.SchoolEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class StaticTestVariables {
    public static LocalDateTime defaultDate = LocalDateTime.of(2021, 11, 15, 1, 1, 1);
    public static SchoolEvent defaultEvent = new SchoolEvent("deadline", "Default Event", 0, defaultDate, defaultDate, "CSC207");
    public static LocalDateTime TODAY = LocalDateTime.now();
    public static ArrayList<String> COURSES = new ArrayList<>(Arrays.asList("CSC207", "CSC148", "CSC165", "CSC311", "CSC236"));

    // This eventList differs only in priorities
    public static ArrayList<SchoolEvent> priorityEventList = new ArrayList<>(Arrays.asList(
            new SchoolEvent("class", "test", 2, defaultDate, defaultDate, "CSC207", "Bahen"),
            new SchoolEvent("deadline", "test", 1, defaultDate, defaultDate, "CSC207"),
            new SchoolEvent("assessment", "test", 0, defaultDate, defaultDate, "CSC207")
    ));

    // This eventList differs only in days
    public static ArrayList<SchoolEvent> dayEventList = new ArrayList<>(Arrays.asList(
            new SchoolEvent("assessment", "test", 0, defaultDate, defaultDate, "CSC207"),
            new SchoolEvent("deadline", "test", 0, defaultDate.plusDays(2), defaultDate.plusDays(2), "CSC207"),
            new SchoolEvent("class", "test", 0, defaultDate.plusDays(1), defaultDate.plusDays(1), "CSC207", "Bahen")
    ));
}
