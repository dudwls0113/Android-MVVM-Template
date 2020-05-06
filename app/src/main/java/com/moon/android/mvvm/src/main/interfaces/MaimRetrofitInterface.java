package com.moon.android.mvvm.src.main.interfaces;

import com.moon.android.mvvm.src.common.models.DefaultResponse;
import com.moon.android.mvvm.src.main.models.SignInParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MaimRetrofitInterface {
    @POST("/user")
    Call<DefaultResponse> postSignIn(@Body SignInParams signInParams);

    @GET("/test")
    Call<DefaultResponse> getTest();
}
