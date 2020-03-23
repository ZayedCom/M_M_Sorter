package com.app_nfusion.mmsorter.ui.dashboard;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app_nfusion.mmsorter.R;

import java.util.concurrent.TimeUnit;

public class DashboardViewModel extends ViewModel {

    static CountDownTimer timer;

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Press start to initiate the sorting");
    }

    public LiveData<String> getText() {
        return mText;
    }

    static public void startTimer(final TextView textView, final boolean stopTimer) {
        final long time = 600000;
        final long interval = 1000;

        if (stopTimer == false) {
            timer = new CountDownTimer(time, interval) {
                @SuppressLint({"DefaultLocale", "SetTextI18n", "ResourceAsColor"})
                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    textView.setTextColor(R.color.colorAccent);
                    textView.setText("" + String.format("%d : %02d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    if (millisUntilFinished % 10 == 0) {
                        DashboardFragment.refresh = true;
                    }
                }

                @Override
                public void onFinish() {
                    textView.setText("0 : 00");
                }
            }.start();
        }
        else if(stopTimer == true){
            timer.cancel();
            textView.setText("0 : 00");
        }
    }
}