package com.example.theapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.adapters.RecyclerViewAdapterCard;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.example.theapp.fragments.DeckFragment;
import com.example.theapp.fragments.MenuBottomCardFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DeckBrowsingCardsActivity extends AppCompatActivity {
    private String TAG = "ANTIGLUTEN";


    private Button floatingActionButton;
    private static String deckName;
    private static RecyclerViewAdapterCard adapterCard;
    private ArrayList<Card> cards;
    private TextView totalTextView;
    private static Deck deck;
    private int position;
    private Button studyButton;
    private int total = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_deck);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        Intent intent = getIntent();
        deckName = intent.getStringExtra("Deck");

        position = intent.getIntExtra("position", 0);

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
        total = databaseHelper.getTotalFromDeck(deckName);
        totalTextView.setText("Total: " + total);

        studyButton = findViewById(R.id.buttonStudy);
    }



    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        Log.d(TAG, "onResume: cards size:" + cards.size());


        Log.d(TAG, "onResume: resuming...");


        int arraySize = cards.size();
        CardDialogFragment cardDialogFragment = new CardDialogFragment(deckName);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDialogFragment.show(getSupportFragmentManager(), "DialogCard");
            }
        });

        cardDialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateCards(arraySize, deckName);
                int number = databaseHelper.getTotalFromDeck(deckName) + 1;
                databaseHelper.refreshTotal(deckName, databaseHelper.getTotalFromDeck(deckName) + 1);
                totalTextView.setText("Total: " + number);
            }
        });

        //TODO manually add the fragment
//        MenuBottomCardFragment menuBottomCardFragment = new MenuBottomCardFragment();
//        adapterCard.setOnItemLongClickListener(new RecyclerViewAdapterCard.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClickListener(int position) {
//                Log.d(TAG, "onItemLongClickListener: position " + position + " cards size: " + cards.size()) ;
//                menuBottomCardFragment.newInstance(R.menu.menu_bottom_card, cards.get(position), position).show(getSupportFragmentManager(), null);
//            }
//        });

        BottomSheetDialog dialog = new BottomSheetDialog(DeckBrowsingCardsActivity.this, R.style.BottomSheetDialogTheme);
        adapterCard.setOnItemLongClickListener(new RecyclerViewAdapterCard.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(int position) {
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.layout_menu_bottom_card,
                                (ViewGroup) findViewById(R.id.bottomSheetContainer),
                                false
                        );

                TextView deckNameView = bottomSheetView.findViewById(R.id.deckNameBottomSheet);
                deckNameView.setText(cards.get(position).getFrontWord());

                LinearLayout deleteLayout = bottomSheetView.findViewById(R.id.bottomSheetDelete);
                deleteLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseHelper.deleteCard(cards.get(position));
                        updateAfterDeletion(position, deckName);
                        dialog.dismiss();
                    }
                });


                dialog.setContentView(bottomSheetView);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                dialog.show();
            }
        });


        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

            }
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

    public void updateAfterDeletion(int position, String deckName) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyItemRemoved(position);
        int total = databaseHelper.getTotalFromDeck(deckName) - 1;
        databaseHelper.refreshTotal(deckName ,total);
        totalTextView.setText("Total: " + databaseHelper.getTotalFromDeck(deckName));
    }

    public static void updateCards(DatabaseHelper databaseHelper, String deckName) {
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyDataSetChanged();
    }


}
