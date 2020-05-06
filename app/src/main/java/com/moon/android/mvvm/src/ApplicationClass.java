package com.moon.android.mvvm.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.moon.android.mvvm.config.XAccessTokenInterceptor;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {

    private static volatile ApplicationClass instance = null;

    public static ApplicationClass getApplicationClassContext() {
        if (instance == null)
            throw new IllegalStateException("Error of ApplicationClass");
        return instance;
    }

    // 테스트 서버 주소
    public static String BASE_URL = "http://52.78.11.153/";
    // 실서버 주소
//    public static String BASE_URL = "https://12.123.123.1/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "MVVM-APP";

    // JWT Token 값
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT_DAY = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    public static SimpleDateFormat DATE_FORMAT_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    public static SimpleDateFormat DATE_FORMAT_KR = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
    public static SimpleDateFormat DATE_FORMAT_EN = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

    // Retrofit 인스턴스
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .addNetworkInterceptor(httpLoggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}