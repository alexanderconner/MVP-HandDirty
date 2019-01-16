package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.view;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.User;

public interface UserScreenView {

    void showLoading();
    void finishShowLoading();
    void showContent();
    void showError(String error);
    void setUser(User user);
    void retryClick();
}
