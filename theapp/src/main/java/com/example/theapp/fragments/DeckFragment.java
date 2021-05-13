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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.DeckBrowsingCardsActivity;
import com.example.theapp.DeckDialogFragment;
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
    private static RecyclerViewAdapterDecks adapterDecks;

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

        adapterDecks.notifyDataSetChanged();

        int arraySize = decks.size();
        DeckDialogFragment deckButtonDialogActivity = new DeckDialogFragment();
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
                Log.d(TAG, "openDialog: dialog clicked");

            }
        });

        adapterDecks.setOnItemClickListener(new RecyclerViewAdapterDecks.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: " + position);
                DatabaseHelper db = new DatabaseHelper(getContext());
                ArrayList<Deck> list = (ArrayList<Deck>) db.getAllDecks();
                Deck deck = list.get(position);
                Log.d(TAG, "onItemClick: " + deck.getId());


                Intent intent = new Intent(getContext(), DeckBrowsingCardsActivity.class);
                intent.putExtra("Deck", deck.getName());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        adapterDecks.setOnItemLongClickListener(new RecyclerViewAdapterDecks.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(int position) {
                Log.d(TAG, "onItemLongClickListener: " + position);
                MenuBottomDeckFragment menuBottomDeckFragment = new MenuBottomDeckFragment();
                menuBottomDeckFragment.newInstance(R.menu.email_bottom_sheet_menu, decks.get(position), position).show(getParentFragmentManager(), null);


            }
        });



    }

    public static void updateDecksData(DatabaseHelper databaseHelper, ArrayList<Deck> decks) {
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.updateDeckList(decks);
        adapterDecks.notifyDataSetChanged();
    }

    public void updateDecks(int arraySize){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.updateDeckList(decks);
        adapterDecks.notifyItemRangeInserted(arraySize, decks.size());
    }

    public static void updateDecks(DatabaseHelper databaseHelper, int position) {
        ArrayList<Deck> decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.updateDeckList(decks);
        adapterDecks.notifyItemRemoved(position);
    }

    public static RecyclerViewAdapterDecks getRecyclerView() {
        return adapterDecks;
    }
}
