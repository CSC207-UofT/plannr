//import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;
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

//    @Test(timeout = 50)
//    public void TestIsCompleted() { assertEquals(false, deadline.isCompleted()); }

    @Test(timeout = 50)
    public void TestToString() {
        String deadlineString = "Deadline (high priority): " +
                "The assignment Phase 0 from CSC207H1 is due on " +
                "15-10-2021 11:59 p.m.";
        assertEquals(deadlineString, deadline.toString());
    }

}