package com.generic.plannr.UseCaseTests;

import org.junit.*;
import static org.junit.Assert.*;

import static UseCases.SortTodaysEvents.sortByDate;
import static UseCases.SortTodaysEvents.sortByPriority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import Entities.Event;
import UseCases.EventDateComparator;
import UseCases.UserManager;

public class SortTodaysEventsTest {

    UserManager user;
    Event e1, e2, e3;
    ArrayList<String> COURSES;
    EventDateComparator EDC;

    @Before
    public void setUp() {
        COURSES = new ArrayList<>();
        EDC = new EventDateComparator();
        user = new UserManager(
                "test",
                COURSES,
                 "school"
        );
        e1 = new Event(
                "test",
                2,
                LocalDateTime.of(2021, 11, 15, 1, 1, 1),
                LocalDateTime.of(2021, 11, 15, 1, 1, 1)
        );
        e2 = new Event(
                "test",
                1,
                LocalDateTime.of(2021, 11, 15, 1, 1, 2),
                LocalDateTime.of(2021, 11, 15, 1, 1, 2)
        );
        e3 = new Event(
                "test",
                0,
                LocalDateTime.of(2021, 11, 15, 1, 1, 3),
                LocalDateTime.of(2021, 11, 15, 1, 1, 3)
        );

        user.addEventToUsersList(e1);
        user.addEventToUsersList(e3);
        user.addEventToUsersList(e2);
    }

    @Test(timeout = 50)
    public void TestSortByDate() {
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(e1, e2, e3));
        assertEquals(expected ,sortByDate(user));

    }

    @Test(timeout = 50)
    public void TestSortByPriority() {
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(e3, e2, e1));
        assertEquals(expected, sortByPriority(user));
    }

}
