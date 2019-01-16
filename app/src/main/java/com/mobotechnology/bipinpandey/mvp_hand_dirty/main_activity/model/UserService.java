package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    // Request method and URL specified in the annotation
    @GET("users/{id}")
    Call<User> getUser(@Path("id") String user);

    @GET("users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @GET("users")
    Call<List<User>> getByUserName(@Query("username") String username);

    @POST("users/new")
    Call<User> createUser(@Body User user);

}
