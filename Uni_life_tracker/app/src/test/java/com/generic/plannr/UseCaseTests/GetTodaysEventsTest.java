package com.generic.plannr.UseCaseTests;

import static org.junit.Assert.assertEquals;
import static com.generic.plannr.UseCases.GetTodaysEvents.getTodaysEvents;
import static com.generic.plannr.StaticTestVariables.*;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.UserManager;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GetTodaysEventsTest {
    ArrayList<Event> events;
    Random rand;

    @Before
    public void setUp() {
        events = new ArrayList<>();
        rand = new Random();
    }

    @Test
    public void TestEmptyEventList() {
        UserManager user = new UserManager("test user", COURSES, "U of Tears and Tuition");
        assertEquals(new ArrayList<>(), getTodaysEvents(user));
    }

    /**
     * This tests the method given a user that only has one event and it also happens today
     */
    @Test
    public void Test1EventOfToday() {
        UserManager user = new UserManager("test user", COURSES, "U of Tears and Tuition");
        Event eventToday = new Event("test", 0, TODAY, TODAY);
        user.addEventToUsersList(eventToday);
        ArrayList<Event> expected = new ArrayList<>(Collections.singletonList(eventToday));

        assertEquals(1, getTodaysEvents(user).size());
        assertEquals(expected, getTodaysEvents(user));
    }

    /**
     * This tests the method given a user that only has 2 events and they also happen today
     */
    @Test
    public void Test2EventOfToday() {
        UserManager user = new UserManager("test user", COURSES, "U of Tears and Tuition");

        Event eventToday1 = new Event("test", 0, TODAY, TODAY);
        Event eventToday2 = new Event("test", 0, TODAY, TODAY);

        user.addEventToUsersList(eventToday1);
        user.addEventToUsersList(eventToday2);
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(eventToday1, eventToday2));

        assertEquals(2, getTodaysEvents(user).size());
        assertEquals(expected, getTodaysEvents(user));
    }

    @Test(timeout = 1000)
    public void TestMixedEvents() {
        int iter = rand.nextInt(50)+30;

        // Iterates by iter amount of times
        for (int i = 0; i < iter; i++) {
            // Instantiate variables for use by assertEquals
            ArrayList<Event> expected = new ArrayList<>();
            UserManager user = new UserManager("test user", COURSES, "U of Tears and Tuition");

            // Loop that creates and adds events that happens today
            for (int j = 0; j < rand.nextInt(50)+1; j++) {
                Event e = new Event("test", rand.nextInt(2), TODAY, TODAY);
                expected.add(e);
                user.addEventToUsersList(e);
            }
            // Loop that creates and adds event that happens in the future
            for (int a = 0; a < rand.nextInt(20); a++) {
                Event e = new Event(
                        "test",
                        rand.nextInt(2),
                        TODAY.plusMonths(rand.nextInt(5)+1),
                        TODAY.plusMonths(rand.nextInt(5)+1)
                );
                user.addEventToUsersList(e);
            }
            // Loop that creates and adds event that happens in the past
            for (int a = 0; a < rand.nextInt(20); a++) {
                Event e = new Event(
                        "test",
                        rand.nextInt(2),
                        TODAY.minusMonths(rand.nextInt(5)+1),
                        TODAY.minusMonths(rand.nextInt(5)+1)
                );
                user.addEventToUsersList(e);
            }

            assertEquals(expected.size(), getTodaysEvents(user).size());
            assertEquals(expected, getTodaysEvents(user));
        }
    }
}
