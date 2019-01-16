package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.view;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.R;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.User;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.presenter.UserPresenter;

public class MainActivity extends AppCompatActivity implements UserScreenView {

    private EditText userNameText;
    private EditText emailText;
    private UserPresenter presenter;

    private ProgressBar progressBar;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userNameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email);
        loginButton = findViewById(R.id.loginButton);
        initProgressBar();



        loginButton.setOnClickListener(loginHandler);

        presenter = new UserPresenter(this);
    }

    View.OnClickListener loginHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getUserInfo(userNameText.getText().toString());
        }
    };

    //Offloads Login logic to presenter
    private void getUserInfo(String username) {
        presenter.loadAccount(username);
    }

    //Setup for this Activity
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                Resources.getSystem().getDisplayMetrics().heightPixels);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.addContentView(progressBar, params);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishShowLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    //TODO Kickoff new activity/fragment for logged in screen
    @Override
    public void showContent() {

    }

    @Override
    public void showError(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error)
                .setTitle("Alert")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void setUser(User user) {

    }

    @Override
    public void retryClick() {

    }
}
