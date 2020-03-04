package com.app_nfusion.mmsorter.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app_nfusion.mmsorter.R;

public class AboutFragment extends Fragment {

    private AboutViewModel aboutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutViewModel =
                ViewModelProviders.of(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        final TextView textView = root.findViewById(R.id.text_about);
        final TextView textViewLeadProgrammer = root.findViewById(R.id.text_lead_programmer);
        final TextView textViewZaid = root.findViewById(R.id.text_zaid);
        final TextView textViewOutputProgrammer = root.findViewById(R.id.text_output_programmer);
        final TextView textViewMamadou = root.findViewById(R.id.text_mamadou);
        final TextView textViewMotorProgrammer = root.findViewById(R.id.text_motor_programmer);
        final TextView textViewBusra = root.findViewById(R.id.text_busra);
        final TextView textViewIntegration = root.findViewById(R.id.text_integration);
        final TextView textViewAhmed = root.findViewById(R.id.text_ahmed);
        final TextView textViewDesign = root.findViewById(R.id.text_design);
        final TextView textViewOmar = root.findViewById(R.id.text_omar);

        final ScrollView scrollView = (ScrollView) root.findViewById(R.id.scrollView);
        scrollView.setEnabled(true);

        aboutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aboutViewModel.getTextLeadProgrammer().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewLeadProgrammer.setText(s);
            }
        });

        aboutViewModel.getTextZaid().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewZaid.setText(s);
            }
        });

        aboutViewModel.getTextOutputProgrammer().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewOutputProgrammer.setText(s);
            }
        });

        aboutViewModel.getTextMamadou().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewMamadou.setText(s);
            }
        });

        aboutViewModel.getTextMotorProgrammer().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewMotorProgrammer.setText(s);
            }
        });

        aboutViewModel.getTextBusra().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewBusra.setText(s);
            }
        });

        aboutViewModel.getTextIntegration().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewIntegration.setText(s);
            }
        });

        aboutViewModel.getTextAhmed().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewAhmed.setText(s);
            }
        });

        aboutViewModel.getTextDesign().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewDesign.setText(s);
            }
        });

        aboutViewModel.getTextOmar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewOmar.setText(s);
            }
        });
        return root;
    }
}
