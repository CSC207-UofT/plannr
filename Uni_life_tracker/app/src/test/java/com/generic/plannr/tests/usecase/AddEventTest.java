package com.generic.plannr.tests.usecase;

import org.junit.*;

import static com.generic.plannr.UseCases.AddEvent.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import com.generic.plannr.UseCases.UserManager;

public class AddEventTest {
    LocalDateTime startDate;

    @Before
    public void setUp() {
        startDate = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
    }

    @Test
    public void TestAddAssessment() {
        // Instantiate a new UserManager
        UserManager user = new UserManager("Test User", "test@gmail.com", "TestUser@123");

        addSchoolEvent(user, "assessment", "Assessment 1", 1, startDate, startDate, "CSC207");

        // Test if the assessment is successfully added
        assertEquals("Assessment 1", user.viewEventList().get(0).getName());
        assertEquals(1, user.viewEventList().get(0).getPriority());
        assertEquals(0, user.viewEventList().get(0).getStartDate().compareTo(startDate));
        assertEquals(0, user.viewEventList().get(0).getEndDate().compareTo(startDate));
        assertEquals("CSC207", user.viewEventList().get(0).getCourse());
    }

    @Test
    public void TestAddStudySession() {
        // Instantiate a new UserManager
        UserManager user = new UserManager("Test User", "test@gmail.com", "TestUser@123");

        addSchoolEvent(user, "study session", "Study Session 1", 1, startDate, startDate, "CSC207", "Robarts");

        // Test if the assessment is successfully added
        assertEquals("Study Session 1", user.viewEventList().get(0).getName());
        assertEquals(1, user.viewEventList().get(0).getPriority());
        assertEquals(0, user.viewEventList().get(0).getStartDate().compareTo(startDate));
        assertEquals(0, user.viewEventList().get(0).getEndDate().compareTo(startDate));
        assertEquals("CSC207", ((user.viewEventList()).get(0)).getCourse());
        assertEquals("Robarts", ((user.viewEventList()).get(0)).getLocation());
    }

    @Test
    public void TestAddClass() {
        // Instantiate a new UserManager
        UserManager user = new UserManager("Test User", "test@gmail.com", "TestUser@123");

        addSchoolEvent(user, "class", "Class 1", 1, startDate, startDate, "CSC207", "Bahen");

        // Test if the assessment is successfully added
        assertEquals("Class 1", user.viewEventList().get(0).getName());
        assertEquals(1, user.viewEventList().get(0).getPriority());
        assertEquals(0, user.viewEventList().get(0).getStartDate().compareTo(startDate));
        assertEquals(0, user.viewEventList().get(0).getEndDate().compareTo(startDate));
        assertEquals("CSC207", (user.viewEventList().get(0)).getCourse());
        assertEquals("Bahen", (user.viewEventList().get(0)).getLocation());
    }
}
