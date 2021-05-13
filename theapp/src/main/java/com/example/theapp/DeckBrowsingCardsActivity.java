package com.example.theapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.adapters.RecyclerViewAdapterCard;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.example.theapp.fragments.MenuBottomCardFragment;
import com.example.theapp.fragments.MenuBottomDeckFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DeckBrowsingCardsActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private static String deckName;
    private static RecyclerViewAdapterCard adapterCard;
    private ArrayList<Card> cards;
    private TextView totalTextView;
    private static Deck deck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_deck);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        Intent intent = getIntent();
        deckName = intent.getStringExtra("Deck");


        TextView name = findViewById(R.id.deckNameBrowsing);
        name.setText(deckName);



//        boolean success = databaseHelper.addCard(new Card("stupid people", "Literally everyone, ever. Including the writer of this definition.",
//                "You're all stupid people and I hate you.", 0, "", "2021-5-3", "Hello"));

//        if (success) {
//            Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getBaseContext(), "Fail", Toast.LENGTH_SHORT).show();
//        }

        cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCards);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterCard = new RecyclerViewAdapterCard(getBaseContext(), cards);
        recyclerView.setAdapter(adapterCard);

        floatingActionButton = findViewById(R.id.floatingActionButton);


        totalTextView = findViewById(R.id.deckTotalBrowsing);
        totalTextView.setText("Total: " + databaseHelper.getTotalFromDeck(deckName));
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());


        int arraySize = cards.size();
        CardDialogFragment cardDialogFragment = new CardDialogFragment(deckName);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cardDialogFragment.show(getSupportFragmentManager(), "DialogCard");




//                databaseHelper.addCard(new Card("stupid people", "Literally everyone, ever. Including the writer of this definition.",
//                        "You're all stupid people and I hate you.", 0, "", "2021-5-3", "Hello"));
//                ArrayList<Card> newCards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
//                adapterCard.updateCardList(newCards);
//                adapterCard.notifyItemInserted(newCards.size());
            }
        });
        cardDialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateCards(arraySize, deckName);
                int number = databaseHelper.getTotalFromDeck(deckName) + 1;
                databaseHelper.addToTotal(deckName, databaseHelper.getTotalFromDeck(deckName) + 1);
                totalTextView.setText("Total: " + number);
            }
        });


        adapterCard.setOnItemLongClickListener(new RecyclerViewAdapterCard.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(int position) {
                MenuBottomCardFragment menuBottomCardFragment = new MenuBottomCardFragment();
                menuBottomCardFragment.newInstance(R.menu.menu_bottom_card, cards.  get(position), position).show(getSupportFragmentManager(), null);
            }
        });

    }


    public static void deleteCard(DatabaseHelper databaseHelper, int position) {
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyItemRemoved(position);
    }

    public void updateCards(int arraySize, String deckName){
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
        adapterCard.updateCardList(cards);
        adapterCard.notifyItemRangeInserted(arraySize, cards.size());
    }
}
