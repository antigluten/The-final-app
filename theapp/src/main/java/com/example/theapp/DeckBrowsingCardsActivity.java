package com.example.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.adapters.RecyclerViewAdapterCard;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;

import java.util.ArrayList;

public class DeckBrowsingCardsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_deck);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        Intent intent = getIntent();
        String deckName = intent.getStringExtra("Deck");
        TextView name = findViewById(R.id.deckNameBrowsing);
        name.setText(deckName);

//        boolean success = databaseHelper.addCard(new Card("stupid people", "Literally everyone, ever. Including the writer of this definition.",
//                "You're all stupid people and I hate you.", 0, "", "2021-5-3", "Hello"));

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

    }
}
