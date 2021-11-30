package com.generic.plannr.UseCaseTests;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.EventPriorityComparator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class EventPriorityComparatorTest {

    EventPriorityComparator EPC;
    LocalDateTime DATE;

    @Before
    public void setUp() {
        EPC = new EventPriorityComparator();
        DATE = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
    }

    @Test(timeout = 50)
    public void TestHigherVLower() {
        Event e1 = new Event("event 1", 1, DATE);
        Event e2 = new Event("event 1", 2, DATE);
        assertEquals(-1, EPC.compare(e1, e2));
    }

    @Test(timeout = 50)
    public void TestLowerVHigher() {
        Event e1 = new Event("event 1", 1, DATE);
        Event e2 = new Event("event 1", 2, DATE);
        assertEquals(1, EPC.compare(e2, e1));
    }

    @Test(timeout = 50)
    public void TestEqualPriority() {
        Event e1 = new Event("event 1", 1, DATE);
        Event e2 = new Event("event 1", 1, DATE);
        assertEquals(0, EPC.compare(e2, e1));
        assertEquals(0, EPC.compare(e1, e2));
    }
}
