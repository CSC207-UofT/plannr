package com.generic.plannr.Gateways;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.generic.plannr.Database.DatabaseClient;
import com.generic.plannr.Entities.Expense;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ExpenseGateway implements ExpenseGatewayInterface {

    public SQLiteDatabase db;
    public DatabaseClient dbclient;

    public ExpenseGateway(Context context) {
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
     * Insert the given expense into the database
     *
     * @param expense The Expense to be inserted
     * @param userID  The id of the User
     */
    public void saveToDatabase(final Expense expense, final int userID) {
        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", expense.getName());
        cv.put("VALUE", expense.getValue());
        cv.put("USER_ID", userID);
        db.insert("expenses", null, cv);
    }

    /**
     * Get the Expense associated with id expenseID currently stored
     * in the database
     *
     * @param expenseID the id of the Expense we want to return
     * @return the Expense with id expenseID
     */
    public Expense getByID(final int expenseID) {
        openDatabase();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM expenses WHERE " +
                        "ID = " + expenseID,
                null);

        if (!cur.moveToFirst()) {
            return null;
        }

        int userID = cur.getInt(cur.getColumnIndexOrThrow("USER_ID"));
        String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
        double value = cur.getDouble(cur.getColumnIndexOrThrow("VALUE"));

        return new Expense(userID, name, value);

    }


    /**
     * Get the list of Expenses currently stored in the database for user with
     * user id userID
     *
     * @param userID the user's id
     * @return a list of all expenses stored in the database for the user
     */
    public ArrayList<Expense> getAllExpenses(int userID) {
        openDatabase();
        ArrayList<Expense> expensesList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cur = db.rawQuery("SELECT * FROM expenses WHERE " +
                "USER_ID = " + userID, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndexOrThrow("NAME"));
                    double value = cur.getDouble(cur.getColumnIndexOrThrow("VALUE"));
                    Expense expense = new Expense(userID, name, value);
                    expensesList.add(expense);

                } while (cur.moveToNext());
            }
        }

        return expensesList;

    }

}
