package com.generic.ult;

import Entities.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListEvents extends RecyclerView.Adapter<ListEvents.MyViewHolder>{
    private ArrayList<Event> eventsList;

    public ListEvents(ArrayList<Event> eventsList){
        this.eventsList = eventsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvEventName, tvEventTime;

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
        String eventName = eventsList.get(position).getName();
        String time = eventsList.get(position).getEndDate().getHour() + ":" +
                eventsList.get(position).getEndDate().getMinute();
        holder.tvEventName.setText(eventName);
        holder.tvEventTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
