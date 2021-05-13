package com.example.theapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.theapp.R;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class MenuBottomDeckFragment extends BottomSheetDialogFragment {
    private NavigationView navigationView;

    Deck deck;
    int position;

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
                Toast.makeText(getContext(), "Item: " + item.toString(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        DatabaseHelper db = new DatabaseHelper(getContext());
                        if (deck != null) {
                            boolean success = db.deleteDeck(getDeck());
                            Toast.makeText(getContext(), "Returns " + success + " " + deck.getId(), Toast.LENGTH_SHORT).show();
                            DeckFragment.updateDecks(db, getPosition());
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Null object", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                dismiss();
                return true;
            }
        });


    }

    public MenuBottomDeckFragment newInstance(@MenuRes int menuResId, Deck deck, int position) {
        MenuBottomDeckFragment fragment = new MenuBottomDeckFragment();
        fragment.setDeck(deck);
        fragment.setPosition(position);

        Bundle bundle = new Bundle();

        bundle.putInt("MenuBottomSheetDialogFragment_menuResId", menuResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }
}
