package com.example.theapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.DeckBrowsingCardsActivity;
import com.example.theapp.DeckButtonDialogActivity;
import com.example.theapp.R;
import com.example.theapp.adapters.RecyclerViewAdapterDecks;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;

import java.util.ArrayList;

public class DeckFragment extends Fragment {
    private String TAG = "ANTIGLUTEN";

    private String getDeckName;
    private FrameLayout frameLayout;
    private Button addDeck;
    private ArrayList<Deck> decks;
    private RecyclerViewAdapterDecks adapterDecks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
        addDeck = rootView.findViewById(R.id.buttonAddDeck);


        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewDeck);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterDecks = new RecyclerViewAdapterDecks(getContext(), decks);
        recyclerView.setAdapter(adapterDecks);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        int arraySize = decks.size();
        DeckButtonDialogActivity deckButtonDialogActivity = new DeckButtonDialogActivity();
        deckButtonDialogActivity.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateDecks(arraySize);

            }
        });
        addDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deckButtonDialogActivity.show(getChildFragmentManager(), "Dialog");
                Log.d(TAG, "openDialog: clicked Dialog");
            }
        });

        adapterDecks.setOnItemClickListener(new RecyclerViewAdapterDecks.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: " + position);
                Intent intent = new Intent(getContext(), DeckBrowsingCardsActivity.class);
                startActivity(intent);
            }
        });

        adapterDecks.setOnItemLongClickListener(new RecyclerViewAdapterDecks.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(int position) {
                Log.d(TAG, "onItemLongClickListener: " + position);
                MenuBottomSheetDialogFragment.newInstance(R.menu.email_bottom_sheet_menu).show(getParentFragmentManager(), null);
            }
        });


    }

    public void updateDecks(int arraySize){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.updateDeckList(decks);
        adapterDecks.notifyItemRangeInserted(arraySize, decks.size());
    }
}
