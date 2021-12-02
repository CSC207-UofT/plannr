package com.generic.plannr;//import static org.junit.jupiter.api.Assertions.*;
import com.generic.plannr.Entities.Deadline;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
//import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    Deadline deadline;

    @Before
    public void setUp() { deadline = new Deadline("Phase 0",
            0,
            LocalDateTime.of(2021, Month.OCTOBER,15, 23, 59),
            "CSC207H1"); }

    @Test(timeout = 50)
    public void TestGetterMethods() {

        assertEquals("Phase 0", deadline.getName());
        assertEquals(0, deadline.getPriority());
        assertEquals("CSC207H1", deadline.getCourse());
        assertEquals(LocalDateTime.of(2021, Month.OCTOBER,15, 23, 59),
                     deadline.getEndDate());
    }

    @Test(timeout = 50)
    public void TestToString() {
        String deadlineString = "Deadline (high priority): " +
                "The assignment Phase 0 from CSC207H1 is due on " +
                "15-10-2021 23:59";
        assertEquals(deadlineString, deadline.toString());
    }

}