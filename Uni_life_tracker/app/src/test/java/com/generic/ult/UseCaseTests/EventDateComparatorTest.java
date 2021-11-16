package com.generic.ult.UseCaseTests;

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
                LocalDateTime.of(2021, 1, 1, 1, 1, 1, 1)
        );
        e2 = new Event(
                "test",
                1,
                LocalDateTime.of(2021, 1, 2, 1, 1, 1, 1)
        );
        e1 = new Event(
                "test",
                1,
                LocalDateTime.of(2021, 1, 2, 1, 1, 1, 1)
        );
    }

}
