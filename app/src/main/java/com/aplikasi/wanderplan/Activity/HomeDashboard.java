package com.aplikasi.wanderplan.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.wanderplan.R;

import java.util.ArrayList;
import java.util.List;

public class HomeDashboard extends AppCompatActivity {

        RecyclerView WisataRecycler, HotelRecycler;

        NewPlacesAdapter wisataAdapter;

        HotelAdapter hotelAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            //dummy data

            List<WisataData> wisataDataList = new ArrayList<>();
            wisataDataList.add(new WisataData(R.drawable.bali_homescreen));
            wisataDataList.add(new WisataData(R.drawable.mountain));
            wisataDataList.add(new WisataData(R.drawable.bali_homescreen));
            wisataDataList.add(new WisataData(R.drawable.mountain));
            wisataDataList.add(new WisataData(R.drawable.bali_homescreen));
            wisataDataList.add(new WisataData(R.drawable.mountain));

            setWisataRecycler(wisataDataList);

            List<HotelData> hotelDataList = new ArrayList<>();
            hotelDataList.add(new HotelData(R.drawable.detailhotel1));
            hotelDataList.add(new HotelData(R.drawable.detailhotel1));
            hotelDataList.add(new HotelData(R.drawable.detailhotel1));
            hotelDataList.add(new HotelData(R.drawable.detailhotel1));
            hotelDataList.add(new HotelData(R.drawable.detailhotel1));

            setHotelRecycler(hotelDataList);

        }

        private void setWisataRecycler(List<WisataData> wisataDataList) {
            WisataRecycler = findViewById(R.id.recent_recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            WisataRecycler.setLayoutManager(layoutManager);
            wisataAdapter = new NewPlacesAdapter(this, wisataDataList);
            WisataRecycler.setAdapter(wisataAdapter);

        }

        private void setHotelRecycler(List<HotelData> hotelDataList) {
            HotelRecycler = findViewById(R.id.top_places_recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            HotelRecycler.setLayoutManager(layoutManager);
            hotelAdapter = new HotelAdapter(this, hotelDataList);
            HotelRecycler.setAdapter(hotelAdapter);
        }

    }
