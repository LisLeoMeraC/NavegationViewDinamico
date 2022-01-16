package com.example.navegationviewlogin.ui.Galeriia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GaleriaViewModelo extends ViewModel {

    private MutableLiveData<String> mText;

    public GaleriaViewModelo() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}