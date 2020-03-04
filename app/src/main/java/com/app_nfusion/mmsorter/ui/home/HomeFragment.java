package com.app_nfusion.mmsorter.ui.home;

import android.os.Bundle;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private boolean showPercentagesSwitch = false;

    private float redColor;
    private float yellowColor;
    private float blueColor;
    private float greenColor;
    private float orangeColor;
    private float brownColor;

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

        final ProgressView progressViewRed = root.findViewById(R.id.dashboardRed);
        final ProgressView progressViewYellow = root.findViewById(R.id.dashboardYellow);
        final ProgressView progressViewBlue = root.findViewById(R.id.dashboardBlue);
        final ProgressView progressViewGreen = root.findViewById(R.id.dashboardGreen);
        final ProgressView progressViewOrange = root.findViewById(R.id.dashboardOrange);
        final ProgressView progressViewBrown = root.findViewById(R.id.dashboardBrown);

        redColor = progressViewRed.getProgress();
        yellowColor = progressViewYellow.getProgress();
        blueColor = progressViewBlue.getProgress();
        greenColor = progressViewGreen.getProgress();
        orangeColor = progressViewOrange.getProgress();
        brownColor = progressViewBrown.getProgress();

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
