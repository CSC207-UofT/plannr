package com.generic.plannr.UseCaseTests;

import org.junit.*;

import static org.junit.Assert.*;

import static com.generic.plannr.UseCases.SortEvents.sortByDate;
import static com.generic.plannr.UseCases.SortEvents.sortByPriority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.EventDateComparator;

public class SortEventsTest {

    Event e1, e2, e3;
    ArrayList<String> COURSES;
    EventDateComparator EDC;
    ArrayList<Event> events;

    @Before
    public void setUp() {
        COURSES = new ArrayList<>();
        EDC = new EventDateComparator();

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
        events = new ArrayList<>(Arrays.asList(e1, e2, e3));
    }

    @Test(timeout = 50)
    public void TestSortByDate() {
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(e1, e2, e3));
        assertEquals(expected, sortByDate(events));
    }

    @Test(timeout = 50)
    public void TestSortByPriority() {
        ArrayList<Event> expected = new ArrayList<>(Arrays.asList(e3, e2, e1));
        assertEquals(expected, sortByPriority(events));
    }

}
