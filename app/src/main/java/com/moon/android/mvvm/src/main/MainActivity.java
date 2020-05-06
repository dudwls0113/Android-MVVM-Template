package com.moon.android.mvvm.src.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.moon.android.mvvm.R;
import com.moon.android.mvvm.databinding.ActivityMainBinding;
import com.moon.android.mvvm.src.BaseActivity;
import com.moon.android.mvvm.src.main.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initStartView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new ViewModelProvider(getViewModelStore(), viewModelFactory).get(MainViewModel.class);
        mBinding.setViewModel(mMainViewModel);
    }

    @Override
    protected void initDataBinding() {
        /* Observing */
        mMainViewModel.defaultResponse.observe(this, response -> {
            showSimpleMessageDialog(response.getMessage());
        });

        mMainViewModel.defaultFailResponse.observe(this, response -> {
            showSimpleMessageDialog(getString(R.string.network_error));
        });
    }

    @Override
    protected void initAfterBinding() {

    }
}
