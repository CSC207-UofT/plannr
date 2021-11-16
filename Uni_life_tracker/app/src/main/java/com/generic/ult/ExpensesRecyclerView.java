package com.generic.ult;

import Entities.Expense;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesRecyclerView extends RecyclerView.Adapter<ExpensesRecyclerView.MyViewHolder>{
    private ArrayList<Expense> expensesArrayList;

    public  ExpensesRecyclerView (ArrayList<Expense> expensesArrayList){
        this.expensesArrayList = expensesArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView expenseNameTxt, expenseAmountTxt;
        //private TextInputLayout expenseNameTxt, expenseAmountTxt;


        public MyViewHolder(final View view){
            super(view);
            expenseNameTxt = view.findViewById(R.id.tv_expense_name);
            expenseAmountTxt = view.findViewById(R.id.tv_expense_amount);
        }
    }

    @NonNull
    @Override
    public ExpensesRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expenses, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExpensesRecyclerView.MyViewHolder holder, int position) {
        String expenseName = expensesArrayList.get(position).getName();
        //Double expenseAmount = expensesArrayList.get(position).getValue();
        holder.expenseNameTxt.setText(expenseName);
        holder.expenseAmountTxt.setText("$" + expenseAmount);
    }

    @Override
    public int getItemCount() {
        return expensesArrayList.size();
    }
}

