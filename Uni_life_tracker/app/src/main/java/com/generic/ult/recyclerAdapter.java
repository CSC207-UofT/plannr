package com.generic.ult;

import Entities.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<Event> eventsList;

    public recyclerAdapter(ArrayList<Event> eventsList){
        this.eventsList = eventsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView eventTxt, timeTxt;

        public MyViewHolder(final View view){
            super(view);
            eventTxt = view.findViewById(R.id.text_event_name);
            timeTxt = view.findViewById(R.id.text_event_time);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_events, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String eventName = eventsList.get(position).getName();
        String time = eventsList.get(position).getEndDate().getHour() + ":" +
                eventsList.get(position).getEndDate().getMinute();
        holder.eventTxt.setText(eventName);
        holder.timeTxt.setText(time);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
