package com.generic.plannr.tests;

import com.generic.plannr.Entities.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class StaticTestVariables {
    public static LocalDateTime defaultDate = LocalDateTime.of(2021, 11, 15, 1, 1, 1);
    public static Event defaultEvent = new Event("Default Event", 0, defaultDate, defaultDate);
    public static LocalDateTime TODAY = LocalDateTime.now();
    public static ArrayList<String> COURSES = new ArrayList<>(Arrays.asList("CSC207", "CSC148", "CSC165", "CSC311", "CSC236"));

    // This eventList differs only in priorities
    public static ArrayList<Event> priorityEventList = new ArrayList<>(Arrays.asList(
            new Event("test", 2, defaultDate, defaultDate),
            new Event("test", 1, defaultDate, defaultDate),
            new Event("test", 0, defaultDate, defaultDate)
    ));

    // This eventList differs only in days
    public static ArrayList<Event> dayEventList = new ArrayList<>(Arrays.asList(
            new Event("test", 0, defaultDate, defaultDate),
            new Event("test", 0, defaultDate.plusDays(2), defaultDate.plusDays(2)),
            new Event("test", 0, defaultDate.plusDays(1), defaultDate.plusDays(1))
    ));
}
