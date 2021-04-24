package com.example.theapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.theapp.R;
import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DeckFragment extends Fragment {
    private String TAG = "ANTIGLUTEN";

    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
//
//        Card card;
//
//        try {
//            card = new Card("Insecure", "Not confident in relationships with others", "I feel insecure in the new company");
//            Toast.makeText(getContext(), card.toString(), Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            Toast.makeText(getContext(), "Error creating a card", Toast.LENGTH_LONG).show();
//            card = new Card("1", "2", "3");
//        }

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
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
