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
    // profile fragment's elements

    Button submit;
    EditText profileName, profileAge, profileSurname;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        submit = rootView.findViewById(R.id.button);
        profileName = rootView.findViewById(R.id.profileName);
        profileSurname = rootView.findViewById(R.id.profileSurname);
        profileAge = rootView.findViewById(R.id.profileAge);
        switch1 = rootView.findViewById(R.id.switch1);
        listView = rootView.findViewById(R.id._dynamic);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteringData();
            }
        });
        return rootView;
    }

    private void enteringData() {
        try {
            UserModel userModel = new UserModel(profileName.getText().toString(), profileSurname.getText().toString(),
                    Integer.parseInt(profileAge.getText().toString()), switch1.isChecked());
            Toast.makeText(getContext(), userModel.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error entering data", Toast.LENGTH_SHORT).show();
        }
    }
}
