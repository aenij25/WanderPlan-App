package com.aplikasi.wanderplan.API.Services;

import com.aplikasi.wanderplan.Model.api.Login.LoginRequest;
import com.aplikasi.wanderplan.Model.api.Login.LoginResponse;
import com.aplikasi.wanderplan.Model.api.Register.RegisterRequest;
import com.aplikasi.wanderplan.Model.api.Register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface AuthService {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
