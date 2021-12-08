package com.generic.plannr.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClient extends SQLiteOpenHelper {

    private static final String createUserInfoTable = "CREATE TABLE userinfo(ID INTEGER " +
            "PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, " +
            "LOGGED_IN INTEGER, INCOME REAL)";

    private static final String createEventTable = "CREATE TABLE events(ID INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, NAME TEXT, PRIORITY INTEGER, START_DATE TEXT, END_DATE TEXT, " +
            "EVENT_TYPE TEXT, COURSE TEXT, LOCATION TEXT, USER_ID INTEGER)";

    private static final String createExpenseTable = "CREATE TABLE expenses(ID INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, NAME TEXT, VALUE DOUBLE, USER_ID INTEGER)";

    public SQLiteDatabase db;

    /**
     * Construct the official database for the application consisting
     * of three separate tables for user info, events and expenses
     *
     * @param context the Context object which specifies the
     *                application environment that requires the database
     */

    public DatabaseClient(Context context) {
        super(context, "PlannrDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserInfoTable);
        db.execSQL(createEventTable);
        db.execSQL(createExpenseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS userinfo");
        db.execSQL("DROP TABLE IF EXISTS events");
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }


}
