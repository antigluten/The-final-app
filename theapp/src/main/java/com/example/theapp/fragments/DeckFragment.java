package com.example.theapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.CardTest;
import com.example.theapp.R;
import com.example.theapp.adapters.MyAdapter;
import com.example.theapp.browser.RecyclerViewAdapter;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeckFragment extends Fragment {
    private String TAG = "ANTIGLUTEN";

    private Button button;
    private EditText editText;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private ArrayList<Card> arrayList = new ArrayList<>();

    private EditText foreign;
    private EditText translation;
    private EditText context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);

        foreign = rootView.findViewById(R.id.foreignWord);
        translation = rootView.findViewById(R.id.translation);
        context = rootView.findViewById(R.id.context);

        button = rootView.findViewById(R.id.cardButton);

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        arrayList = (ArrayList<Card>) databaseHelper.getAll();

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(getContext(), arrayList);
        recyclerView.setAdapter(adapter);

        Log.d(TAG, "onCreateView: " + adapter.getItemCount());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Card card = arrayList.get(position);
                Log.d(TAG, "Clicked: " + position);
                Intent intent = new Intent(getContext(), CardTest.class);
                intent.putExtra("Foreign", card.getFrontWord());
                intent.putExtra("Translation", card.getTranslationWord());
                intent.putExtra("Sentence", card.getContext());
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                String word = foreign.getText().toString().trim();
                String back = translation.getText().toString().trim();
                String sentence = context.getText().toString().trim();

                if (!word.isEmpty() && !back.isEmpty()) {

                    Card card = new Card(
                            word,
                            back,
                            sentence
                    );

                    boolean success = databaseHelper.addOne(card);

                    if (success) {
                        foreign.setText("");
                        translation.setText("");
                        context.setText("");
                        arrayList = (ArrayList<Card>) databaseHelper.getAll();
                        adapter.update(arrayList);
                        adapter.notifyDataSetChanged();
                    } else {

                    }
                }

            }
        });
    }


}
