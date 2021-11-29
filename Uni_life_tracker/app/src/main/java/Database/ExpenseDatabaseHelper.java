package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Entities.Expense;

public class ExpenseDatabaseHelper extends SQLiteOpenHelper {


    private static final String createExpenseTable = "CREATE TABLE expenses(NAME TEXT, VALUE DOUBLE)";
    private SQLiteDatabase db;


    public ExpenseDatabaseHelper(Context context) {
        super(context, "expenseDatabase", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createExpenseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the older table if it exists
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }

    /**
     * Open the database for reading or writing
     */
    public void openDatabase() {
        db = getWritableDatabase();
    }


    /**
     * Insert an Expense object into the database
     *
     * @param expense The Expense to be inserted
     */
    public void insertExpense(Expense expense){
        ContentValues cv = new ContentValues();
        cv.put("NAME", expense.getName());
        cv.put("VALUE", expense.getValue());
        db.insert("expenses", null, cv);
    }

    /**
     * Delete an Expense object from the database
     *
     * @param expense The Expense to be deleted
     */
    public void deleteExpense(Expense expense){
        db.delete("expenses",  "NAME= ?",
                new String[] {String.valueOf(expense.getName())});
    }

    /**
     * Return a list of all expenses currently in the database
     *
     * @return returns the list of Expense objects
     */
    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query("expenses", null, null, null,
                    null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        Expense expense = new Expense(cur.getString(cur.getColumnIndexOrThrow("NAME")),
                                cur.getDouble(cur.getColumnIndexOrThrow("VALUE")));
                        expenseList.add(expense);

                    } while (cur.moveToNext());

                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }

        return expenseList;
    }



}
