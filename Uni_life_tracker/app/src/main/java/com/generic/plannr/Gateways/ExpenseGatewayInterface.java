package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.Event;
import com.generic.plannr.Entities.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseGatewayInterface {
    void openDatabase();
    void saveToDatabase(final Expense expense);
    Expense getByID(final int userID);
    List<Expense> getAllExpenses(int userID);
}
