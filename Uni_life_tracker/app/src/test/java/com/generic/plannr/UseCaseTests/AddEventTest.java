package com.generic.plannr.UseCaseTests;

import org.junit.*;

import static com.generic.plannr.UseCases.AddEvent.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.generic.plannr.UseCases.UserManager;

public class AddEventTest {
    LocalDateTime startDate;

    @Before
    public void setUp() {
        startDate = LocalDateTime.of(2021, 1,1,1,1,1);
    }

    @Test
    public void TestAddAssessment() {
        // Instantiate a new UserManager
        UserManager user = new UserManager("Test User", "test@gmail.com", "TestUser@123");

        addAssessment(user, "Assessment 1", 1, startDate, startDate, "CSC207");

        // Test if the assessment is successfully added
        assertEquals("Assessment 1", user.viewEventList().get(0).getName());
        assertEquals(1, user.viewEventList().get(0).getPriority());
        assertEquals(0, user.viewEventList().get(0).getStartDate().compareTo(startDate));
        assertEquals(0, user.viewEventList().get(0).getEndDate().compareTo(startDate));
        assertEquals("CSC207", ((Assessment)user.viewEventList().get(0)).getCourse());
    }

    @Test
    public void TestAddStudySession() {
        // Instantiate a new UserManager
        UserManager user = new UserManager("Test User", "test@gmail.com", "TestUser@123");
        ArrayList<String> participants = new ArrayList<>(Arrays.asList("Kathy", "Dana", "Bolade", "Sari", "Evgenia", "Daniel"));

        addStudySession(user, "Study Session 1", 1, startDate, startDate, "CSC207", "Robarts", participants);

        // Test if the assessment is successfully added
        assertEquals("Study Session 1", user.viewEventList().get(0).getName());
        assertEquals(1, user.viewEventList().get(0).getPriority());
        assertEquals(0, user.viewEventList().get(0).getStartDate().compareTo(startDate));
        assertEquals(0, user.viewEventList().get(0).getEndDate().compareTo(startDate));
        assertEquals("CSC207", ((StudySession)(user.viewEventList()).get(0)).getCourse());
        assertEquals("Robarts", ((StudySession)(user.viewEventList()).get(0)).getLocation());
        assertEquals(((StudySession) (user.viewEventList()).get(0)).getParticipants(), participants);
    }

    @Test
    public void TestAddClass() {
        // Instantiate a new UserManager
        UserManager user = new UserManager("Test User", "test@gmail.com", "TestUser@123");

        addClass(user, "Class 1", 1, startDate, startDate, "CSC207", "Bahen");

        // Test if the assessment is successfully added
        assertEquals("Class 1", user.viewEventList().get(0).getName());
        assertEquals(1, user.viewEventList().get(0).getPriority());
        assertEquals(0, user.viewEventList().get(0).getStartDate().compareTo(startDate));
        assertEquals(0, user.viewEventList().get(0).getEndDate().compareTo(startDate));
        assertEquals("CSC207", ((Class)user.viewEventList().get(0)).getCourse());
        assertEquals("Bahen", ((Class)user.viewEventList().get(0)).getLocation());
    }
}
