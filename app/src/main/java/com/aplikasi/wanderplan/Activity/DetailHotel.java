package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aplikasi.wanderplan.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailHotel extends Activity {
    Button btnBook;
    ImageView imageView;
    TextView tvTourName,tvTourDesc,tvTourPrice,tvTourLocation,tvTourRating;
    ObjectMapper om = new ObjectMapper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailhotel);

        btnBook = findViewById(R.id.btn_selectRoom);
        imageView = findViewById(R.id.imageView1);
        tvTourName = findViewById(R.id.tv_namaHotel);
        tvTourDesc = findViewById(R.id.tv_desc);
        tvTourPrice = findViewById(R.id.tv_harga);
        tvTourLocation = findViewById(R.id.tv_kota);
        tvTourRating = findViewById(R.id.tv_rating);

        Intent intent = getIntent();
        long tourId = intent.getLongExtra("tour_id",0);
        String tourName = intent.getStringExtra("tour_name");
        String tourImage = intent.getStringExtra("image");
        String tourLocation = Objects.requireNonNull(intent.getStringExtra("location")).replace(", Indonesia","");
        int tourPrice = intent.getIntExtra("price",0);
        long tourRate = intent.getLongExtra("rate",0);
        String tourDescription = intent.getStringExtra("description");

        updateUI(tourId, tourName, tourImage,tourLocation,tourPrice,tourRate,tourDescription);

        btnBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookRoom.class);
                v.getContext().startActivity(intent);
            }
        });

//        btnBack.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                goBackToPreviousActivity();
//            }
//        });
    }
    private void goBackToPreviousActivity() {
        // Create an Intent to navigate back to the PreviousActivity
        Intent intent = new Intent(this, HomeDashboard.class);

        // If you want to pass data back to the previous activity, you can use putExtra here
        // intent.putExtra("key", "value");

        // Start the PreviousActivity
        startActivity(intent);

        // Finish the current activity (CurrentActivity)
        finish();
    }

    private void updateUI(long tourId,String tourName,String tourImage,String tourLocation,int tourPrice,long tourRate,String tourDescription) {
        // Load image using Picasso or Glide
        int resourceId = getResources().getIdentifier(tourImage, "drawable", getPackageName());
        if (resourceId != 0) {
            Picasso.get().load(resourceId).into(imageView);
        }

        // Update other UI elements
        tvTourName.setText(tourName);
        tvTourDesc.setText(tourDescription);
        tvTourPrice.setText((String.valueOf(tourPrice) + "IDR"));
        tvTourLocation.setText(tourLocation);
        tvTourRating.setText(String.valueOf(tourRate));
        // Update other UI elements as needed
    }
}
