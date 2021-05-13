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

import com.example.theapp.DeckBrowsingCardsActivity;
import com.example.theapp.R;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class MenuBottomCardFragment extends BottomSheetDialogFragment {
    private NavigationView navigationView;

    Card card;
    int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_bottom_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navigationView = view.findViewById(R.id.navigation_view_card);
        navigationView.inflateMenu(R.menu.menu_bottom_card);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(getContext(), "Item: " + item.toString(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        DatabaseHelper db = new DatabaseHelper(getContext());
                        if (card != null) {
                            boolean success = db.deleteCard(card);
                            Toast.makeText(getContext(), "Returns " + success + " " + card.getId(), Toast.LENGTH_SHORT).show();
                            DeckBrowsingCardsActivity.deleteCard(db, getPosition());
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

    public MenuBottomCardFragment newInstance(@MenuRes int menuResId, Card card, int position) {
        MenuBottomCardFragment fragment = new MenuBottomCardFragment();
        fragment.setCard(card);
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

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
