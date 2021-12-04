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

public class UserGateway implements UserGatewayInterface {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = dbclient.getWritableDatabase();
    }

    /**
     * Insert user's info into the database
     *
     * @param user The User to be inserted
     */
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
     * Update the User's name in the database
     *
     * @param name The user's name to be inserted
     */
    @SuppressLint("Recycle")
    public void updateName(String name){
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        db.update("userinfo", cv, "LOGGEDIN = 1", null);
    }

    /**
     * Update the User's university in the database
     *
     * @param uni The user's university to be inserted
     */
    public void updateUni(String uni){
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UNIVERSITY", uni);
        db.update("userinfo", cv, "LOGGEDIN = 1", null);
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
        openDatabase();
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
        openDatabase();
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
     * Get the user id that is currently logged into the app
     *
     * @return the logged in user id
     */
    public Integer getLoggedInUserID() {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGEDIN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getInt(cur.getColumnIndexOrThrow("ID"));
        }

        return null;

    }

    /**
     * Get the current logged in user's name currently stored in the database
     *
     * @return the logged in user's name in the database
     */
    public String getLoggedInName(){
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGEDIN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("NAME"));
        }

        return null;
    }

    /**
     * Get the current logged in user's university currently stored in the database
     *
     * @return the logged in user's university in the database
     */
    public String getLoggedInUni(){
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGEDIN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("UNIVERSITY"));
        }

        return null;
    }

    /**
     * Get the current logged in user's email currently stored in the database
     *
     * @return the logged in user's email in the database
     */
    public String getLoggedInEmail(){
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGEDIN = 1",
                null);

        return cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
    }

    /**
     * Get the password associated with userEmail currently stored in the database
     *
     * @param userEmail the user's email
     *
     * @return the userEmail's password from the database
     */
    public String getPassword(String userEmail){
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                "EMAIL = " + '"' + userEmail + '"', null);

        return cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));
    }

    /**
     *
     * Set which account is currently logged in in the database
     *
     */
    public void updateLoggedInUser(String userEmail) {
        openDatabase();
        db.execSQL("UPDATE userinfo SET LOGGEDIN = 0");
        ContentValues cv = new ContentValues();
        cv.put("LOGGEDIN", 1);
        db.update("userinfo", cv, "EMAIL = " + "'" + userEmail + "'", null);

    }

    /**
     * Searches through database for email to see if user had already signed up
     *
     * @return true if the email is not found in the database and false otherwise
     */
    @SuppressLint("Recycle")
    public boolean uniqueEmail(String userEmail) {
        openDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE EMAIL = " + '"' +
                userEmail + '"', null);

        return !cur.moveToFirst();
    }

    /**
     * Get the list of Users currently stored in the database
     *
     *
     * @return a list of all users stored in the database
     */
    public List<User> getAllUsers() {
        openDatabase();
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
