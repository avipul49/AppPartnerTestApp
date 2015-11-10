package com.apppartner.androidprogrammertest.services;

import com.apppartner.androidprogrammertest.models.Response;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by vipulmittal on 06/11/15.
 */
public interface ServiceInterface {
    @FormUrlEncoded
    @POST("scripts/login.php")
    Call<Response> login(@Field("username") String userName, @Field("password") String password);
}
