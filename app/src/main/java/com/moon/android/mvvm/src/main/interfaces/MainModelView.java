package com.moon.android.mvvm.src.main.interfaces;

import androidx.annotation.NonNull;

import com.moon.android.mvvm.src.common.models.DefaultResponse;

public interface MainModelView {

    void testSuccess(@NonNull DefaultResponse response);

    void networkFail();
}
