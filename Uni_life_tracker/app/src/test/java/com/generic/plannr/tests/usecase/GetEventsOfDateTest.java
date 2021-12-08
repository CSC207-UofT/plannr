package com.generic.plannr.tests.usecase;

import org.junit.*;

import static org.junit.Assert.*;
import static com.generic.plannr.tests.StaticTestVariables.defaultDate;
import static com.generic.plannr.UseCases.GetEventsOfDate.getEventsOfDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import com.generic.plannr.Entities.SchoolEvent;

public class GetEventsOfDateTest {
    Random rand;

    @Before
    public void setUp() {
        rand = new Random();
    }

    /**
     * This tests the basic functionality of the UseCase GetEventsOfDate
     */
    @Test
    public void TestBasicFunctions() {
        // Empty arraylist to store events for testing
        ArrayList<SchoolEvent> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            events.add(new SchoolEvent("deadline", "test", 1, defaultDate, defaultDate, "CSC207"));
        }
        // Add an event that is 1 day later
        events.add(new SchoolEvent("deadline", "test", 1, defaultDate.plusDays(1), defaultDate, "CSC207"));

        // Assert Equals
        assertEquals(10, getEventsOfDate(events, defaultDate.toLocalDate()).size());
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
            LocalDateTime target = defaultDate.plusDays(rand.nextInt(3));
            // Counter to count how many times we added events of the same date
            int counter = 0;
            // Creates a list to store the events
            ArrayList<SchoolEvent> events = new ArrayList<>();

            // Add events to the list
            for (int i = 0; i < 15; i++) {
                // To store the randomly generated date
                LocalDateTime startDate = defaultDate.plusDays(rand.nextInt(3));
                if (startDate.compareTo(target) == 0) {
                    counter += 1;
                }
                events.add(new SchoolEvent(
                        "deadline",
                        "test",
                        1,
                        startDate,
                        defaultDate.plusDays(rand.nextInt(100)),
                        "CSC207"
                ));
            }

            // Compare the size of the return and
            assertEquals(counter, getEventsOfDate(events, target.toLocalDate()).size());

            iter -= 1;
        }
    }
}
