package com.example.theapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.DeckBrowsingCardsActivity;
import com.example.theapp.DeckDialogFragment;
import com.example.theapp.R;
import com.example.theapp.adapters.RecyclerViewAdapterDecks;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class DeckFragment extends Fragment {
    private String TAG = "ANTIGLUTEN";

    private Button addDeck;
    private ArrayList<Deck> decks;
    private static RecyclerViewAdapterDecks adapterDecks;
    private LinearLayout linearLayout;

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
        adapterDecks = new RecyclerViewAdapterDecks(getContext(), decks, databaseHelper);
        recyclerView.setAdapter(adapterDecks);

        linearLayout = rootView.findViewById(R.id.bottomSheetContainerDeck);

        for (Deck deck : decks) {
            databaseHelper.refreshDecks(deck.getName());
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        for (Deck deck : decks) {
            databaseHelper.refreshDecks(deck.getName());
        }

        adapterDecks.notifyDataSetChanged();


        int arraySize = decks.size();
        DeckDialogFragment deckButtonDialogActivity = new DeckDialogFragment();
        deckButtonDialogActivity.setOnDismissListener(dialog -> updateDecks(arraySize));
        addDeck.setOnClickListener(v -> {
            deckButtonDialogActivity.show(getChildFragmentManager(), "Dialog");
            Log.d(TAG, "openDialog: dialog clicked");

        });

        adapterDecks.setOnItemClickListener(position -> {
            Log.d(TAG, "onItemClick: " + position);
            DatabaseHelper db = new DatabaseHelper(getContext());
            ArrayList<Deck> list = (ArrayList<Deck>) db.getAllDecks();
            Deck deck = list.get(position);
            Log.d(TAG, "onItemClick: " + deck.getId());


            Intent intent = new Intent(getContext(), DeckBrowsingCardsActivity.class);
            intent.putExtra("Deck", deck.getName());
            intent.putExtra("position", position);
            startActivity(intent);
        });


        BottomSheetDialog dialog = new BottomSheetDialog(getContext(),
                R.style.BottomSheetDialogTheme);

        adapterDecks.setOnItemLongClickListener(position -> {
            View bottomSheetView = LayoutInflater.from(getContext()).
                    inflate(R.layout.layout_menu_bottom_deck,
                            linearLayout,
                            false
                    );

            TextView deckNameView = bottomSheetView.findViewById(R.id.deckNameBottomSheetDeck);
            decks = (ArrayList<Deck>) databaseHelper.getAllDecks();

            if (decks.size() != 0) {
                deckNameView.setText(decks.get(position).getName());

                LinearLayout deleteLayout = bottomSheetView.findViewById(R.id.bottomSheetDelete);
                deleteLayout.setOnClickListener(v -> {
                    databaseHelper.deleteDeck(decks.get(position));

                    updateAfterDeletion(position);
                    dialog.dismiss();
                });

                dialog.setContentView(bottomSheetView);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                dialog.show();
            } else {

            }


        });


    }

    public static void updateDecksData(DatabaseHelper databaseHelper) {
        ArrayList<Deck> decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
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

    public void updateAfterDeletion(int position) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Deck> decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.updateDeckList(decks);
        adapterDecks.notifyItemRemoved(position);
    }
}
