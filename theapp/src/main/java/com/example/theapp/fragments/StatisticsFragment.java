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
    View.OnLongClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentStatisticsBinding binding = FragmentStatisticsBinding.inflate(getLayoutInflater());

        binding.textViewTest.setText("Hello world");

//        binding.textViewTest.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                MenuBottomSheetDialogFragment menuBottomSheetDialogFragment = new MenuBottomSheetDialogFragment();
//                menuBottomSheetDialogFragment.newInstance(R.menu.email_bottom_sheet_menu).show(getParentFragmentManager(), null);
//                return true;
//            }
//        });


        return binding.getRoot();


    }


}