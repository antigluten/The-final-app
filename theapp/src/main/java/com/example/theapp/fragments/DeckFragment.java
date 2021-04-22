package com.example.theapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.R;

import java.util.ArrayList;

public class DeckFragment extends Fragment {
    String TAG = "Antigluten";


    private ListView recyclerView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        arrayList = new ArrayList<>();


        EditText editText = rootView.findViewById(R.id.test_edit);
        Button submit = rootView.findViewById(R.id.test_button);


        arrayAdapter = new ArrayAdapter<>(
                getContext(),
//                android.R.layout.simple_list_item_1,
                R.layout.item_test,
                arrayList
        );


        recyclerView.setAdapter(arrayAdapter);

        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                if (arrayList.size() != 0) {
                    if (!arrayList.contains(text)) {
                        arrayList.add(text);
                        arrayAdapter.notifyDataSetChanged();
                        editText.setText("");
                    }
                } else {
                    arrayList.add(text);
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });

        return rootView;
    }
}
