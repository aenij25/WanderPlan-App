package com.aplikasi.wanderplan.API.Services;

import com.aplikasi.wanderplan.Model.api.Login.LoginRequest;
import com.aplikasi.wanderplan.Model.api.MessageModel;
import com.aplikasi.wanderplan.Model.api.Register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface AuthService {
    @POST("user/login")
    Call<MessageModel> login(@Body LoginRequest loginRequest);

    @POST("user/register")
    Call<MessageModel> register(@Body RegisterRequest registerRequest);
}
