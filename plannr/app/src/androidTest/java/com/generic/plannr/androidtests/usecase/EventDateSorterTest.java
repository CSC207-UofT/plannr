package com.generic.plannr.androidtests.usecase;

import static org.junit.Assert.*;
import static com.generic.plannr.UseCases.UserCreator.signUp;
import static com.generic.plannr.UseCases.AddSchoolEvent.addSchoolEvent;
import static com.generic.plannr.UseCases.SortEvents.sortByDate;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Entities.SchoolEvent;
import com.generic.plannr.Gateways.EventGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.AddSchoolEvent;
import com.generic.plannr.UseCases.EventDateComparator;
import com.generic.plannr.UseCases.EventDateSorter;
import com.generic.plannr.UseCases.EventList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@SuppressWarnings("ALL")
public class EventDateSorterTest {
    Context context;
    EventList eventList;
    EventGateway EG;
    UserGateway UG;
    AddSchoolEvent AS_INSTANCE;
    Random rand;
    EventDateComparator EDC;
    EventDateSorter EDS;

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
        EDS = new EventDateSorter(EDC);
    }

    @After
    public void tearDown() throws Exception {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void TestCompare() {
        int iteration = rand.nextInt(20) + 5;
        ArrayList<LocalDateTime> expectedList = new ArrayList<>();

        for (int i = 0; i < iteration; i++) {
            LocalDateTime time = LocalDateTime.of(2021,
                    rand.nextInt(11) + 1,
                    rand.nextInt(27) + 1,
                    0,
                    0,
                    0);
            expectedList.add(time);
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

        Collections.sort(expectedList);

        ArrayList<SchoolEvent> sortedEventList = EDS.sortEvents(EG.getAllEvents(UG.getLoggedInUserID()));
        ArrayList<SchoolEvent> sortedEventListBySortEvents = sortByDate(EG.getAllEvents(UG.getLoggedInUserID()));

        for (int i = 0; i < iteration; i++) {
            assertEquals(expectedList.get(i), sortedEventList.get(i).getStartDate());
            assertEquals(expectedList.get(i), sortedEventListBySortEvents.get(i).getStartDate());
            assertEquals(sortedEventList.get(i).getStartDate(), sortedEventListBySortEvents.get(i).getStartDate());
        }

    }
}