package com.example.theapp.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.DeckButtonDialogActivity;
import com.example.theapp.R;
import com.example.theapp.adapters.RecyclerViewAdapter;
import com.example.theapp.adapters.RecyclerViewAdapterDecks;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.google.firebase.database.DatabaseReference;

import java.time.Instant;
import java.util.ArrayList;

public class DeckFragment extends Fragment {
    private String TAG = "ANTIGLUTEN";

//    private Button button;
//    private EditText editText;
//
//    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerView recyclerView;
//    private RecyclerViewAdapter adapter;
//
//    private ArrayList<Card> arrayList = new ArrayList<>();
//
//    private EditText foreign;
//    private EditText translation;
//    private EditText context;
    private String getDeckName;
    private FrameLayout frameLayout;
    private Button addDeck;
    private ArrayList<Deck> decks;
    private RecyclerViewAdapterDecks adapterDecks;
    private int lastIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
        addDeck = rootView.findViewById(R.id.buttonAddDeck);


        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewDeck);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterDecks = new RecyclerViewAdapterDecks(getContext(), decks);
        recyclerView.setAdapter(adapterDecks);


        lastIndex = decks.size();


//        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
//        boolean success = databaseHelper.addCard(new Card("Hello", "World",
//                "Hello world",0, "2021-05-02", "2021-05-03", "English"));
//        if (success) {
//            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
//        }
//        foreign = rootView.findViewById(R.id.foreignWord);
//        translation = rootView.findViewById(R.id.translation);
//        context = rootView.findViewById(R.id.context);
//
//        button = rootView.findViewById(R.id.cardButton);
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
//
//        arrayList = (ArrayList<Card>) databaseHelper.getAll();
//
//        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView = rootView.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        adapter = new RecyclerViewAdapter(getContext(), arrayList);
//        recyclerView.setAdapter(adapter);
//
//        Log.d(TAG, "onCreateView: " + adapter.getItemCount());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.notifyItemRangeInserted(lastIndex, decks.size());
        DeckButtonDialogActivity deckButtonDialogActivity = new DeckButtonDialogActivity();
        deckButtonDialogActivity.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateDecks();
            }
        });
        addDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deckButtonDialogActivity.show(getChildFragmentManager(), "Dialog");
                Log.d(TAG, "openDialog: clicked Dialog");
//                openDialog();
//                lastIndex = decks.size();
//                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
//                boolean success = databaseHelper.addDeck(new Deck("Hello world"));
//                if (success) {
//                    Toast.makeText(getContext(), "Success adding the deck", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                }
//                updateDecks();
            }
        });


//        addDeck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
//
//        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Card card = arrayList.get(position);
//                Log.d(TAG, "Clicked: " + position);
//                Intent intent = new Intent(getContext(), CardTest.class);
//                intent.putExtra("Foreign", card.getFrontWord());
//                intent.putExtra("Translation", card.getTranslationWord());
//                intent.putExtra("Sentence", card.getContext());
//                startActivity(intent);
//            }
//        });
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
//                String word = foreign.getText().toString().trim();
//                String back = translation.getText().toString().trim();
//                String sentence = context.getText().toString().trim();
//
//                if (!word.isEmpty() && !back.isEmpty()) {
//
//                    Card card = new Card(
//                            word,
//                            back,
//                            sentence
//                    );
//
//                    boolean success = databaseHelper.addOne(card);
//
//                    if (success) {
//                        foreign.setText("");
//                        translation.setText("");
//                        context.setText("");
//                        arrayList = (ArrayList<Card>) databaseHelper.getAll();
//                        adapter.update(arrayList);
//                        adapter.notifyDataSetChanged();
//                    } else {
//
//                    }
//                }
//
//            }
//        });

    }

    public void updateDecks(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        adapterDecks.updateDeckList(decks);
        adapterDecks.notifyDataSetChanged();
    }

    public void openDialog() {
        DeckButtonDialogActivity deckButtonDialogActivity = new DeckButtonDialogActivity();
        deckButtonDialogActivity.show(getChildFragmentManager(), "Dialog");
        Log.d(TAG, "openDialog: clicked Dialog");
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
