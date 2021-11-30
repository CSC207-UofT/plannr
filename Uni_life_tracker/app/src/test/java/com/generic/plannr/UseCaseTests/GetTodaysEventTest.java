package com.generic.plannr.UseCaseTests;

import org.junit.*;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import Entities.Event;
import static UseCases.GetTodaysEvents.getTodaysEvents;
import UseCases.UserManager;

public class GetTodaysEventTest {

    UserManager user;
    Event e1, e2, e3;
    ArrayList<String> COURSES;

    @Before
    public void setUp() {
        COURSES = new ArrayList<>();

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
    public void TestBasic() {
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(e1, e3, e2));
        assertEquals(expected, getTodaysEvents(user));
    }

    @Test(timeout = 50)
    public void TestOneEventNotToday() {
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(e1, e3, e2));
        user.addEventToUsersList(
                new Event(
                        "test",
                        0,
                        LocalDateTime.of(2021, 11, 1, 1, 1, 3),
                        LocalDateTime.of(2021, 11, 1, 1, 1, 3)
                )
        );
        assertEquals(expected, getTodaysEvents(user));
    }
}
