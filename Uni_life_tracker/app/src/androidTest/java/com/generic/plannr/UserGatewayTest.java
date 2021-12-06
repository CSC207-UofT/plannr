package com.generic.plannr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserGatewayTest {
    UserGateway ug;
    Context context;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ug = new UserGateway(context);
    }

    @After
    public void after() {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void openDatabase() {
        ug.openDatabase();
        assertNull(ug.getLoggedInUserID());
        assertEquals(0, ug.getAllUsers().size());
    }

    @Test
    public void saveToDatabase() {
        UserManager user = new UserManager("Test User",
                "test@gmail.com",
                "TestUser@123");
        ug.saveToDatabase(user.getUser());
        assertEquals("test@gmail.com", ug.getLoggedInEmail());
        assertEquals("Test User", ug.getLoggedInName());
        assertEquals("TestUser@123", ug.getPassword("test@gmail.com"));
    }

    @Test
    public void updateName() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void getByID() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getLoggedInUserID() {
    }

    @Test
    public void getLoggedInName() {
    }

    @Test
    public void getLoggedInEmail() {
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void updateLoggedInUser() {
    }

    @Test
    public void uniqueEmail() {
    }

    @Test
    public void getAllUsers() {
    }
}