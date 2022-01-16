package com.example.navegationviewlogin.ui.Presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PresentationViewModelo extends ViewModel {

    private MutableLiveData<String> mText;

    public PresentationViewModelo() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}