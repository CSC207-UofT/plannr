package com.generic.plannr.UseCaseTests;

import com.generic.plannr.UseCases.EventPriorityComparator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.generic.plannr.StaticTestVariables.*;

public class EventPriorityComparatorTest {

    EventPriorityComparator EPC;

    @Before
    public void setUp() {
        EPC = new EventPriorityComparator();
    }

    @Test(timeout = 50)
    public void TestHigherVLower1DegDiff() {
        assertEquals(-1, EPC.compare(priorityEventList.get(1), priorityEventList.get(0)));
    }

    @Test(timeout = 50)
    public void TestHigherVLower2DegDiff() {
        assertEquals(-1, EPC.compare(priorityEventList.get(2), priorityEventList.get(0)));
    }

    @Test(timeout = 50)
    public void TestLowerVHigher1DegDiff() {
        assertEquals(1, EPC.compare(priorityEventList.get(0), priorityEventList.get(1)));
    }

    @Test(timeout = 50)
    public void TestLowerVHigher2DegDiff() {
        assertEquals(1, EPC.compare(priorityEventList.get(0), priorityEventList.get(2)));
    }

    @Test(timeout = 50)
    public void TestEqualPriority() {
        assertEquals(0, EPC.compare(defaultEvent, priorityEventList.get(2)));
    }
}
