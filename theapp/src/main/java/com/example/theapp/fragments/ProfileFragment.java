package com.example.theapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.theapp.MainActivity;
import com.example.theapp.R;
import com.example.theapp.database.UserModel;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);




        return rootView;
    }

}
