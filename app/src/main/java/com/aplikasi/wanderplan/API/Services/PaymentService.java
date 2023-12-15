package com.aplikasi.wanderplan.API.Services;

import com.aplikasi.wanderplan.Model.api.Login.LoginRequest;
import com.aplikasi.wanderplan.Model.api.MessageModel;
import com.aplikasi.wanderplan.Model.api.Register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentService {
    @POST("payment/get-history")
    Call<MessageModel> getHistory(@Query("email") String email);

    @POST("payment/transaction")
    Call<MessageModel> register(@Body RegisterRequest registerRequest);
}
