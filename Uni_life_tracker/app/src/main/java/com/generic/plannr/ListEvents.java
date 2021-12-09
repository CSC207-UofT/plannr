/* Plannr by Generic Name
 *
 * This file represents a ListEvents class which displays events in a
 * RecyclerView, for activity_main.xml and activity_school.xml.
 */
package com.generic.plannr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.generic.plannr.Entities.SchoolEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ListEvents extends RecyclerView.Adapter<ListEvents.MyViewHolder> {
    private ArrayList<SchoolEvent> eventsList;
    private RecyclerViewClickLister listener;

    /**
     * Construct a ListEvents, giving it an eventslist.
     *
     * @param eventsList An ArrayList of Events to be displayed
     */
    public ListEvents(ArrayList<SchoolEvent> eventsList, RecyclerViewClickLister listener) {
        this.eventsList = eventsList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvEventName, tvEventTime;

        /**
         * Finds and sets TextView to display event details.
         *
         * @param view a View for the device screen.
         */
        public MyViewHolder(final View view) {
            super(view);
            tvEventName = view.findViewById(R.id.tv_event_name);
            tvEventTime = view.findViewById(R.id.tv_event_time);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ListEvents.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_events, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListEvents.MyViewHolder holder, int position) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String eventName = eventsList.get(position).getName();
        LocalTime eventStartTime = eventsList.get(position).getStartDate().toLocalTime();
        LocalTime eventEndTime = eventsList.get(position).getEndDate().toLocalTime();
        String eventTime = formatter.format(eventStartTime) + " - " + formatter.format(eventEndTime);

        holder.tvEventName.setText(eventName);
        holder.tvEventTime.setText(eventTime);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public interface RecyclerViewClickLister {
        void onClick(View v, int position);
    }
}
