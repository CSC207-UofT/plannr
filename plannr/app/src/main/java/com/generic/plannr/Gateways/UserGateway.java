package com.generic.plannr.Gateways;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.generic.plannr.Database.DatabaseClient;
import com.generic.plannr.Entities.User;

import static java.sql.Types.NULL;

@SuppressWarnings("ALL")
public class UserGateway implements UserGatewayInterface {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;

    public UserGateway(Context context) {
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
     * Insert user's info into the database
     *
     * @param user The User to be inserted
     */
    public void saveToDatabase(final User user) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", user.getName());
        cv.put("EMAIL", user.getEmail());
        cv.put("PASSWORD", user.getPassword());
        cv.put("LOGGED_IN", 1);
        cv.put("INCOME", NULL);
        db.insert("userinfo", null, cv);
    }

    /**
     * Update the User's name in the database
     *
     * @param name The user's name to be inserted
     */
    @SuppressLint("Recycle")
    public void updateName(String name) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        db.update("userinfo", cv, "LOGGED_IN = 1", null);
    }

    /**
     * Update the User's password in the database
     *
     * @param password The user's password to be inserted
     */
    public void updatePassword(String password) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PASSWORD", password);
        db.update("userinfo", cv, "LOGGED_IN = 1", null);
    }

    /**
     * Update the User's income in the database
     *
     * @param income The user's income to be inserted
     */
    public void updateIncome(Double income) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("INCOME", income);
        db.update("userinfo", cv, "LOGGED_IN = 1", null);
    }

    /**
     * Get the User associated with id userID currently stored
     * in the database
     *
     * @param userID the id of the User we want to return
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
        String email = cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        String password = cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));

        return new User(name, email, password);

    }

    /**
     * Get the User associated with email userEmail currently stored
     * in the database
     *
     * @param userEmail the email of the User we want to return
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
        String email = cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        String password = cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));

        return new User(name, email, password);

    }

    /**
     * Get the user id that is currently logged into the app
     *
     * @return the logged-in user id
     */
    public Integer getLoggedInUserID() {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGED_IN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getInt(cur.getColumnIndexOrThrow("ID"));
        }

        return null;

    }

    /**
     * Get the current logged-in user's name currently stored in the database
     *
     * @return the logged-in user's name in the database
     */
    public String getLoggedInName() {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGED_IN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("NAME"));
        }

        return "";
    }

    /**
     * Get the current logged-in user's email currently stored in the database
     *
     * @return the logged in user's email in the database
     */
    public String getLoggedInEmail() {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGED_IN = 1",
                null);

        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        }

        return "";
    }

    /**
     * Get the current logged-in user's password currently stored in the database
     *
     * @return the logged-in user's password in the database
     */
    public String getLoggedInPassword() {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGED_IN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));
        }

        return "";
    }

    /**
     * Get the current logged-in user's income currently stored in the database
     *
     * @return the logged in user's income in the database
     */
    public String getLoggedInIncome() {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                        "LOGGED_IN = 1",
                null);

        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("INCOME"));
        }

        return "";
    }

    /**
     * Get the password associated with userEmail currently stored in the database
     *
     * @param userEmail the user's email
     * @return the userEmail's password from the database
     */
    public String getPassword(String userEmail) {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE " +
                "EMAIL = " + '"' + userEmail + '"', null);

        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));
        }

        return "";
    }

    /**
     * Set which account is currently logged in the database
     */
    public void updateLoggedInUser(String userEmail) {
        openDatabase();
        db.execSQL("UPDATE userinfo SET LOGGED_IN = 0");
        ContentValues cv = new ContentValues();
        cv.put("LOGGED_IN", 1);
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

}
