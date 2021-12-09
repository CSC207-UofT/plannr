package com.generic.plannr.androidtests.usecase;

import static org.junit.Assert.*;
import static com.generic.plannr.UseCases.AddExpense.addExpense;
import static com.generic.plannr.UseCases.UserCreator.signUp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.generic.plannr.Gateways.ExpenseGateway;
import com.generic.plannr.Gateways.UserGateway;
import com.generic.plannr.UseCases.AddExpense;
import com.generic.plannr.UseCases.CalculateExpenseBalance;
import com.generic.plannr.UseCases.ExpenseList;
import com.generic.plannr.UseCases.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@SuppressWarnings("ALL")
public class CalculateExpenseBalanceTest {
    Context context;
    ExpenseList expenseList;
    ExpenseGateway EG;
    UserGateway UG;
    AddExpense AE_INSTANCE;
    Random rand;
    UserManager UM;
    CalculateExpenseBalance CEB_INSTANCE;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        EG = new ExpenseGateway(context);
        EG.openDatabase();
        UG = new UserGateway(context);
        UG.openDatabase();
        signUp(
                UG,
                "test",
                "test@gmail.com",
                "Test@123"
        );
        expenseList = new ExpenseList();
        rand = new Random();
        AE_INSTANCE = new AddExpense(expenseList, EG);

        UM = new UserManager(UG);
        UG.updateIncome(10000000000000.0);
        UM.setUserIncome(10000000000000.0);

        CEB_INSTANCE = new CalculateExpenseBalance(expenseList, UM);
    }

    @After
    public void tearDown() {
        context.deleteDatabase("PlannrDatabase");
    }

    @Test
    public void TestCalculateExpenseBalance() {
        // Generate random integers to determine how many iterations it goes through
        int iteration = rand.nextInt(50) + 10;
        // Expected values
        double expectedAmt = 0.0;
        for (int i = 0; i < iteration; i++) {
            double amount = i * (rand.nextDouble() * 10 + 1);
            expectedAmt += amount;
            addExpense(
                    UG.getLoggedInUserID(),
                    "test expense",
                    amount
            );
        }
        assertEquals(expectedAmt, CEB_INSTANCE.getTotalExpense(), 0.0);
        assertEquals(Double.parseDouble(UM.getUsersIncome()) - expectedAmt, CEB_INSTANCE.getBalance(), 0.0);
    }
}
