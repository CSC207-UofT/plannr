package Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserInfoDatabaseHelper extends SQLiteOpenHelper {

    private static final String createUserInfoTable = "CREATE TABLE userinfo(NAME TEXT, " +
            "UNIVERSITY TEXT, EMAIL TEXT, PASSWORD TEXT, LOGIN INTEGER)";
    private SQLiteDatabase db;

    public UserInfoDatabaseHelper(Context context) {
        super(context, "userInfoDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserInfoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userinfo");
        onCreate(db);
    }


    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = getWritableDatabase();
    }

    /**
     * Insert User Info into the database if initially empty
     *
     * @param name The user's name to be inserted
     * @param uni The user's university to be inserted
     * @param email The user's email to be inserted
     * @param password The user's password to be inserted
     */
    public void insertUserInfo(String name, String uni, String email, String password){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo",
                null);
        db.execSQL("UPDATE userinfo SET LOGIN = 0");
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("UNIVERSITY", uni);
        cv.put("EMAIL", email);
        cv.put("PASSWORD", password);
        cv.put("LOGIN", 1);
        db.insert("userinfo", null, cv);
    }

    /**
     * Update the User's name in the database
     *
     * @param name The user's name to be inserted
     */
    @SuppressLint("Recycle")
    public void updateName(String name){
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        db.update("userinfo", cv, "LOGIN = 1", null);
    }
    //UPDATE userinfo SET NAME =" + name + " WHERE NAME = " + nameA
    /**
     * Update the User's university in the database
     *
     * @param uni The user's university to be inserted
     */
    public void updateUni(String uni){
        ContentValues cv = new ContentValues();
        cv.put("UNIVERSITY", uni);
        db.update("userinfo", cv, "LOGIN = 1", null);
    }

    /**
     * Update the User's email in the database
     *
     * @param email The user's email to be inserted
     */
    public void updateEmail(String email){
        ContentValues cv = new ContentValues();
        cv.put("EMAIL", email);
        db.update("userinfo", cv, "LOGIN = 1", null);
    }

    /**
     * Update the User's password in the database
     *
     * @param password The user's password to be inserted
     */
    public void updatePassword(String password){
        ContentValues cv = new ContentValues();
        cv.put("PASSWORD", password);
        db.update("userinfo", cv, "LOGIN = 1", null);
    }

    /**
     * Get the User's name currently stored in the database
     *
     * @return the User's name in the database
     */
    public String getName(){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGIN = 1",
                null);
        if (cur.moveToFirst()) {
            // if database is not empty then get the User's name
            return cur.getString(cur.getColumnIndexOrThrow("NAME"));
        }

        return null;
    }

    /**
     * Get the User's university currently stored in the database
     *
     * @return the User's university in the database
     */
    public String getUni(){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGIN = 1",
                null);
        if (cur.moveToFirst()) {
            // if database is not empty then get the User's name
            return cur.getString(cur.getColumnIndexOrThrow("UNIVERSITY"));
        }

        return null;
    }

    /**
     * Get the User's email currently stored in the database
     *
     * @return the User's email in the database
     */
    public String getEmail(){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGIN = 1",
                null);
        if (cur.moveToFirst()) {
            // if database is not empty then get the User's name
            return cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        }

        return null;
    }

    /**
     * Get the User's password currently stored in the database
     *
     * @return the User's password in the database
     */
    public String getPassword(){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGIN = 1",
                null);
        if (cur.moveToFirst()) {
            // if database is not empty then get the User's name
            return cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));
        }

        return null;
    }

}
