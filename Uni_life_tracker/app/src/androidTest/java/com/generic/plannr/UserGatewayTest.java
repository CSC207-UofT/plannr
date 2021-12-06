package com.generic.plannr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.generic.plannr.Gateways.UserGateway;

import org.junit.Before;
import org.junit.Test;

public class UserGatewayTest {
    UserGateway ug;
    Context context;

    @Before
    public void setUp() throws Exception {
        context = ApplicationProvider.getApplicationContext();
        ug = new UserGateway(context);
    }

    @Test
    public void openDatabase() {
        ug.openDatabase();
        assertNull(ug.getLoggedInUserID());
        assertEquals(0, ug.getAllUsers().size());
    }

    @Test
    public void saveToDatabase() {
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