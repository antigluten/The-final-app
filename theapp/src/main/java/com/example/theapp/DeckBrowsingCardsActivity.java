package com.example.theapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.adapters.RecyclerViewAdapterCard;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.example.theapp.fragments.DeckFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class DeckBrowsingCardsActivity extends AppCompatActivity {
    private final String TAG = "ANTIGLUTEN";

    private Button floatingActionButton;
    private static String deckName;
    private static RecyclerViewAdapterCard adapterCard;
    private ArrayList<Card> cards;
    private TextView totalTextView;
    private Button studyButton;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_deck);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        //todo checking relearn number
        long learn = databaseHelper.getNumberOfLearnCards(deckName);
        Log.d(TAG, "onCreate: to learn " + learn);

        Intent intent = getIntent();
        deckName = intent.getStringExtra("Deck");

        TextView name = findViewById(R.id.deckNameBrowsing);
        name.setText(deckName);

        cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCards);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterCard = new RecyclerViewAdapterCard(getBaseContext(), cards);
        recyclerView.setAdapter(adapterCard);

        floatingActionButton = findViewById(R.id.buttonBrowsing);

        totalTextView = findViewById(R.id.deckTotalBrowsing);
        totalTextView.setText(getString(R.string.total) + databaseHelper.getTotalFromDeck(deckName));

        studyButton = findViewById(R.id.buttonStudy);
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        Log.d(TAG, "onResume: cards size:" + cards.size());


        Log.d(TAG, "onResume: resuming...");


        int arraySize = cards.size();
        CardDialogFragment cardDialogFragment = new CardDialogFragment(deckName);
        floatingActionButton.setOnClickListener(v -> cardDialogFragment.show(getSupportFragmentManager(), "DialogCard"));

        cardDialogFragment.setOnDismissListener(dialog -> {
            updateCards(arraySize, deckName);
            int number = databaseHelper.getTotalFromDeck(deckName) + 1;
            databaseHelper.refreshTotal(deckName, databaseHelper.getTotalFromDeck(deckName) + 1);
            totalTextView.setText(getString(R.string.total) + number);
        });

        BottomSheetDialog dialog = new BottomSheetDialog(DeckBrowsingCardsActivity.this, R.style.BottomSheetDialogTheme);
        adapterCard.setOnItemLongClickListener(position -> {
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                    inflate(R.layout.layout_menu_bottom_card,
                            (ViewGroup) findViewById(R.id.bottomSheetContainer),
                            false
                    );

            TextView deckNameView = bottomSheetView.findViewById(R.id.deckNameBottomSheet);
            deckNameView.setText(cards.get(position).getFrontWord());

            LinearLayout deleteLayout = bottomSheetView.findViewById(R.id.bottomSheetDelete);
            deleteLayout.setOnClickListener(v -> {
                databaseHelper.deleteCard(cards.get(position));
                updateAfterDeletion(position, deckName);
                dialog.dismiss();
            });

            LinearLayout changeLayout = bottomSheetView.findViewById(R.id.bottomSheetChangeType);
            changeLayout.setOnClickListener(v -> {
                databaseHelper.changeTypeOfCard(cards.get(position));
                dialog.dismiss();
            });


            dialog.setContentView(bottomSheetView);
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            dialog.show();
        });


        studyButton.setOnClickListener(v -> {

        });
    }


    public static void deleteCard(DatabaseHelper databaseHelper, int position) {
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        databaseHelper.refreshTotal(deckName, databaseHelper.getTotalFromDeck(deckName) - 1);
        adapterCard.updateCardList(cards);
        adapterCard.notifyItemRemoved(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ArrayList<Deck> decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        DeckFragment.updateDecksData(databaseHelper, decks);
    }

    public void updateCards(int arraySize, String deckName) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyItemRangeInserted(arraySize, cards.size());
    }

    @SuppressLint("SetTextI18n")
    public void updateAfterDeletion(int position, String deckName) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyItemRemoved(position);
        int total = databaseHelper.getTotalFromDeck(deckName) - 1;
        databaseHelper.refreshTotal(deckName ,total);
        totalTextView.setText(getString(R.string.total) + databaseHelper.getTotalFromDeck(deckName));
    }

    public static void updateCards(DatabaseHelper databaseHelper, String deckName) {
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyDataSetChanged();
    }

}
