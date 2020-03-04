package com.app_nfusion.mmsorter.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mTextLeadProgrammer;
    private MutableLiveData<String> mTextZaid;
    private MutableLiveData<String> mTextOutputProgrammer;
    private MutableLiveData<String> mTextMamadou;
    private MutableLiveData<String> mTextMotorProgrammer;
    private MutableLiveData<String> mTextBusra;
    private MutableLiveData<String> mTextIntegration;
    private MutableLiveData<String> mTextAhmed;
    private MutableLiveData<String> mTextDesign;
    private MutableLiveData<String> mTextOmar;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mTextLeadProgrammer = new MutableLiveData<>();
        mTextZaid = new MutableLiveData<>();
        mTextOutputProgrammer = new MutableLiveData<>();
        mTextMamadou =  new MutableLiveData<>();
        mTextMotorProgrammer = new MutableLiveData<>();
        mTextBusra = new MutableLiveData<>();
        mTextIntegration = new MutableLiveData<>();
        mTextAhmed = new MutableLiveData<>();
        mTextDesign = new MutableLiveData<>();
        mTextOmar = new MutableLiveData<>();

        mText.setValue("M&M Sorter Capstone Project");
        mTextLeadProgrammer.setValue("Color Detecting Algorithm & Mobile Developer");
        mTextZaid.setValue("Zaid K. Al Qassar");
        mTextOutputProgrammer.setValue("Output & Display Programmer");
        mTextMamadou.setValue("Mamadou Diop");
        mTextMotorProgrammer.setValue("Motor Programmer");
        mTextBusra.setValue("Büşra Çelik");
        mTextIntegration.setValue("Integration of the Project");
        mTextAhmed.setValue("Ahmed Tariq Abdalaal Aboseif");
        mTextDesign.setValue("Design");
        mTextOmar.setValue("Omar Fakhereldin");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getTextLeadProgrammer() {
        return mTextLeadProgrammer;
    }

    public LiveData<String> getTextZaid() {
        return mTextZaid;
    }

    public LiveData<String> getTextOutputProgrammer() {
        return mTextOutputProgrammer;
    }

    public LiveData<String> getTextMamadou() {
        return mTextMamadou;
    }

    public LiveData<String> getTextMotorProgrammer() {
        return mTextMotorProgrammer;
    }

    public LiveData<String> getTextBusra() {
        return mTextBusra;
    }

    public LiveData<String> getTextIntegration() {
        return mTextIntegration;
    }

    public LiveData<String> getTextAhmed() {
        return mTextAhmed;
    }

    public LiveData<String> getTextDesign() {
        return mTextDesign;
    }

    public LiveData<String> getTextOmar() {
        return mTextOmar;
    }
}