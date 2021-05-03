package com.example.theapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.adapters.RecyclerViewAdapterCard;
import com.example.theapp.data.Card;

import java.util.ArrayList;

public class DeckBrowsingCardsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_deck);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("pee pee poo poo hours", "When some little bitchboy is acting like a little tiny pee pee poo poo baby and messing up the group plans.",
                "Oh I didn’t know it was pee pee poo poo hours", 0, "2021-5-2", "2021-5-3", "Urban Dictionary"));
        cards.add(new Card("stupid people", "Literally everyone, ever. Including the writer of this definition.",
                "You're all stupid people and I hate you.", 0, "", "2021-5-3", "Urban Dictionary"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCards);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerViewAdapterCard adapterCard = new RecyclerViewAdapterCard(getBaseContext(), cards);
        recyclerView.setAdapter(adapterCard);

    }
}
