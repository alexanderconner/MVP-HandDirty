package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserServiceImpl {

    // Trailing slash is needed
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
