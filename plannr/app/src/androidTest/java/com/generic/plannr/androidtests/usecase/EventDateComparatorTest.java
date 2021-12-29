package com.generic.plannr.androidtests.usecase;

import static org.junit.Assert.*;
import static com.generic.plannr.UseCases.UserCreator.signUp;
import static com.generic.plannr.UseCases.AddSchoolEvent.addSchoolEvent;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.AddSchoolEvent;
import com.generic.plannr.UseCases.EventDateComparator;
import com.generic.plannr.UseCases.EventList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Random;

@SuppressWarnings("ALL")
public class EventDateComparatorTest {
    Context context;
    EventList eventList;
    EventGateway EG;
    UserGateway UG;
    AddSchoolEvent AS_INSTANCE;
    Random rand;
    EventDateComparator EDC;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        EG = new EventGateway(context);
        EG.openDatabase();
        UG = new UserGateway(context);
        UG.openDatabase();
        signUp(
                UG,
                "test",
                "test@gmail.com",
                "Test@123"
        );
        eventList = new EventList();
        rand = new Random();
        AS_INSTANCE = new AddSchoolEvent(eventList, EG);
        EDC = new EventDateComparator();
    }

    @After
    public void tearDown() throws Exception {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void TestCompare() {
        int iteration = rand.nextInt(10) + 20;

        for (int i = 0; i < iteration; i++) {
            LocalDateTime time = LocalDateTime.of(2021,
                    rand.nextInt(11) + 1,
                    rand.nextInt(27) + 1,
                    0,
                    0,
                    0);

            addSchoolEvent(
                    UG.getLoggedInUserID(),
                    "assessment",
                    "test event",
                    rand.nextInt(2),
                    time,
                    time,
                    "CSC207"
            );
        }
        for (SchoolEvent e1 : EG.getAllEvents(UG.getLoggedInUserID())) {
            for (SchoolEvent e2 : EG.getAllEvents(UG.getLoggedInUserID())) {
                if (e1.getStartDate().compareTo(e2.getStartDate()) < 0) {
                    assertEquals(-1, EDC.compare(e1, e2));
                } else if (e1.getStartDate().compareTo(e2.getStartDate()) == 0) {
                    assertEquals(0, EDC.compare(e1, e2));
                } else {
                    assertEquals(1, EDC.compare(e1, e2));
                }
            }
        }
    }
}