package com.example.theapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class StudyCardsActivity extends AppCompatActivity {
    private String TAG = "ANTIGLUTEN";

    private String deckName;
    private TextView totalNumber;
    private TextView newNumber;
    private TextView againNumber;
    private TextView reviseNumber;

    private TextView frontCard;
    private TextView opporsiteCard;
    private TextView contextTextView;

    private Button againButton;
    private Button dueFutureButton;

    private ArrayList<Card> cards;

    private long oneDay = 86000000;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_cards);

        Intent intent = getIntent();
        deckName = intent.getStringExtra("Deck");

        TextView deckNameView = findViewById(R.id.deckNameStudy);
        deckNameView.setText(deckName);

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        cards = databaseHelper.getAllCardsToday(deckName);

        totalNumber = findViewById(R.id.deckTotalStudy);
        totalNumber.setText("Total: " + cards.size());

        newNumber = findViewById(R.id.deckLearnStudy);
        newNumber.setText("New: " + databaseHelper.getNumberOfNewCards(deckName));

        againNumber = findViewById(R.id.deckRelearnStudy);
        againNumber.setText("Learn: " + databaseHelper.getNumberOfLearnCards(deckName));

        reviseNumber = findViewById(R.id.deckReviseStudy);
        reviseNumber.setText("Revise " + databaseHelper.getNumberOfReviseCards(deckName));

        againButton = findViewById(R.id.laterButton);
        dueFutureButton = findViewById(R.id.dueFutureButton);

        contextTextView = findViewById(R.id.studyContext);

        Collections.shuffle(cards);

        frontCard = findViewById(R.id.textViewFront);
        opporsiteCard = findViewById(R.id.textViewOpporsite);

        //setting first cards values;
        contextTextView.setText(cards.get(0).getContext());
        frontCard.setText(cards.get(0).getFrontWord());
        opporsiteCard.setText(cards.get(0).getTranslationWord());

        dueFutureButton.setOnClickListener(v -> {
            setNextCard(databaseHelper);
        });

        againNumber.setOnClickListener(v -> {
            setAgainButton(databaseHelper);
        });

        againButton.setOnClickListener(v -> {
            setAgainButton(databaseHelper);
        });
    }

    public void setNextCard(DatabaseHelper databaseHelper) {
        Card card = cards.get(0);
        if (card.getInterval() == 0) {
            databaseHelper.updateInterval(getToday(), oneDay, card.getId());
        } else {
            long interval = (long) (card.getInterval() * 1.2);
            databaseHelper.updateInterval(getToday(), interval, card.getId());
        }
        databaseHelper.increaseTypeOfCard(card);
        cards.remove(0);
        totalNumber.setText("Total: " + cards.size());
        int learn = 0, revise = 0, newCards = 0;
        for (int i = 0; i < cards.size(); i++) {
            switch (cards.get(i).getType()) {
                case 0:
                    newCards++;
                    break;
                case 1:
                    learn++;
                    break;
                case 2:
                    revise++;
                    break;
            }
        }
        newNumber.setText("New: " + newCards);
        againNumber.setText("Again: " + learn);
        reviseNumber.setText("Revise: " + revise);


//        updateNumbers(databaseHelper);
        if (cards.size() != 0) {
            setNextView();
        } else {
            finish();
            Toast.makeText(getBaseContext(), "You studies all cards today", Toast.LENGTH_SHORT).show();
        }

    }

    public void setAgainButton(DatabaseHelper databaseHelper) {
        Card card = cards.get(0);
        databaseHelper.changeTypeOfCard(card);
        if (card.getInterval() == 0) {
            databaseHelper.updateInterval(getToday(), oneDay, card.getId());
        } else {
            long interval = (long) (card.getInterval() * 0.8);
            databaseHelper.updateInterval(getToday(), interval, card.getId());
        }
        Collections.shuffle(cards);
        setNextView();
    }

    public void setNextView() {
        contextTextView.setText(cards.get(0).getContext());
        frontCard.setText(cards.get(0).getFrontWord());
        opporsiteCard.setText(cards.get(0).getTranslationWord());
    }

    public long getToday() {
        return new Date().getTime();
    }

    public void updateNumbers(DatabaseHelper databaseHelper) {
        totalNumber.setText("Total: " + databaseHelper.getNumberOfTotalCardsToday(deckName));

        newNumber.setText("New: " + databaseHelper.getNumberOfNewCards(deckName));

        againNumber.setText("Learn: " + databaseHelper.getNumberOfLearnCards(deckName));

        reviseNumber.setText("Revise " + databaseHelper.getNumberOfReviseCards(deckName));
    }

}
