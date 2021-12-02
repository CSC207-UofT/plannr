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

    /**
     * This test will randomly generate numbers and do assert equals on every iteration
     */
    @Test(timeout = 10000)
    public void TestRandom() {
        // Randomly defines how many times the loop runs
        int iter = rand.nextInt(40) + 20;

        while (iter > 0) {
            // The target date
            LocalDateTime target = date_.plusDays(rand.nextInt(3));
            // Counter to count how many times we added events of the same date
            int counter = 0;
            // Creates a list to store the events
            ArrayList<Event> events = new ArrayList<>();

            // Add events to the list
            for (int i = 0; i < 15; i++) {
                // To store the randomly generated date
                LocalDateTime startDate = date_.plusDays(rand.nextInt(3));
                if (startDate.compareTo(target) == 0) {
                    counter += 1;
                }
                events.add(new Event(
                        "test",
                        1,
                        startDate,
                        date_.plusDays(rand.nextInt(100))
                ));
            }

            // Compare the size of the return and
            assertEquals(counter, getEventsOfDate(events, target.toLocalDate()).size());

            iter -= 1;
        }
    }
}
