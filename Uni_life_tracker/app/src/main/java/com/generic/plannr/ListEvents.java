/* Plannr by Generic Name
 *
 * This file represents a ListEvents class which displays events in a
 * RecyclerView, for activity_main.xml and activity_school.xml.
 */
package com.generic.plannr;

import com.generic.plannr.Entities.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListEvents extends RecyclerView.Adapter<ListEvents.MyViewHolder>{
    ArrayList<Event> eventsList;

    /**
     * Construct a ListEvents, giving it an eventslist.
     *
     * @param eventsList    An ArrayList of Events to be displayed
     */
    public ListEvents(ArrayList<Event> eventsList){
        this.eventsList = eventsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvEventName, tvEventTime;

        /**
         * Finds and sets TextView to display event details.
         *
         * @param view  a View for the device screen.
         */
        public MyViewHolder(final View view){
            super(view);
            tvEventName = view.findViewById(R.id.tv_event_name);
            tvEventTime = view.findViewById(R.id.tv_event_time);
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
        LocalTime eventTime = eventsList.get(position).getEndDate().toLocalTime();

        holder.tvEventName.setText(eventName);
        holder.tvEventTime.setText(formatter.format(eventTime));
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
