package com.moon.android.mvvm.src.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.moon.android.mvvm.src.common.models.DefaultFailResponse;
import com.moon.android.mvvm.src.common.models.DefaultResponse;
import com.moon.android.mvvm.src.main.MainService;
import com.moon.android.mvvm.src.main.interfaces.MainModelView;


public class MainViewModel extends ViewModel implements MainModelView {

    //xml과 연결되는 변수 (Data Binding)
    public ObservableField<String> id = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    //view 에서 observe하고있을 변수
    public MutableLiveData<DefaultResponse> defaultResponse = new MutableLiveData<DefaultResponse>();
    public MutableLiveData<DefaultFailResponse> defaultFailResponse = new MutableLiveData<DefaultFailResponse>();


    public MainViewModel() {

    }

    public void signInBtnClicked() {
        final MainService mainService = new MainService(this);
//        mainService.trySignIn(id.getValue(), password.getValue());
        mainService.tryTest();
    }


    @Override
    public void testSuccess(@NonNull DefaultResponse response) {
        defaultResponse.setValue(response);
    }

    @Override
    public void networkFail() {
        defaultFailResponse.setValue(new DefaultFailResponse());
    }
}
