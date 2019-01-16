package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.presenter;

import android.util.Log;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.User;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.UserService;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.UserServiceImpl;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.view.UserScreenView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bpn on 11/30/17.
 *
 * 0. In MVP the presenter assumes the functionality of the "middle-man". All presentation logic is pushed to the presenter.
 * 1. Listens to user action and model updates
 * 2. Updates model and view
 */

public class UserPresenter {

    private UserScreenView view;
    private UserService apiService;
    private UserServiceImpl serviceImpl;

    public UserPresenter(UserScreenView view) {
        this.view = view;
        serviceImpl = new UserServiceImpl();

        apiService = serviceImpl.getRetrofit()
                .create(UserService.class);
    }


    public void loadAccount(String username) {
        view.showLoading();

        Call<List<User>> call = apiService.getByUserName(username);
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                view.finishShowLoading();
                int statusCode = response.code();
                Log.i("onResponse", "Status Code: " + statusCode);
                List<User> users = response.body();
                if (users != null && users.size() > 0) {
                    Log.i("onResponse", "Response Body: " + users.toString());
                    Log.i("onResponse", "Response name:  " + users.get(0).getName());
                    Log.i("onResponse", "Response username:  " + users.get(0).getUsername());
                } else {
                    view.showError("User Not Found");
                    Log.i("onResponse", "Response Body was NULL");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Log error here since request failed
                view.finishShowLoading();
                view.showError(t.getMessage());
            }

        });
    }

    /*
    public void onRetry() {
        loadAccount();
    }




    public void updateFullName(String fullName){
        service.setFullName(fullName);
        view.updateUserInfoTextView(service.toString());

    }

    public void updateEmail(String email){
        service.setEmail(email);
        view.updateUserInfoTextView(service.toString());

    } */



}
