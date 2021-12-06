package com.generic.plannr.usecasetests;

import com.generic.plannr.UseCases.EventDateComparator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.generic.plannr.StaticTestVariables.*;

public class EventDateComparatorTest {
    EventDateComparator EDC;

    @Before
    public void setUp() {
        EDC = new EventDateComparator();
    }

    @Test(timeout = 50)
    public void TestSoonerVLater1DayDiff() {
        assertEquals(-1, EDC.compare(dayEventList.get(0), dayEventList.get(2)));
    }

    @Test(timeout = 50)
    public void TestSoonerVLater2DayDiff() {
        assertEquals(-1, EDC.compare(dayEventList.get(0), dayEventList.get(1)));
    }

    @Test(timeout = 50)
    public void TestLaterVSooner1DayDiff() {
        assertEquals(1, EDC.compare(dayEventList.get(1), dayEventList.get(2)));
    }

    @Test(timeout = 50)
    public void TestLaterVSooner2DayDiff() {
        assertEquals(1, EDC.compare(dayEventList.get(1), dayEventList.get(0)));
    }

    @Test(timeout = 50)
    public void TestSameDate() {
        assertEquals(0, EDC.compare(defaultEvent, dayEventList.get(0)));
    }

}
