package com.generic.ult.UseCaseTests;

import org.junit.*;

import static org.junit.Assert.*;
import static UseCases.GetEventsOfDate.getEventsOfDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import Entities.Event;

public class GetEventsOfDateTest {
    LocalDateTime date_;
    Random rand;
    @Before
    public void setUp() {
        date_ = LocalDateTime.of(2021, 1, 1, 1, 1, 1, 1);
        rand = new Random();
    }
    /**
     * This tests the basic functionality of the UseCase GetEventsOfDate
     */
    @Test
    public void TestBasicFunctions() {
        // Empty arraylist to store events for testing
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < 10;  i++) {
            events.add(new Event("test", 1, date_, date_));
        }
        // Add an event that is 1 day later
        events.add(new Event("test", 1, date_.plusDays(1), date_));

       // Assert Equals
        assertEquals(10, getEventsOfDate(events, date_.toLocalDate()).size());
    }

}
