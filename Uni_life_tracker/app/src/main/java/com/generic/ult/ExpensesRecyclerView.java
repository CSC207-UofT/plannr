package com.generic.ult;

import Entities.Expenses;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesRecyclerView extends RecyclerView.Adapter<ExpensesRecyclerView.MyViewHolder> {
        private ArrayList<Expenses> expensesArrayList;

        public ExpensesRecyclerView (ArrayList<Expenses> expensesArrayList){
            this.expensesArrayList = expensesArrayList;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView expenseTxt, amountTxt, addexpenseTxt,  addamountTxt;

            public MyViewHolder(final View view){
                super(view);
                expenseTxt = view.findViewById(R.id.expense_name);
                amountTxt = view.findViewById(R.id.expense_amount);
                addexpenseTxt = view.findViewById(R.id.add_expense_name);
                addamountTxt = view.findViewById(R.id.add_expense_amount);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_expenses_list, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String expenseName = expensesArrayList.get(position).getName();
            String amount = "$" + expensesArrayList.get(position).getValue();
            holder.expenseTxt.setText(expenseName);
            holder.amountTxt.setText(amount);
            holder.addexpenseTxt.setText(expenseName);
            holder.addamountTxt.setText(amount);
        }

        @Override
        public int getItemCount() {
            return expensesArrayList.size();
        }
    }

