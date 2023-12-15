package com.aplikasi.wanderplan.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.wanderplan.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    Context context;

    List<HotelData> hotelDataList;

    public HotelAdapter(Context context, List<HotelData> hotelDataList) {
        this.context = context;
        this.hotelDataList = hotelDataList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_hotel, parent, false);

        return new HotelViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {

        //holder.countryName.setText(hotelDataList.get(position).getCountryName());
        //holder.placeName.setText(hotelDataList.get(position).getPlaceName());
        //holder.price.setText(hotelDataList.get(position).getPrice());
        holder.placeImage.setImageResource(hotelDataList.get(position).getImageUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailHotel.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() { return hotelDataList.size(); }

    public static final class HotelViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);

        }

    }

}
