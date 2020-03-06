package com.app_nfusion.mmsorter.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app_nfusion.mmsorter.R;

import java.util.concurrent.TimeUnit;

import static com.app_nfusion.mmsorter.ui.dashboard.DashboardViewModel.startTimer;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    boolean startSwitch = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        final Button stopButton = root.findViewById(R.id.buttonStop);
        final Button startButton = root.findViewById(R.id.buttonStart);
        final Button statsButton = root.findViewById(R.id.buttonStats);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startSwitch == false) {
                    startTimer(textView, false);
                    startButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_pause_white_24dp, 0, 0);
                    startButton.setText("Pause");
                    Toast.makeText(getActivity(), "Initiating M&M Sorter", Toast.LENGTH_LONG).show();
                    startSwitch = true;
                }
                else if (startSwitch == true) {
                    startTimer(textView, true);
                    startButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_play_arrow_white_24dp, 0, 0);
                    startButton.setText("Resume");
                    Toast.makeText(getActivity(), "Pausing M&M Sorter", Toast.LENGTH_LONG).show();
                    startSwitch = false;
                }
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;
    }
}
