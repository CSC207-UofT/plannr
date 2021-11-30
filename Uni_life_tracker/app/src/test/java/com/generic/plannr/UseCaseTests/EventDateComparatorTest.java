package com.generic.plannr.UseCaseTests;

import org.junit.*;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import Entities.Event;
import UseCases.EventDateComparator;

public class EventDateComparatorTest {
    EventDateComparator EDC;
    Event e1;
    Event e2;
    Event e3;

    @Before
    public void setUp() {
        EDC = new EventDateComparator();
        e1 = new Event(
                "test",
                1,
                LocalDateTime.of(2021, 1, 1, 1, 1, 1, 1),
                LocalDateTime.of(2021, 1, 1, 1, 1, 1, 1)
        );
        e2 = new Event(
                "test",
                1,
                LocalDateTime.of(2021, 1, 2, 1, 1, 1, 1),
                LocalDateTime.of(2021, 1, 2, 1, 1, 1, 1)
        );
        e3 = new Event(
                "test",
                1,
                LocalDateTime.of(2021, 1, 2, 1, 1, 1, 1),
                LocalDateTime.of(2021, 1, 2, 1, 1, 1, 1)
        );
    }

    @Test(timeout = 50)
    public void TestSoonerVLater() {
        assertEquals(-1, EDC.compare(e1, e2));
    }

    @Test(timeout = 50)
    public void TestLaterVSooner() {
        assertEquals(1, EDC.compare(e2, e1));
    }

    @Test(timeout = 50)
    public void TestSameDate() {
        assertEquals(0, EDC.compare(e2, e3));
    }

}
