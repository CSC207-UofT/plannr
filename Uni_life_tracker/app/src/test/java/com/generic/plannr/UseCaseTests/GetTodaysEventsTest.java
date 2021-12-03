package com.generic.plannr.UseCaseTests;

import static org.junit.Assert.assertEquals;
import static com.generic.plannr.UseCases.GetTodaysEvents.getTodaysEvents;
import static com.generic.plannr.StaticTestVariables.*;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.UserManager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GetTodaysEventsTest {
    ArrayList<Event> events;
    UserManager user;

    @Before
    public void setUp() {
        events = new ArrayList<>();
        user = new UserManager("test user", COURSES, "U of Tears and Tuition");
    }

    @Test
    public void TestEmptyEventList() {
        assertEquals(new ArrayList<>(), getTodaysEvents(user));
    }

    /**
     * This tests the method given a user that only has one event and it also happens today
     */
    @Test
    public void Test1EventOfToday() {
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
        Event eventToday1 = new Event("test", 0, TODAY, TODAY);
        Event eventToday2 = new Event("test", 0, TODAY, TODAY);

        user.addEventToUsersList(eventToday1);
        user.addEventToUsersList(eventToday2);
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(eventToday1, eventToday2));

        assertEquals(2, getTodaysEvents(user).size());
        assertEquals(expected, getTodaysEvents(user));
    }
}
