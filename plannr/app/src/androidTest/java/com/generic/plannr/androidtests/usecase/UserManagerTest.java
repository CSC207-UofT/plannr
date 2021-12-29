package com.generic.plannr.androidtests.usecase;

import static org.junit.Assert.*;
import static com.generic.plannr.UseCases.UserCreator.signUp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {
    Context context;
    UserGateway UG;
    UserManager UM;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UG = new UserGateway(context);
        UG.openDatabase();
        signUp(
                UG,
                "test",
                "test@gmail.com",
                "Test@123"
        );
        UM = new UserManager(UG);
    }

    @After
    public void tearDown() {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void TestGetUsersName() {
        assertEquals("test", UM.getUsersName());
    }

    @Test
    public void TestGetUsersEmail() {
        assertEquals(UG.getLoggedInEmail(), UM.getUsersEmail());
    }

    @Test
    public void TestGetUsersPassword() {
        assertEquals(UG.getLoggedInPassword(), UM.getUsersPassword());
    }

    @Test
    public void TestGetUsersIncome() {
        assertEquals(UG.getLoggedInIncome(), UM.getUsersIncome());
    }

    @Test
    public void TestSetUserName() {
        UM.setUsersName("test 2");
        assertEquals("test 2", UM.getUsersName());
    }

    @Test
    public void TestSetUserPassword() {
        UM.setUsersPassword("Test@456");
        assertEquals(UG.getLoggedInPassword(), UM.getUsersPassword());
    }

    @Test
    public void TestSetUserIncome() {
        UM.setUserIncome(100.0);
        assertEquals(UG.getLoggedInIncome(), UM.getUsersIncome());
    }
}
