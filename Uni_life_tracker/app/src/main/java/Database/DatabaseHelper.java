package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Entities.Event;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String createEventTable = "CREATE TABLE events(NAME TEXT, PRIORITY " +
        "INTEGER, START_DATE TEXT, END_DATE TEXT)";
    private SQLiteDatabase db;

    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public DatabaseHelper(Context context) {
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
     * Close the database
     */
    public void closeDatabase() {
        db.close();
    }
    /**
     * Insert an Event object into the database
     *
     * @param event The Event to be inserted
     */
    public void insertEvent(Event event){
        ContentValues cv = new ContentValues();
        cv.put("NAME", event.getName());
        cv.put("PRIORITY", event.getPriority());
        cv.put("START_DATE", event.getStartDate().format(DATEFORMAT));
        cv.put("END_DATE", event.getEndDate().format(DATEFORMAT));
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
     * Return a list of all events currently in the database
     *
     * @return returns the list of Event objects
     */
    public List<Event> getAllEvents(){
        List<Event> eventList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query("events", null, null, null,
                    null, null, null, null);
            if (cur != null && cur.moveToFirst()) {
                do {
                    LocalDateTime start = LocalDateTime.parse(cur.getString(cur.getColumnIndex("START_DATE")), DATEFORMAT);
                    LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndex("END_DATE")), DATEFORMAT);
                    Event event = new Event(cur.getString(cur.getColumnIndex("NAME")),
                                            cur.getInt(cur.getColumnIndex("PRIORITY")),
                                            start,
                                            end);
                    eventList.add(event);

                } while (cur.moveToNext());

            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }

        return eventList;
    }
}
