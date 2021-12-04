package com.generic.plannr.Gateways;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.generic.plannr.Database.DatabaseClient;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserGateway {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = dbclient.getWritableDatabase();
    }

    public void saveToDatabase(final User user) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", user.getName());
        cv.put("UNIVERSITY", user.getSchool());
        cv.put("EMAIL", user.getEmail());
        cv.put("PASSWORD", user.getPassword());
        cv.put("LOGGEDIN", 1);
        db.insert("userinfo", null, cv);
    }

    /**
     * Get the User associated with id userID currently stored
     * in the database
     *
     * @param userID the id of the User we want to return
     *
     * @return the User with id userID
     */
    public User getByID(final int userID) {
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "ID = " + userID,
                null);

        if (!cur.moveToFirst()) {
            return null;
        }

        String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
        String uni = cur.getString(cur.getColumnIndexOrThrow("UNIVERSITY"));
        String email = cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        String password = cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));

        return new User(name, email, password, uni);

    }

    /**
     * Get the User associated with email userEmail currently stored
     * in the database
     *
     * @param userEmail the email of the User we want to return
     *
     * @return the User with email userEmail
     */
    public User getByEmail(final String userEmail) {
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "EMAIL = " + "'" + userEmail + "'",
                null);

        if (!cur.moveToFirst()) {
            return null;
        }

        String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
        String uni = cur.getString(cur.getColumnIndexOrThrow("UNIVERSITY"));
        String email = cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        String password = cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));

        return new User(name, email, password, uni);

    }

    /**
     * Get the list of Users currently stored in the database
     *
     *
     * @return a list of all users stored in the database
     */
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo",
                null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
                    String uni = cur.getString(cur.getColumnIndexOrThrow("UNIVERSITY"));
                    String email = cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
                    String password = cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));
                    User user = new User(name, email, password, uni);
                    usersList.add(user);

                } while (cur.moveToNext());
            }
        }

        return usersList;

    }

}
