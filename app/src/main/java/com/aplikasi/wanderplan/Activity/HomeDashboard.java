package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aplikasi.wanderplan.API.RetrofitClient;
import com.aplikasi.wanderplan.API.Services.AuthService;
import com.aplikasi.wanderplan.API.Services.TourService;
import com.aplikasi.wanderplan.Model.GlobalModel;
import com.aplikasi.wanderplan.Model.ViewModel.AccountViewModel;
import com.aplikasi.wanderplan.Model.api.Login.LoginRequest;
import com.aplikasi.wanderplan.Model.api.MessageModel;
import com.aplikasi.wanderplan.Model.data.Account.Account;
import com.aplikasi.wanderplan.R;
import com.aplikasi.wanderplan.Util.CustomToast;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDashboard extends Activity {

    private LinearLayout imageContainer1,imageContainer2;
    ImageView imgWisata1, imgHotel1;
    Activity thisActivity;
    View thisView;
    ObjectMapper om = new ObjectMapper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageContainer1 = findViewById(R.id.imageContainer1);
        imageContainer2 = findViewById(R.id.imageContainer2);
        RetrofitClient
                .getInstance()
                .create(TourService.class)
                .getAllTour()
                .enqueue(new Callback<MessageModel>() {
                    @Override
                    public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                        int statusCode = response.code();

                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            JsonNode resp = om.convertValue(response.body().getData(), JsonNode.class);
                            System.out.println(resp);
                            List tourList = om.convertValue(response.body().getData(),List.class);

                            // Add ImageViews dynamically based on the API response
                            for (Object tour : tourList) {
                                JsonNode tourMap = om.convertValue(tour,JsonNode.class);
                                if(tourMap.get("type").asText().equals("tour")){
                                    createAndAddImageViewWisata(tourMap.get("image").asText(),tourMap.get("id").asLong());
                                }else{
                                    createAndAddImageViewHotel(tourMap.get("image").asText(),tourMap.get("id").asLong());
                                }
                            }
                        } else {
                            try {
                                assert response.body() != null;
                                String errorBody = response.body().getMessage();
                                Log.e("Status code: ", String.valueOf(statusCode));
                                Log.e("Error Response Body", errorBody);

                                JSONObject errorJson = new JSONObject(errorBody);
                                String errorMessage = errorJson.optString("message");
                                String errorText = errorJson.optString("error");
                                new CustomToast(errorText, thisView).show();
                            } catch (JSONException e) {
                                Log.e("error", e.toString());
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageModel> call, Throwable t) {
                        new CustomToast("Koneksi Error!", thisView).show();
                    }
                });
    }

    private void createAndAddImageViewHotel(String imageUrl,Long tourId) {
        // Create a new ImageView
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setPadding(16, 0, 16, 0);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Get the resource identifier for the drawable using the image name
        int resourceId = getResources().getIdentifier(imageUrl, "drawable", getPackageName());
        System.out.println(resourceId);
        // Check if the resource is found
        if (resourceId != 0) {
            // Use Picasso to load the image from the resource
            Picasso.get().load(resourceId).into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RetrofitClient
                                    .getInstance()
                                    .create(TourService.class)
                                    .getDetailTour(tourId)
                                    .enqueue(new Callback<MessageModel>() {
                                        @Override
                                        public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                                            int statusCode = response.code();

                                            if (response.isSuccessful()) {
                                                assert response.body() != null;
                                                JsonNode resp = om.convertValue(response.body().getData(), JsonNode.class);
                                                Intent intent = new Intent(v.getContext(), DetailHotel.class);

                                                // Pass data to the DetailWisata activity
                                                intent.putExtra("tour_id", resp.get("id").asLong());
                                                intent.putExtra("tour_name", resp.get("name").asText());
                                                intent.putExtra("image", resp.get("image").asText());
                                                intent.putExtra("location", resp.get("location").asText());
                                                intent.putExtra("price", resp.get("price").asInt());
                                                intent.putExtra("rate", resp.get("rate").asLong());
                                                intent.putExtra("description", resp.get("description").asText());
                                                // Add other data as needed

                                                // Start the DetailWisata activity
                                                startActivity(intent);
                                            }
                                            else {
                                                try {
                                                    assert response.body() != null;
                                                    String errorBody = response.body().getMessage();
                                                    Log.e("Status code: ", String.valueOf(statusCode));
                                                    Log.e("Error Response Body", errorBody);

                                                    JSONObject errorJson = new JSONObject(errorBody);
                                                    String errorMessage = errorJson.optString("message");
                                                    String errorText = errorJson.optString("error");
                                                    new CustomToast(errorText, thisView).show();
                                                } catch (JSONException e) {
                                                    Log.e("error", e.toString());
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<MessageModel> call, Throwable t) {
                                            new CustomToast("Koneksi Error!", thisView).show();
                                        }
                                    });
                        }
                    });
                }
            });

            // Add the ImageView to the LinearLayout
            imageContainer2.addView(imageView);
        } else {
            // Handle the case where the resource is not found
            Log.e("Image Load", "Resource not found for: " + imageUrl);
        }
    }
    private void createAndAddImageViewWisata(String imageUrl,Long tourId) {
        // Create a new ImageView
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setPadding(16, 0, 16, 0);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Get the resource identifier for the drawable using the image name
        int resourceId = getResources().getIdentifier(imageUrl, "drawable", getPackageName());
        System.out.println(resourceId);
        // Check if the resource is found
        if (resourceId != 0) {
            // Use Picasso to load the image from the resource
            Picasso.get().load(resourceId).into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitClient
                            .getInstance()
                            .create(TourService.class)
                            .getDetailTour(tourId)
                            .enqueue(new Callback<MessageModel>() {
                                @Override
                                public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                                    int statusCode = response.code();

                                    if (response.isSuccessful()) {
                                        assert response.body() != null;
                                        JsonNode resp = om.convertValue(response.body().getData(), JsonNode.class);
                                        Intent intent = new Intent(v.getContext(), DetailWisata.class);

                                        // Pass data to the DetailWisata activity
                                        intent.putExtra("tour_id", resp.get("id").asLong());
                                        intent.putExtra("tour_name", resp.get("name").asText());
                                        intent.putExtra("image", resp.get("image").asText());
                                        intent.putExtra("location", resp.get("location").asText());
                                        intent.putExtra("price", resp.get("price").asInt());
                                        intent.putExtra("rate", resp.get("rate").asLong());
                                        intent.putExtra("description", resp.get("description").asText());
                                        // Add other data as needed

                                        // Start the DetailWisata activity
                                        startActivity(intent);
                                    }
                                    else {
                                        try {
                                            assert response.body() != null;
                                            String errorBody = response.body().getMessage();
                                            Log.e("Status code: ", String.valueOf(statusCode));
                                            Log.e("Error Response Body", errorBody);

                                            JSONObject errorJson = new JSONObject(errorBody);
                                            String errorMessage = errorJson.optString("message");
                                            String errorText = errorJson.optString("error");
                                            new CustomToast(errorText, thisView).show();
                                        } catch (JSONException e) {
                                            Log.e("error", e.toString());
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MessageModel> call, Throwable t) {
                                    new CustomToast("Koneksi Error!", thisView).show();
                                }
                            });
                }
            });

            // Add the ImageView to the LinearLayout
            imageContainer1.addView(imageView);
        } else {
            // Handle the case where the resource is not found
            Log.e("Image Load", "Resource not found for: " + imageUrl);
        }
    }
    private int getDrawableResourceId(String imageName) {
        return this.getApplicationContext().getResources().getIdentifier(imageName, "drawable", "android");
    }
}
