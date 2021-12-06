package com.generic.plannr.usecasetests;

import org.junit.*;

import static org.junit.Assert.*;

import static com.generic.plannr.UseCases.SortEvents.sortByDate;
import static com.generic.plannr.UseCases.SortEvents.sortByPriority;
import static com.generic.plannr.StaticTestVariables.*;

import java.util.ArrayList;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.EventDateComparator;

public class SortEventsTest {

    ArrayList<String> COURSES;
    EventDateComparator EDC;

    @Before
    public void setUp() {
        COURSES = new ArrayList<>();
        EDC = new EventDateComparator();
    }

    @Test(timeout = 50)
    public void TestSortByDate() {
        ArrayList<Event> sortedEvents = sortByDate(dayEventList);
        assertEquals(defaultDate, sortedEvents.get(0).getStartDate());
        assertEquals(defaultDate.plusDays(1), sortedEvents.get(1).getStartDate());
        assertEquals(defaultDate.plusDays(2), sortedEvents.get(2).getStartDate());
    }

    @Test(timeout = 50)
    public void TestSortByPriority() {
        ArrayList<Event> sortedEvents = sortByPriority(priorityEventList);
        assertEquals(0, sortedEvents.get(0).getPriority());
        assertEquals(1, sortedEvents.get(1).getPriority());
        assertEquals(2, sortedEvents.get(2).getPriority());
    }

}
