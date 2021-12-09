/* Plannr by Generic Name
 *
 * This file represents a CalendarViewHolder class which is used in a
 * RecyclerView, for activity_school.xml.
 */
package com.generic.plannr;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    /**
     * Constructs a CalendarViewHolder, giving it an onItemListener, days, and assigning itemViews.
     *
     * @param itemView       a View.
     * @param onItemListener a CalendarAdapter.onItemListener.
     * @param days           an ArrayList<LocalDate> of days.
     */
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(days.get(getAdapterPosition()));
    }
}