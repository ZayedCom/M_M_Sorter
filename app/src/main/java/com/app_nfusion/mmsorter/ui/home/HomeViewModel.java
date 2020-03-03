package com.app_nfusion.mmsorter.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mTextTotal;
    private MutableLiveData<String> mTextUnidentified;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mTextTotal = new MutableLiveData<>();
        mTextUnidentified = new MutableLiveData<>();

        mText.setValue("Chart of Total M&M's Detected");
        mTextTotal.setValue("Total : 92");
        mTextUnidentified.setValue("Unidentified : 2");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getTextTotal() {
        return mTextTotal;
    }

    public LiveData<String> getTextUnidentified() {
        return mTextUnidentified;
    }
}