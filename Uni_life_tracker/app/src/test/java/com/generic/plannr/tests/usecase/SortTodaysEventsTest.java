package com.generic.plannr.tests.usecase;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.UseCases.EventDateComparator;
import com.generic.plannr.UseCases.UserManager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.generic.plannr.tests.StaticTestVariables.TODAY;
import static com.generic.plannr.UseCases.SortEvents.*;
import static org.junit.Assert.assertEquals;

public class SortTodaysEventsTest {

    UserManager user;
    SchoolEvent e1, e2, e3;
    ArrayList<String> COURSES;
    EventDateComparator EDC;

    @Before
    public void setUp() {
        COURSES = new ArrayList<>();
        EDC = new EventDateComparator();
        user = new UserManager("Test User", "test@gmail.com", "TestUser@123");

        e1 = new SchoolEvent("deadline", "test", 2, TODAY, TODAY, "CSC207");
        e2 = new SchoolEvent("assessment", "test", 1, TODAY.plusSeconds(1), TODAY.plusSeconds(1), "CSC311");
        e3 = new SchoolEvent("deadline", "test", 0, TODAY.plusSeconds(2), TODAY.plusSeconds(2), "ECO349");

        user.addEventToUsersList(e1);
        user.addEventToUsersList(e3);
        user.addEventToUsersList(e2);
    }

    @Test(timeout = 50)
    public void TestSortByDate() {
        ArrayList<SchoolEvent> expected = new ArrayList<>(Arrays.asList(e1, e2, e3));
        assertEquals(expected, sortByDate(user.viewEventList()));
    }

    @Test(timeout = 50)
    public void TestSortByPriority() {
        ArrayList<SchoolEvent> expected = new ArrayList<>(Arrays.asList(e3, e2, e1));
        assertEquals(expected, sortByPriority(user.viewEventList()));
    }

}
