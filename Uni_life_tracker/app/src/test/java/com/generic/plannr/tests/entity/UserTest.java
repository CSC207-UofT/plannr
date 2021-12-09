package com.generic.plannr.tests.entity;

import static org.junit.Assert.*;

import com.generic.plannr.Entities.User;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User(
                "test",
                "test@gmail.com",
                "Test@123"
        );
    }

    @Test
    public void getName() {
        assertEquals("test", user.getName());
    }

    @Test
    public void getEmail() {
        assertEquals("test@gmail.com", user.getEmail());
    }

    @Test
    public void getPassword() {
        assertEquals("Test@123", user.getPassword());
    }

    @Test
    public void getIncome() {
        assertEquals(0.0, user.getIncome(), 0.0);
    }

    @Test
    public void setName() {
        user.setName("Daniel");
        assertEquals("Daniel", user.getName());
    }

    @Test
    public void setPassword() {
        user.setPassword("Test@456");
        assertEquals("Test@456", user.getPassword());
    }

    @Test
    public void setIncome() {
        user.setIncome(100.0);
        assertEquals(100.0, user.getIncome(), 0.0);
    }
}
