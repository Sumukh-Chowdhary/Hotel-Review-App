package com.example.hotelreviewsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private final List<Hotel> hotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
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
        holder.hotelId.setText(hotel.getId());
        holder.hotelName.setText(hotel.getName());
        holder.hotelRating.setText(String.valueOf(hotel.getRating()));
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView hotelId, hotelName, hotelRating;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelId = itemView.findViewById(R.id.hotelId);
            hotelName = itemView.findViewById(R.id.hotelName);
            hotelRating = itemView.findViewById(R.id.hotelRating);
        }
    }
}
