package com.generic.plannr.UseCaseTests;

import static org.junit.Assert.assertEquals;
import static com.generic.plannr.UseCases.GetTodaysEvents.getTodaysEvents;
import static com.generic.plannr.StaticTestVariables.*;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.UserManager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
}
