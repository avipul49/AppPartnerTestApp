package com.apppartner.androidprogrammertest;

import android.app.Application;

import com.apppartner.androidprogrammertest.services.ServiceInterface;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MyApp extends Application {
    private Retrofit retrofit;
    private ServiceInterface serviceInterface;
    static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url)).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        serviceInterface = retrofit.create(ServiceInterface.class);
        mInstance = this;
    }

    public static MyApp getInstance() {
        return mInstance;
    }

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }
}