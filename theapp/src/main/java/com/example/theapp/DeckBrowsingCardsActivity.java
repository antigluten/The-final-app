package com.example.theapp;

import android.app.Dialog;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DeckBrowsingCardsActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_deck);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        Intent intent = getIntent();
        String deckName = intent.getStringExtra("Deck");
        TextView name = findViewById(R.id.deckNameBrowsing);
        name.setText(deckName);

        boolean success = databaseHelper.addCard(new Card("stupid people", "Literally everyone, ever. Including the writer of this definition.",
                "You're all stupid people and I hate you.", 0, "", "2021-5-3", "Hello"));

//        if (success) {
//            Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getBaseContext(), "Fail", Toast.LENGTH_SHORT).show();
//        }

        ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCards);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerViewAdapterCard adapterCard = new RecyclerViewAdapterCard(getBaseContext(), cards);
        recyclerView.setAdapter(adapterCard);

        floatingActionButton = findViewById(R.id.floatingActionButton);

    }

    @Override
    protected void onResume() {
        super.onResume();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardDialogFragment cardDialogFragment = new CardDialogFragment();
                cardDialogFragment.show(getSupportFragmentManager(), "DialogCard");





//                databaseHelper.addCard(new Card("stupid people", "Literally everyone, ever. Including the writer of this definition.",
//                        "You're all stupid people and I hate you.", 0, "", "2021-5-3", "Hello"));
//                ArrayList<Card> newCards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(deckName);
//                adapterCard.updateCardList(newCards);
//                adapterCard.notifyItemInserted(newCards.size());
            }

        });
    }


}
