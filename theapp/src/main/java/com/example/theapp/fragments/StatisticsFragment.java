package com.example.theapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theapp.R;
import com.example.theapp.databinding.FragmentStatisticsBinding;

import org.xmlpull.v1.XmlPullParser;


public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentStatisticsBinding binding = FragmentStatisticsBinding.inflate(getLayoutInflater());

        binding.textViewTest.setText("Hello world");

        binding.textViewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuBottomSheetDialogFragment.newInstance(R.menu.email_bottom_sheet_menu).show(getParentFragmentManager(), null);
            }
        });


        return binding.getRoot();


    }
}