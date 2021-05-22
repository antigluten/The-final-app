package com.example.theapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.theapp.LogInActivity;
import com.example.theapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    View rootView;
    Button logout;
    FirebaseUser currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        logout = rootView.findViewById(R.id.profileLogout);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
//            Toast.makeText(getContext(), "You are logged in", Toast.LENGTH_LONG).show();
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getContext(), LogInActivity.class));
                }
            });
        } else {
            startActivity(new Intent(getContext(), LogInActivity.class));

        }
    }
}

