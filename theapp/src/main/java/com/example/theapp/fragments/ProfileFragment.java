package com.example.theapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.theapp.R;
import com.example.theapp.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        View rootView = inflater.inflate(R.layout.activity_signup, container, false);
        EditText login  = rootView.findViewById(R.id.profileName);
        EditText password = rootView.findViewById(R.id.profilePassword);
        EditText email = rootView.findViewById(R.id.profileEmail);
        Button submit = rootView.findViewById(R.id.profileSubmit);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = login.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String mail = email.getText().toString().trim();

                User currentUser = new User(
                        name,
                        pass,
                        mail
                );
                Log.d("ANTIGLUTEN", currentUser.toString());
                ref.push().setValue(currentUser);
            }
        });



        return rootView;
    }

}
