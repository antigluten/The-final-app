package com.example.theapp.fragments;

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

import com.example.theapp.R;
import com.example.theapp.adapters.MyAdapter;
import com.example.theapp.browser.RecyclerAdapter;
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
    private RecyclerAdapter adapter;

    private ArrayList<String> arrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);

        editText = rootView.findViewById(R.id.foreignWord);
        button = rootView.findViewById(R.id.cardButton);


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(getContext(), arrayList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(editText.getText().toString().trim());
                adapter.notifyDataSetChanged();
            }
        });


    }


}
