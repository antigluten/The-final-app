package com.example.theapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.theapp.R;
import com.example.theapp.adapters.MyAdapter;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeckFragment extends Fragment {
    private String TAG = "ANTIGLUTEN";

    private ListView listView;
    private ArrayList<Card> arrayList;
    private ArrayAdapter<Card> adapter;
//    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        Button submit = rootView.findViewById(R.id.cardButton);
        EditText foreign = rootView.findViewById(R.id.foreignWord);
        EditText translation = rootView.findViewById(R.id.translation);
        EditText context = rootView.findViewById(R.id.context);

        listView = rootView.findViewById(R.id.recycler_view);
        arrayList = (ArrayList<Card>) databaseHelper.getAll();

//        adapter = new MyAdapter(
//                Objects.requireNonNull(getContext()),
//                arrayList
//        );

        adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arrayList
        );

        listView.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                Card card = new Card(
                        foreign.getText().toString().trim(),
                        translation.getText().toString().trim(),
                        context.getText().toString().trim()
                );
                Toast.makeText(getContext(), card.toString(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + card.toString());

                boolean success = databaseHelper.addOne(card);
                Toast.makeText(getContext(), "Success = " + success, Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + success);

                if (success) {
                    foreign.setText("");
                    translation.setText("");
                    context.setText("");
                }

                arrayList.add(card);
                listView.setAdapter(adapter);
            }
        });



//        boolean success = databaseHelper.addOne(card);
//        Toast.makeText(getContext(), "Success = " + success, Toast.LENGTH_LONG).show();


//        Toast.makeText(getContext(), "Success = " + success, Toast.LENGTH_LONG).show();

        List<Card> list = databaseHelper.getAll();
        for (Card card1 : list) {
            Log.d(TAG, "onCreateView: " + card1.toString());
        }

        return rootView;
    }

}
