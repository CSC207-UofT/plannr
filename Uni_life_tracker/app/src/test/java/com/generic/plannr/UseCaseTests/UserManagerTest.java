package com.generic.plannr.UseCaseTests;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.UserManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.generic.plannr.StaticTestVariables.COURSES;
import static com.generic.plannr.StaticTestVariables.dayEventList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UserManagerTest {
    UserManager user;

    @Before
    public void setUp() {
        user = new UserManager("Test User", "test@gmail.com", "TestUser@123");
    }

    @Test
    public void TestConstructor() {
        assertEquals(0, user.viewCourses().size());
        assertEquals(new ArrayList<>(), user.getExpenses());
        assertEquals("Test User", user.getUsersName());
        assertEquals(0.0, user.calculateBalance());
    }

    @Test
    public void TestViewEventListEmpty() {
        assertEquals(new ArrayList<>(), user.viewEventList());
    }

    /**
     * This tests both the addEventToUserList and viewEventList methods
     */
    @Test
    public void TestAddEventToUserList() {
        for (Event e : dayEventList) {
            user.addEventToUsersList(e);
        }
        assertEquals(dayEventList.size(), user.viewEventList().size());
        assertEquals(dayEventList, user.viewEventList());
    }

    @Test
    public void TestChangeUsersName() {
        user.changeUsersName("Daniel");
        assertEquals("Daniel", user.getUsersName());
    }

    @Test
    public void TestAddCourseToUsersList() {
        user.addCourseToUsersList("CSC999");
        assertEquals(1, user.viewCourses().size());
        assertTrue(user.viewCourses().contains("CSC999"));
    }

    @Test
    public void TestAddExpense() {
        user.addExpense(10.0, "test");
        assertEquals(-10.0, user.calculateBalance());
    }
}
