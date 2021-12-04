package com.generic.plannr;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    public final View parentView;
    private final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        parentView = itemView.findViewById(R.id.parentView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}