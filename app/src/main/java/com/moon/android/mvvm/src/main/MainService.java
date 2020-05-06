package com.moon.android.mvvm.src.main;

import com.moon.android.mvvm.src.common.models.DefaultResponse;
import com.moon.android.mvvm.src.main.interfaces.MaimRetrofitInterface;
import com.moon.android.mvvm.src.main.interfaces.MainModelView;
import com.moon.android.mvvm.src.main.models.SignInParams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.moon.android.mvvm.src.ApplicationClass.getRetrofit;


public class MainService {
    final MainModelView mMainModelView;

    public MainService(MainModelView mainModelView) {
        this.mMainModelView = mainModelView;
    }

    public void trySignIn(String id, String password) {
        final MaimRetrofitInterface maimRetrofitInterface = getRetrofit().create(MaimRetrofitInterface.class);
        maimRetrofitInterface.postSignIn(new SignInParams(id, password)).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainModelView.networkFail();
                    return;
                }
                if (!defaultResponse.getIsSuccess()) {
                    mMainModelView.networkFail();
                    return;
                }
                mMainModelView.testSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                t.printStackTrace();
                mMainModelView.networkFail();
            }
        });
    }

    public void tryTest() {
        final MaimRetrofitInterface maimRetrofitInterface = getRetrofit().create(MaimRetrofitInterface.class);
        maimRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainModelView.networkFail();
                    return;
                }
                if (!defaultResponse.getIsSuccess()) {
                    mMainModelView.testSuccess(defaultResponse);
                    return;
                }
                mMainModelView.testSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                t.printStackTrace();
                mMainModelView.networkFail();
            }
        });
    }
}
