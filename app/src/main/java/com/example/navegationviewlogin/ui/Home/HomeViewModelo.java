package com.example.navegationviewlogin.ui.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModelo extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModelo() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}