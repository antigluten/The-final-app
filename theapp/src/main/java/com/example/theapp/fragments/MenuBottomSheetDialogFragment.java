package com.example.theapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.theapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class MenuBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_bottom_sheet_dialog_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navigationView = view.findViewById(R.id.navigation_view);
        navigationView.inflateMenu(R.menu.email_bottom_sheet_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                dismiss();
                return true;
            }
        });


    }

    public final static MenuBottomSheetDialogFragment newInstance(@MenuRes int menuResId) {
        MenuBottomSheetDialogFragment fragment = new MenuBottomSheetDialogFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("MenuBottomSheetDialogFragment_menuResId", menuResId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
