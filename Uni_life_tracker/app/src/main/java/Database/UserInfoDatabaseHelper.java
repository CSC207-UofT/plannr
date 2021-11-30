package Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserInfoDatabaseHelper extends SQLiteOpenHelper {

    private static final String createUserInfoTable = "CREATE TABLE userinfo(NAME TEXT, " +
            "UNIVERSITY TEXT, EMAIL TEXT, PASSWORD TEXT, LOGGEDIN INTEGER)";
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
        db.execSQL("UPDATE userinfo SET LOGGEDIN = 0");
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("UNIVERSITY", uni);
        cv.put("EMAIL", email);
        cv.put("PASSWORD", password);
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
        ContentValues cv = new ContentValues();
        cv.put("UNIVERSITY", uni);
        db.update("userinfo", cv, "LOGGEDIN = 1", null);
    }

    /**
     * Update the User's email in the database
     *
     * @param email The user's email to be inserted
     */
    public void updateEmail(String email){
        ContentValues cv = new ContentValues();
        cv.put("EMAIL", email);
        db.update("userinfo", cv, "LOGGEDIN = 1", null);
    }

    /**
     * Update the User's password in the database
     *
     * @param password The user's password to be inserted
     */
    public void updatePassword(String password){
        ContentValues cv = new ContentValues();
        cv.put("PASSWORD", password);
        db.update("userinfo", cv, "LOGGEDIN = 1", null);
    }

    /**
     * Get the current logged in user's name currently stored in the database
     *
     * @return the logged in user's name in the database
     */
    public String getLoggedInName(){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGGEDIN = 1",
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
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGGEDIN = 1",
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
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE LOGGEDIN = 1",
                null);
        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("EMAIL"));
        }

        return null;
    }



    /**
     * Get the password associated with email currently stored in the database if email
     * is stored
     *
     * @return the user email's password from the database
     */
    public String getPassword(String input){
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE EMAIL = " + '"' + input + '"', null);

        if (cur.moveToFirst()) {
            return cur.getString(cur.getColumnIndexOrThrow("PASSWORD"));
        }

        return "";
    }
    /**
     * Searches through database for email to see if user had already signed up
     *
     * @return true if the email is not found in the database and false otherwise
     */
    @SuppressLint("Recycle")
    public boolean uniqueEmail(String input) {
        Cursor cur = db.rawQuery("SELECT * FROM userinfo WHERE EMAIL = " + '"' + input + '"', null);

        return !cur.moveToFirst();
    }

    /**
     * Set which account is currently logged in in the database
     */
    @SuppressLint("Recycle")
    public void updateLoggedInUser(String input) {
        db.execSQL("UPDATE userinfo SET LOGGEDIN = 0");
        ContentValues cv = new ContentValues();
        cv.put("LOGGEDIN", 1);
        db.update("userinfo", cv, "EMAIL = " + "'" + input + "'", null);
    }

}
