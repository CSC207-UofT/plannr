/* Plannr by Generic Name
 *
 * This file represents a ListExpenses class which displays expenses in a
 * RecyclerView, for activity_expenses.xml.
 */
package com.generic.plannr;

import com.generic.plannr.Entities.Expense;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ListExpenses extends RecyclerView.Adapter<ListExpenses.MyViewHolder> {
    private ArrayList<Expense> expensesArrayList;

    /**
     * Construct a ListExpenses, giving it an expensesArrayList.
     *
     * @param expensesArrayList An ArrayList of Expenses to be displayed
     */
    public ListExpenses(ArrayList<Expense> expensesArrayList) {
        this.expensesArrayList = expensesArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView expenseNameTxt, expenseAmountTxt;

        /**
         * Finds and sets TextView to display expense details.
         *
         * @param view a View for the device screen.
         */
        public MyViewHolder(final View view) {
            super(view);
            expenseNameTxt = view.findViewById(R.id.tv_expense_name);
            expenseAmountTxt = view.findViewById(R.id.tv_expense_amount);
        }
    }

    @NonNull
    @Override
    public ListExpenses.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expenses, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListExpenses.MyViewHolder holder, int position) {
        String expenseName = expensesArrayList.get(position).getName();
        double expenseAmount = expensesArrayList.get(position).getValue();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        holder.expenseNameTxt.setText(expenseName);
        holder.expenseAmountTxt.setText(formatter.format(expenseAmount));
    }

    @Override
    public int getItemCount() {
        return expensesArrayList.size();
    }
}

