package com.moon.android.mvvm.src.main.models;

import com.google.gson.annotations.SerializedName;

public class SignInParams {
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String pw;

    public SignInParams(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

}
