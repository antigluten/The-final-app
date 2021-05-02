package com.example.theapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.R;
import com.example.theapp.data.Card;
import com.example.theapp.data.Deck;

import java.util.ArrayList;

public class RecyclerViewAdapterDecks extends RecyclerView.Adapter<RecyclerViewAdapterDecks.DeckViewHolder> {
    private Context context;
    private ArrayList<Deck> decks;

    public RecyclerViewAdapterDecks(Context context, ArrayList<Deck> decks) {
        this.context = context;
        this.decks = decks;
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deck, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        Deck deck = decks.get(position);
        holder.name.setText(deck.getName());
        holder.numberToRevise.setText(String.valueOf("Revise: " + deck.getNumberToRevise()));
        holder.numberNewCards.setText(String.valueOf("New: " + deck.getNumberNewCards()));
        holder.totalNumberOfCard.setText(String.valueOf("Total: " + deck.getTotalNumberOfCard()));
        holder.numberToRelearn.setText(String.valueOf("Learn: " + deck.getNumberToRelearn()));

    }

    public void updateDeckList(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public class DeckViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView totalNumberOfCard;
        public TextView numberToRelearn;
        public TextView numberNewCards;
        public TextView numberToRevise;

        public DeckViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.deckName);
            totalNumberOfCard = itemView.findViewById(R.id.deckTotal);
            numberToRelearn = itemView.findViewById(R.id.deckRelearn);
            numberNewCards = itemView.findViewById(R.id.deckLearn);
            numberToRevise = itemView.findViewById(R.id.deckRevise);

        }
    }
}
