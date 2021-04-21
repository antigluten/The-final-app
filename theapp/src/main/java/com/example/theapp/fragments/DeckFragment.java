package com.example.theapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.R;
import com.example.theapp.deck.Deck;
import com.example.theapp.deck.DeckAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.TreeSet;

public class DeckFragment extends Fragment {

    private RecyclerView recyclerView;
    private DeckAdapter deckAdapter;
    private Button submit;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);

        DatabaseReference decks = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("Decks");

        ArrayList<Deck> item = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getUid());

        recyclerView = rootView.findViewById(R.id.recycler_view);
        deckAdapter = new DeckAdapter(item);


//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    Deck object = snapshot.getValue(Deck.class);
//                    item.add(new Deck(object.image, object.text));
//                    deckAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(deckAdapter);

        submit = rootView.findViewById(R.id.test_button);
        editText = rootView.findViewById(R.id.test_edit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();

                if (item.size() == 0) {
                    item.add(new Deck(R.drawable.orange, text));
                    deckAdapter.notifyDataSetChanged();
                }
                else {
                    int equal = 0;
                    int diff = 0;
                    for (int i = 0; i < item.size(); i++) {
                        if (!item.get(i).text.equals(text)) {
                            diff++;
                        }
                    }
                    if (diff > 0) {
                        item.add(new Deck(R.drawable.orange, text));
                        deckAdapter.notifyDataSetChanged();
                    }
                }
//                Deck deck = new Deck(R.drawable.orange, text);
//                decks.child(text).setValue(deck);
            }
        });

        return rootView;
    }
}
