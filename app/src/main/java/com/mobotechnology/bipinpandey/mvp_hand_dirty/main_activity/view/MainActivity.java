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
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.UserService;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.UserServiceImpl;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.presenter.UserPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserScreenView {

    private EditText userNameText;
    private EditText emailText;
    private UserPresenter presenter;
    private UserServiceImpl serviceImpl;
    private UserService apiService;
    private TextView myTextView;
    private ProgressBar progressBar;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //myTextView = findViewById(R.id.myTextView);
        userNameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email);
        Button loginButton = findViewById(R.id.loginButton);
        initProgressBar();

        serviceImpl = new UserServiceImpl();

        apiService = serviceImpl.getRetrofit()
                .create(UserService.class);

        loginButton.setOnClickListener(loginHandler);


        userNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //presenter.updateFullName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                finishShowLoading();
            }
        });

        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //presenter.updateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                finishShowLoading();
            }
        });

    }

    View.OnClickListener loginHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getUserInfo(userNameText.getText().toString());
        }
    };

    //TODO add to presenter
    private void getUserInfo(String username) {
        Call<List<User>> call = apiService.getByUserName(username);
        showLoading();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                finishShowLoading();
                int statusCode = response.code();
                Log.i("onResponse", "Status Code: " + statusCode);
                List<User> users = response.body();
                if (users != null && users.size() > 0) {

                    Log.i("onResponse", "Response Body: " + users.toString());
                    Log.i("onResponse", "Response name:  " + users.get(0).getName());
                    Log.i("onResponse", "Response username:  " + users.get(0).getUsername());
                } else {
                    showError("User Not Found");
                    Log.i("onResponse", "Response Body was NULL");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Log error here since request failed
                finishShowLoading();
                showError(t.getMessage());
            }

        });
    }

    //This is specific to this activity so will not move
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
    public void updateUserInfoTextView(String info) {
        myTextView.setText(info);
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishShowLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

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
