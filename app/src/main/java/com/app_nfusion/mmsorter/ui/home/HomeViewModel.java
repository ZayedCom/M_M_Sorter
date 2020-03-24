package com.app_nfusion.mmsorter.ui.home;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static com.app_nfusion.mmsorter.ui.home.HomeFragment.totalNumber;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.unidentifiedColor;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mTextTotal;
    private MutableLiveData<String> mTextUnidentified;

    @SuppressLint("DefaultLocale")
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mTextTotal = new MutableLiveData<>();
        mTextUnidentified = new MutableLiveData<>();

        mText.setValue("Chart of Total M&M's Detected");
        mTextTotal.setValue("Total : " + String.format("%.0f", totalNumber));
        mTextUnidentified.setValue("Unidentified : " +  String.format("%.0f", unidentifiedColor));
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