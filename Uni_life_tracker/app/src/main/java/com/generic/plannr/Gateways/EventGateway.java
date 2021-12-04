package com.generic.plannr.Gateways;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.generic.plannr.Database.DatabaseClient;
import com.generic.plannr.Entities.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventGateway {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;
    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = dbclient.getWritableDatabase();
    }

    public void saveToDatabase(final Event event) {
        UserGateway ug = new UserGateway();
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", event.getName());
        cv.put("PRIORITY", event.getPriority());
        cv.put("START_DATE", event.getStartDate().format(DATEFORMAT));
        cv.put("END_DATE", event.getEndDate().format(DATEFORMAT));
        cv.put("USER_ID", ug.getLoggedInUserID());
        db.insert("events", null, cv);
    }

    /**
     * Get the Event associated with id eventID currently stored
     * in the database
     *
     * @param eventID the id of the Event we want to return
     *
     * @return the Event with id eventID
     */
    public Event getByID(final int eventID) {
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM events WHERE " +
                        "ID = " + eventID,
                null);

        if (!cur.moveToFirst()) {
            return null;
        }

        String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
        int priority = cur.getInt(cur.getColumnIndexOrThrow("PRIORITY"));
        LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);
        LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);

        return new Event(name, priority, start, end);

    }


    /**
     * Get the list of Events currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     *
     * @return a list of all events stored in the database for the user
     */
    public List<Event> getAllEvents(int userID) {
        List<Event> eventsList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM events WHERE " +
                        "USER_ID = " + userID, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
                    int priority = cur.getInt(cur.getColumnIndexOrThrow("PRIORITY"));
                    LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);
                    LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
                    Event event = new Event(name, priority, start, end);
                    eventsList.add(event);

                } while (cur.moveToNext());
            }
        }

        return eventsList;

    }

    /**
     * Get the list of Events that start at date currently stored in the
     * database for user with user id userID
     *
     * @param userID the user's id
     *
     * @return a list of all events that start at date stored in the
     * database for the user
     */
    public List<Event> getEventsByDate(LocalDate date, int userID) {
        List<Event> eventsList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM events WHERE " +
                "USER_ID = " + userID, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);

                    if (!(start.toLocalDate().isEqual(date))) {
                        continue;
                    }

                    String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
                    int priority = cur.getInt(cur.getColumnIndexOrThrow("PRIORITY"));
                    LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
                    Event event = new Event(name, priority, start, end);
                    eventsList.add(event);

                } while (cur.moveToNext());
            }
        }

        return eventsList;

    }

}
