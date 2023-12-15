package com.aplikasi.wanderplan.API.Services;

import com.aplikasi.wanderplan.Model.api.Login.LoginRequest;
import com.aplikasi.wanderplan.Model.api.MessageModel;
import com.aplikasi.wanderplan.Model.api.Register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TourService {
    @POST("tour/get-all-tour")
    Call<MessageModel> getAllTour();

    @POST("tour/get-tour-detail")
    Call<MessageModel> getDetailTour(@Query("id") Long id);
}
