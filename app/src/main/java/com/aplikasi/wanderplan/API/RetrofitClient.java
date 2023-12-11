package com.aplikasi.wanderplan.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retro = null;

    private static final String BASE_URL = "https://api-wanderplan.vercel.app";

    private RetrofitClient() {
        // Private constructor to prevent instantiation from other classes.
    }

    public static Retrofit getInstance() {
        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(TimestampConverter.createGson()))
                    .build();
        }
        return retro;
    }
}
