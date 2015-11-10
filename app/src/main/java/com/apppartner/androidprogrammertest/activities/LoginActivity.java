package com.apppartner.androidprogrammertest.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apppartner.androidprogrammertest.MyApp;
import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.Response;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

public class LoginActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private EditText mUserName;
    private EditText mPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupToolbar();
        mUserName = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.toolbar_progress_bar);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ((TextView) mToolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.title_activity_login));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLoginButtonClicked(View v) {
        final long startTime = System.currentTimeMillis();
        progressBar.setVisibility(View.VISIBLE);
        Call<Response> call = MyApp.getInstance().getServiceInterface().login(mUserName.getText().toString(), mPassword.getText().toString());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
                progressBar.setVisibility(View.GONE);
                createDialog(response.body(), startTime).show();

            }

            @Override
            public void onFailure(Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AlertDialog createDialog(final Response response, long startTime) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format(getString(R.string.login_response_message), response.getCode(), response.getMessage(), formatTime(System.currentTimeMillis() - startTime)))
                .setTitle("Login");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (response.isSuccess()) {
                    finish();
                }
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public static String formatTime(long time) {
        long diff = time;
        if (diff / 1000 <= 0)
            return diff + " millis";
        long millis = diff % 1000;
        diff /= 1000;
        if (diff / 60 == 0)
            return diff + " secs " + millis + " millis";
        return "";
    }
}
