package com.generic.plannr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.generic.plannr.Entities.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventDatabaseHelper extends SQLiteOpenHelper {


    private static final String createEventTable = "CREATE TABLE events(NAME TEXT, PRIORITY " +
        "INTEGER, START_DATE TEXT, END_DATE TEXT, USER_EMAIL TEXT)";
    private SQLiteDatabase db;

    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public EventDatabaseHelper(Context context) {
        super(context, "eventDatabase", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createEventTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the older table if it exists
        db.execSQL("DROP TABLE IF EXISTS events");
        onCreate(db);
    }

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = getWritableDatabase();
    }


    /**
     * Insert an Event object into the database for the user with the
     * email userEmail
     *
     * @param event The Event to be inserted
     * @param userEmail The user's email that has the Event <event>
     */
    public void insertEvent(Event event, String userEmail){
        ContentValues cv = new ContentValues();
        cv.put("NAME", event.getName());
        cv.put("PRIORITY", event.getPriority());
        cv.put("START_DATE", event.getStartDate().format(DATEFORMAT));
        cv.put("END_DATE", event.getEndDate().format(DATEFORMAT));
        cv.put("USER_EMAIL", userEmail);
        db.insert("events", null, cv);
    }

    /**
     * Delete an Event object from the database
     *
     * @param event The Event to be deleted
     */
    public void deleteEvent(Event event){
        db.delete("events",  "NAME= ?",
                new String[] {String.valueOf(event.getName())});
    }

    /**
     * Return a list of all events currently in the database belonging to
     * the user with email userEmail
     *
     * @param userEmail The user's email
     *
     * @return returns the list of Event objects
     */
    public List<Event> getAllEvents(String userEmail){
        List<Event> eventList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query("events", null, null, null,
                    null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        if (!userEmail.equals(cur.getString(cur.getColumnIndexOrThrow("USER_EMAIL")))) {
                            continue;
                        }
                        LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);
                        LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
                        Event event = new Event(cur.getString(cur.getColumnIndexOrThrow("NAME")),
                                                cur.getInt(cur.getColumnIndexOrThrow("PRIORITY")),
                                                start,
                                                end);
                        eventList.add(event);

                    } while (cur.moveToNext());

                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }

        return eventList;
    }


    /**
     * Return a list of all events which start at date currently in the database
     * belonging to the user with email userEmail
     *
     * @param date The given date
     * @param userEmail The user's email
     *
     * @return returns the list of Event objects which start at date
     */
    public List<Event> getEventsByDate(LocalDate date, String userEmail) {
        List<Event> eventList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query("events", null, null, null,
                    null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        if (!(userEmail.equals(cur.getString(cur.getColumnIndexOrThrow("USER_EMAIL"))))) {
                            continue;
                        }

                        LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("START_DATE")), DATEFORMAT);

                        if (!(start.toLocalDate().isEqual(date))) {
                            continue;
                        }

                        LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
                        Event event = new Event(cur.getString(cur.getColumnIndexOrThrow("NAME")),
                                cur.getInt(cur.getColumnIndexOrThrow("PRIORITY")),
                                start,
                                end);

                        eventList.add(event);

                    } while (cur.moveToNext());

                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }

        return eventList;
    }

}
