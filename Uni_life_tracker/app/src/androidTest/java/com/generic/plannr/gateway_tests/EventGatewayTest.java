package com.generic.plannr.gateway_tests;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.User;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class EventGatewayTest {
    EventGateway eventGateway;
    UserGateway ug;
    Context context;
    User user;
    LocalDateTime time;

    @Before
    public void setUp() throws Exception {
        time = LocalDateTime.now();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        eventGateway = new EventGateway(context);
        ug = new UserGateway(context);
        eventGateway.openDatabase();
        ug.openDatabase();
        user = new User(
                "test",
                "test@gmail.com",
                "Test@123"
        );
        ug.saveToDatabase(user);
    }

    @After
    public void after() {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void openDatabase() {
        assertEquals(0, eventGateway.getAllEvents(ug.getLoggedInUserID()).size());
    }

    @Test
    public void saveToDatabase() {
        Event e = new Event(
                "test event 1",
                0,
                time,
                time
        );
        eventGateway.saveToDatabase(e, ug.getLoggedInUserID());
        assertEquals(1, eventGateway.getAllEvents(ug.getLoggedInUserID()).size());
        assertEquals(
                "test event 1",
                eventGateway.getEventsByDate(
                        time.toLocalDate(),
                        ug.getLoggedInUserID()
                ).get(0).getName()
        );
    }

    @Test
    public void getByID() {
    }
}