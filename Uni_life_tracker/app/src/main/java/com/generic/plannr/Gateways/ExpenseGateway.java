package com.generic.plannr.Gateways;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.generic.plannr.Database.DatabaseClient;
import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExpenseGateway implements ExpenseGatewayInterface {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;
    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = dbclient.getWritableDatabase();
    }

    public void saveToDatabase(final Expense expense) {
        openDatabase();
        UserGateway ug = new UserGateway();
        ContentValues cv = new ContentValues();
        cv.put("NAME", expense.getName());
        cv.put("VALUE", expense.getValue());
        cv.put("USER_ID", ug.getLoggedInUserID());
        db.insert("expenses", null, cv);
    }

    /**
     * Get the Expense associated with id expenseID currently stored
     * in the database
     *
     * @param expenseID the id of the Expense we want to return
     *
     * @return the Event with id eventID
     */
    public Expense getByID(final int expenseID) {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM expenses WHERE " +
                        "ID = " + expenseID,
                null);

        if (!cur.moveToFirst()) {
            return null;
        }

        String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
        double value = cur.getDouble(cur.getColumnIndexOrThrow("VALUE"));

        return new Expense(name, value);

    }


    /**
     * Get the list of Expenses currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     *
     * @return a list of all expenses stored in the database for the user
     */
    public List<Expense> getAllExpenses(int userID) {
        openDatabase();
        List<Expense> expensesList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM expenses WHERE " +
                "USER_ID = " + userID, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
                    double value = cur.getDouble(cur.getColumnIndexOrThrow("VALUE"));   LocalDateTime end = LocalDateTime.parse(cur.getString(cur.getColumnIndexOrThrow("END_DATE")), DATEFORMAT);
                    Expense expense = new Expense(name, value);
                    expensesList.add(expense);

                } while (cur.moveToNext());
            }
        }

        return expensesList;

    }

}
