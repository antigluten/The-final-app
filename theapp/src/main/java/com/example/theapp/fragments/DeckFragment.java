package com.example.theapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.R;
import com.example.theapp.deck.Deck;
import com.example.theapp.deck.DeckAdapter;

import java.util.ArrayList;

public class DeckFragment extends Fragment {

    private RecyclerView recyclerView;
    private DeckAdapter deckAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<Deck> item = new ArrayList<Deck>();
        item.add(new Deck(R.drawable.orange, "orange"));
        item.add(new Deck(R.drawable.orange, "orange"));
        item.add(new Deck(R.drawable.orange, "orange"));

        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        deckAdapter = new DeckAdapter(item);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(deckAdapter);


        return rootView;
    }
}
