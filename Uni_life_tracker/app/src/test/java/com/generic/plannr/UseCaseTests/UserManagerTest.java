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
    public void TestViewEventListEmpty() {
        assertEquals(new ArrayList<>(), user.viewEventList());
    }
}
