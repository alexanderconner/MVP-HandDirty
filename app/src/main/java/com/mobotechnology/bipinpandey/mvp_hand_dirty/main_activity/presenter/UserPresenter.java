package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.presenter;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.User;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.UserService;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.view.UserScreenView;

/**
 * Created by bpn on 11/30/17.
 *
 * 0. In MVP the presenter assumes the functionality of the "middle-man". All presentation logic is pushed to the presenter.
 * 1. Listens to user action and model updates
 * 2. Updates model and view
 */

public class UserPresenter {

    private UserService service;
    private UserScreenView view;

    public UserPresenter(UserScreenView view, UserService service) {
        this.service = service;
        this.view = view;
    }

    /*

    public void loadAccount() {
        view.showLoading();


        User user = service.getUser("test");
        if (user.status.equals("LOGGED IN")) {
            view.showContent();
            view.setUser(user.username);
        }
    }

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
