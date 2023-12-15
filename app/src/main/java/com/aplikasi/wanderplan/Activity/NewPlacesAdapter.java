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

public class NewPlacesAdapter extends RecyclerView.Adapter<NewPlacesAdapter.NewPlacesViewHolder> {

    Context context;

    List<WisataData> wisataDataList;

    public NewPlacesAdapter(Context context, List<WisataData> wisataDataList) {
        this.context = context;
        this.wisataDataList = wisataDataList;
    }

    @NonNull
    @Override
    public NewPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.new_places, parent, false);

        return new NewPlacesViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull NewPlacesViewHolder holder, int position) {

        holder.PlaceImage.setImageResource(wisataDataList.get(position).getImageUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, DetailWisata.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() { return wisataDataList.size(); }
    public static final class NewPlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView PlaceImage;

        public NewPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            PlaceImage = itemView.findViewById(R.id.Place_image);
        }

    }

}
