package com.generic.plannr.Gateways;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.generic.plannr.Database.DatabaseClient;
import com.generic.plannr.Entities.SchoolEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class EventGateway implements EventGatewayInterface {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;
    public final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public EventGateway(Context context) {
        dbclient = new DatabaseClient(context);
    }

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        // Opens the database. (Note: this is an example usage of the Façade Design Pattern)
        db = dbclient.getWritableDatabase();
    }

    /**
     * Insert the given event into the database
     *
     * @param event The SchoolEvent to be inserted
     */
    public void saveToDatabase(final SchoolEvent event, final int userID) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", event.getName());
        cv.put("PRIORITY", event.getPriority());
        cv.put("START_DATE", event.getStartDate().format(DATEFORMAT));
        cv.put("END_DATE", event.getEndDate().format(DATEFORMAT));
        cv.put("EVENT_TYPE", event.getEventType());
        cv.put("COURSE", event.getCourse());
        cv.put("LOCATION", event.getLocation());
        cv.put("USER_ID", userID);
        db.insert("events", null, cv);
    }

    /**
     * Get the SchoolEvent associated with id eventID currently stored
     * in the database
     *
     * @param eventID the id of the SchoolEvent we want to return
     * @return the SchoolEvent with id eventID
     */
    public SchoolEvent getByID(final int eventID) {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM events WHERE " +
                        "ID = " + eventID,
                null);

        if (!cur.moveToFirst()) {
            return null;
        }
        String eventType = cur.getString(cur.getColumnIndexOrThrow("EVENT_TYPE"));
        String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
        int priority = cur.getInt(cur.getColumnIndexOrThrow("PRIORITY"));
        LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);
        LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
        String location = cur.getString(cur.getColumnIndexOrThrow("LOCATION"));
        String course = cur.getString(cur.getColumnIndexOrThrow("COURSE"));
        int userID = cur.getInt(cur.getColumnIndexOrThrow("USER_ID"));


        if (location.equals("N/A")) {
            return new SchoolEvent(userID, eventType, name, priority, start, end, course);
        } else {
            return new SchoolEvent(userID, eventType, name, priority, start, end, course, location);
        }


    }


    /**
     * Get the list of SchoolEvents currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     * @return a list of all events stored in the database for the user
     */
    public ArrayList<SchoolEvent> getAllEvents(int userID) {
        openDatabase();
        ArrayList<SchoolEvent> eventsList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM events WHERE " +
                "USER_ID = " + userID, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    String eventType = cur.getString(cur.getColumnIndexOrThrow("EVENT_TYPE"));
                    String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
                    int priority = cur.getInt(cur.getColumnIndexOrThrow("PRIORITY"));
                    LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);
                    LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
                    String location = cur.getString(cur.getColumnIndexOrThrow("LOCATION"));
                    String course = cur.getString(cur.getColumnIndexOrThrow("COURSE"));

                    if (location.equals("N/A")) {
                        SchoolEvent event = new SchoolEvent(userID, eventType, name, priority, start, end, course);
                        eventsList.add(event);
                    } else {
                        SchoolEvent event = new SchoolEvent(userID, eventType, name, priority, start, end, course, location);
                        eventsList.add(event);
                    }


                } while (cur.moveToNext());
            }
        }

        return eventsList;

    }

}
