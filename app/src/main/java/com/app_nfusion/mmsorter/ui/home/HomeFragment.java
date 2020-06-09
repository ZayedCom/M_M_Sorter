package com.app_nfusion.mmsorter.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app_nfusion.mmsorter.R;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private boolean showPercentagesSwitch = false;

    static public ArrayList<Float> savedColors = new ArrayList<>();

    static public float redColor;
    static public float yellowColor;
    static public float blueColor;
    static public float greenColor;
    static public float orangeColor;
    static public float brownColor;

    static public float unidentifiedColor;
    static public float totalNumber;

    ProgressView progressViewRed;
    ProgressView progressViewYellow;
    ProgressView progressViewBlue;
    ProgressView progressViewGreen;
    ProgressView progressViewOrange;
    ProgressView progressViewBrown;

    public void calculatePercentages(){
        if (savedColors.isEmpty()) {
            savedColors.add(0, (float) 0);
            savedColors.add(1, (float) 0);
            savedColors.add(2, (float) 0);
            savedColors.add(3, (float) 0);
            savedColors.add(4, (float) 0);
            savedColors.add(5, (float) 0);
            savedColors.add(6, (float) 0);
        }
        else {
            redColor = savedColors.get(0);
            yellowColor = savedColors.get(1);
            blueColor = savedColors.get(2);
            greenColor = savedColors.get(3);
            orangeColor = savedColors.get(4);
            brownColor = savedColors.get(5);
            unidentifiedColor = savedColors.get(6);

            totalNumber = redColor + yellowColor + brownColor + greenColor + orangeColor + brownColor;
            Log.i("Total : ", String.valueOf(totalNumber));

            redColor = (redColor * 100)/128;
            yellowColor = (yellowColor * 100)/128;
            blueColor = (blueColor * 100)/128;
            greenColor = (greenColor * 100)/128;
            orangeColor = (orangeColor * 100)/128;
            brownColor = (brownColor * 100)/128;

            Log.i("RED", String.valueOf(redColor));
            Log.i("YELLOW", String.valueOf(yellowColor));
            Log.i("BLUE", String.valueOf(blueColor));
            Log.i("GREEN", String.valueOf(greenColor));
            Log.i("ORANGE", String.valueOf(orangeColor));
            Log.i("BROWN", String.valueOf(brownColor));
            Log.i("UNIDENTIFIED", String.valueOf(unidentifiedColor));

            progressViewRed.setProgress(redColor);
            progressViewYellow.setProgress(yellowColor);
            progressViewBlue.setProgress(blueColor);
            progressViewGreen.setProgress(greenColor);
            progressViewOrange.setProgress(orangeColor);
            progressViewBrown.setProgress(brownColor);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        final TextView textViewTotal = root.findViewById(R.id.text_total);
        final TextView textViewUnidentified = root.findViewById(R.id.text_unidentified);

        final Button buttonShowPercentages = root.findViewById(R.id.buttonShowPercentages);
        final Button buttonClearData = root.findViewById(R.id.buttonClearData);

        progressViewRed = root.findViewById(R.id.dashboardRed);
        progressViewYellow = root.findViewById(R.id.dashboardYellow);
        progressViewBlue = root.findViewById(R.id.dashboardBlue);
        progressViewGreen = root.findViewById(R.id.dashboardGreen);
        progressViewOrange = root.findViewById(R.id.dashboardOrange);
        progressViewBrown = root.findViewById(R.id.dashboardBrown);

        calculatePercentages();

        buttonShowPercentages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Show all",  Toast.LENGTH_LONG).show();
                if (showPercentagesSwitch == false) {
                    progressViewRed.setLabelText(String.valueOf(redColor) + "%");
                    progressViewYellow.setLabelText(String.valueOf(yellowColor) + "%");
                    progressViewBlue.setLabelText(String.valueOf(blueColor) + "%");
                    progressViewGreen.setLabelText(String.valueOf(greenColor) + "%");
                    progressViewOrange.setLabelText(String.valueOf(orangeColor) + "%");
                    progressViewBrown.setLabelText(String.valueOf(brownColor) + "%");
                    showPercentagesSwitch = true;
                }
                else {
                    progressViewRed.setLabelText(String.valueOf("Red"));
                    progressViewYellow.setLabelText(String.valueOf("Yellow"));
                    progressViewBlue.setLabelText(String.valueOf("Blue"));
                    progressViewGreen.setLabelText(String.valueOf("Green"));
                    progressViewOrange.setLabelText(String.valueOf("Orange"));
                    progressViewBrown.setLabelText(String.valueOf("Brown"));
                    showPercentagesSwitch = false;
                }
            }
        });

        buttonClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Clear all",  Toast.LENGTH_LONG).show();
                redColor = 0;
                yellowColor = 0;
                blueColor = 0;
                greenColor = 0;
                orangeColor = 0;
                brownColor = 0;
                progressViewRed.setProgress(0);
                progressViewYellow.setProgress(0);
                progressViewBlue.setProgress(0);
                progressViewGreen.setProgress(0);
                progressViewOrange.setProgress(0);
                progressViewBrown.setProgress(0);
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        homeViewModel.getTextTotal().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewTotal.setText(s);
            }
        });

        homeViewModel.getTextUnidentified().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewUnidentified.setText(s);
            }
        });

        return root;
    }
}
