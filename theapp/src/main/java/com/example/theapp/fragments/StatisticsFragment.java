package com.example.theapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theapp.R;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.databinding.FragmentStatisticsBinding;

import org.xmlpull.v1.XmlPullParser;


public class StatisticsFragment extends Fragment {
    int i = 0;
    View.OnLongClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentStatisticsBinding binding = FragmentStatisticsBinding.inflate(getLayoutInflater());

        binding.textViewTest.setText("Hello world");

//        binding.textViewTest.setOnLongClickListener(new View.OnLongClickListener() {
//            @Overrideq
//            public boolean onLongClick(View v) {
//                MenuBottomSheetDialogFragment menuBottomSheetDialogFragment = new MenuBottomSheetDialogFragment();
//                menuBottomSheetDialogFragment.newInstance(R.menu.email_bottom_sheet_menu).show(getParentFragmentManager(), null);
//                return true;
//            }
//        });

//        binding.textViewTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                i++;
//                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
//                databaseHelper.addToTotal("joji", i);
//            }
//        });


        return binding.getRoot();


    }


}