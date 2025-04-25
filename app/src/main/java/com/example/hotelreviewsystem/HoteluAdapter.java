package com.example.hotelreviewsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HoteluAdapter extends RecyclerView.Adapter<HoteluAdapter.HotelViewHolder> {
    private final List<Hotel> hotelList;
    private final Context context;

    public HoteluAdapter(List<Hotel> hotelList, Context context) {
        this.hotelList = hotelList;
        this.context = context;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.hotelName.setText(hotel.getName());
        holder.hotelCity.setText(hotel.getCity());
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView hotelName, hotelCity;
        Button viewRatingsButton;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotelName);
            hotelCity = itemView.findViewById(R.id.hotelCity);
            viewRatingsButton = itemView.findViewById(R.id.viewRatingsButton);
        }
    }
}