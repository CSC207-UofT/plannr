package com.generic.plannr.androidtests.usecase;

import static org.junit.Assert.*;
import static com.generic.plannr.UseCases.UserCreator.signUp;
import static com.generic.plannr.UseCases.AddSchoolEvent.addSchoolEvent;
import static com.generic.plannr.UseCases.SortEvents.sortByPriority;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.AddSchoolEvent;
import com.generic.plannr.UseCases.EventPriorityComparator;
import com.generic.plannr.UseCases.EventPrioritySorter;
import com.generic.plannr.UseCases.EventList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@SuppressWarnings("ALL")
public class EventPrioritySorterTest {
    Context context;
    EventList eventList;
    EventGateway EG;
    UserGateway UG;
    AddSchoolEvent AS_INSTANCE;
    Random rand;
    EventPriorityComparator EPC;
    EventPrioritySorter EPS;

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
        EPC = new EventPriorityComparator();
        EPS = new EventPrioritySorter(EPC);
    }

    @After
    public void tearDown() throws Exception {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void TestCompare() {
        int iteration = rand.nextInt(20) + 5;
        ArrayList<Integer> expectedList = new ArrayList<>();

        for (int i = 0; i < iteration; i++) {
            int priority = rand.nextInt(2);
            LocalDateTime time = LocalDateTime.of(2021,
                    rand.nextInt(11) + 1,
                    rand.nextInt(27) + 1,
                    0,
                    0,
                    0);
            expectedList.add(priority);
            addSchoolEvent(
                    UG.getLoggedInUserID(),
                    "assessment",
                    "test event",
                    priority,
                    time,
                    time,
                    "CSC207"
            );
        }

        Collections.sort(expectedList);

        ArrayList<SchoolEvent> sortedEventList = EPS.sortEvents(EG.getAllEvents(UG.getLoggedInUserID()));
        ArrayList<SchoolEvent> sortedEventListBySortEvents = sortByPriority(EG.getAllEvents(UG.getLoggedInUserID()));
        for (int i = 0; i < iteration; i++) {
            assertEquals(((int)expectedList.get(i)), sortedEventList.get(i).getPriority());
            assertEquals(((int)expectedList.get(i)), sortedEventListBySortEvents.get(i).getPriority());
            assertEquals(sortedEventList.get(i).getPriority(), sortedEventListBySortEvents.get(i).getPriority());
        }

    }
}