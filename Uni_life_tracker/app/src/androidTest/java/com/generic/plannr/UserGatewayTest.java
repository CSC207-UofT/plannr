package com.generic.plannr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    UserManager um;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ug = new UserGateway(context);
        um = new UserManager("Test User",
                "test@gmail.com",
                "TestUser@123");
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
        ug.saveToDatabase(um.getUser());
        assertEquals("test@gmail.com", ug.getLoggedInEmail());
        assertEquals("Test User", ug.getLoggedInName());
        assertEquals("TestUser@123", ug.getPassword("test@gmail.com"));
    }

    @Test
    public void updateName() {
        ug.saveToDatabase(um.getUser());
        ug.updateName("Test User 2");
        assertEquals("Test User 2", ug.getLoggedInName());
        assertEquals("Test User 2", ug.getByID(ug.getLoggedInUserID()).getName());
    }

    @Test
    public void updatePassword() {
        ug.saveToDatabase(um.getUser());
        ug.updatePassword("TestUser@456");
        assertEquals("TestUser@456", ug.getPassword("test@gmail.com"));
    }

    @Test
    public void getByID() {
        ug.saveToDatabase(um.getUser());
        Integer userID = ug.getLoggedInUserID();
        assertEquals("Test User", ug.getByID(userID).getName());
        assertEquals("TestUser@123", ug.getByID(userID).getPassword());
        assertEquals("test@gmail.com", ug.getByID(userID).getEmail());
        assertEquals(0, ug.getByID(userID).getCourses().size());
    }

    @Test
    public void getByEmail() {
        ug.saveToDatabase(um.getUser());
        assertEquals("Test User", ug.getByEmail("test@gmail.com").getName());
        assertEquals("TestUser@123", ug.getByEmail("test@gmail.com").getPassword());
        assertEquals(0, ug.getByEmail("test@gmail.com").getCourses().size());
    }

    @Test
    public void getLoggedInUserID() {
        ug.saveToDatabase(um.getUser());
        Integer userID1 = ug.getLoggedInUserID();
        ug.saveToDatabase(um.createUser("Test User 2", "test2@gmail.com", "TestUser@456"));

        // Sets the Test User 1 as logged in
        ug.updateLoggedInUser("test@gmail.com");
        assertEquals(userID1, ug.getLoggedInUserID());

        // Sets the Test User 2 as logged in
        ug.updateLoggedInUser("test2@gmail.com");
        Integer userID2 = ug.getLoggedInUserID();
        assertEquals(userID2, ug.getLoggedInUserID());
        // Makes sure that the two user IDs are different
        assertNotEquals(userID1, userID2);
    }

    @Test
    public void getLoggedInName() {
        ug.saveToDatabase(um.getUser());
        ug.saveToDatabase(um.createUser("Test User 2", "test2@gmail.com", "TestUser@456"));

        // Sets the Test User 1 as logged in
        ug.updateLoggedInUser("test@gmail.com");
        assertEquals("Test User", ug.getLoggedInName());

        // Sets the Test User 2 as logged in
        ug.updateLoggedInUser("test2@gmail.com");
        assertEquals("Test User 2", ug.getLoggedInName());
    }

    @Test
    public void getLoggedInEmail() {
        ug.saveToDatabase(um.getUser());
        ug.saveToDatabase(um.createUser("Test User 2", "test2@gmail.com", "TestUser@456"));

        // Sets the Test User 1 as logged in
        ug.updateLoggedInUser("test@gmail.com");
        assertEquals("test@gmail.com", ug.getLoggedInEmail());

        // Sets the Test User 2 as logged in
        ug.updateLoggedInUser("test2@gmail.com");
        assertEquals("test2@gmail.com", ug.getLoggedInEmail());
    }

    @Test
    public void getPassword() {
    }

    // updateLoggedInUser is not tested as it's tested by previous tests already

    @Test
    public void uniqueEmail() {
    }

    @Test
    public void getAllUsers() {
    }
}