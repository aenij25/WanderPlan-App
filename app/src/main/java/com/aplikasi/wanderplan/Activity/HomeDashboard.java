package com.aplikasi.wanderplan.Activity;

import static com.aplikasi.wanderplan.R.id.top_places_recycler;

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
            wisataDataList.add(new WisataData(R.drawable.imgwisata_komodo_island_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_raja_ampat_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_gili_trawangan_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_lombok_mandalika_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_bukit_marinding_waingapu_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_bunaken_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_taman_nasional_bromo_semeru_1));
            wisataDataList.add(new WisataData(R.drawable.imgwisata_danau_toba_1));


            setWisataRecycler(wisataDataList);

            List<HotelData> hotelDataList = new ArrayList<>();
            hotelDataList.add(new HotelData(R.drawable.detailhotel1));
            hotelDataList.add(new HotelData(R.drawable.imghotel_adiwana_bisma_1));
            hotelDataList.add(new HotelData(R.drawable.imghotel_g_h_universal_1));
            hotelDataList.add(new HotelData(R.drawable.imghotel_hotel_tentrem_semarang_1));
            hotelDataList.add(new HotelData(R.drawable.imghotel_padma_resort_ubud_1));
            hotelDataList.add(new HotelData(R.drawable.imghotel_the_kayon_jungle_resort_1));
            hotelDataList.add(new HotelData(R.drawable.imghotel_the_seminyak_beach_resort_1));

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
            HotelRecycler = findViewById(top_places_recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            HotelRecycler.setLayoutManager(layoutManager);
            hotelAdapter = new HotelAdapter(this, hotelDataList);
            HotelRecycler.setAdapter(hotelAdapter);
        }

    }
