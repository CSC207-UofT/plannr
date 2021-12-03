package com.generic.plannr.UseCaseTests;

import static junit.framework.TestCase.assertEquals;
import static com.generic.plannr.StaticTestVariables.*;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.UseCases.UserManager;

import org.junit.*;

import java.util.ArrayList;

public class UserManagerTest {
    UserManager user;

    @Before
    public void setUp() {
        user = new UserManager("Test User", COURSES, "U of T");
    }

    @Test
    public void TestConstructor() {
        assertEquals(COURSES, user.viewCourses());
        assertEquals(new ArrayList<>(), user.getExpenses());
        assertEquals("Test User", user.getUsersName());
        assertEquals("U of T", user.getUsersSchool());
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
}
