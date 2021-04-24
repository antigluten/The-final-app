package com.example.theapp.fragments;

import android.database.sqlite.SQLiteDatabase;
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

import com.example.theapp.Database;
import com.example.theapp.R;
import com.example.theapp.data.Contact;
import com.example.theapp.model.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class DeckFragment extends Fragment {
    String TAG = "ANTIGLUTEN";


    private ListView recyclerView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_deck, container, false);

        Database database = new Database();
        database.main();
//
//        DatabaseHandler db = new DatabaseHandler(getContext());
//        Contact vladimir = new Contact();
//        vladimir.setName("Vladimir");
//        vladimir.setPhoheNumber("123456789");
//        db.addContact(vladimir);
//
//        Contact jeremy = new Contact();
//        jeremy.setName("Jeremy");
//        jeremy.setPhoheNumber("007");
//        db.addContact(jeremy);
//
//
//        List<Contact> contactList = new ArrayList<>();
//        contactList = db.getAllContacts();
//
//        for (Contact contact : contactList) {
//            Log.d(TAG, "addContact: " + contact.getId() + " " + contact.getName() + " " + contact.getPhoheNumber());
//        }
//
//        recyclerView = rootView.findViewById(R.id.recycler_view);
//        arrayList = new ArrayList<>();
//
//
//        EditText editText = rootView.findViewById(R.id.test_edit);
//        Button submit = rootView.findViewById(R.id.test_button);
//
//
//        arrayAdapter = new ArrayAdapter<>(
//                getContext(),
//                R.layout.item_test,
//                arrayList
//        );
//
//
//        recyclerView.setAdapter(arrayAdapter);
//
//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "onItemClick: " + position + " " + arrayList.get(position));
//            }
//        });
//
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String text = editText.getText().toString().trim();
////                if (arrayList.size() != 0) {
////                    if (!arrayList.contains(text)) {
////                        arrayList.add(text);
////                        arrayAdapter.notifyDataSetChanged();
////                        editText.setText("");
////                    }
////                } else {
////                    arrayList.add(text);
////                    arrayAdapter.notifyDataSetChanged();
////                    editText.setText("");
////                }
////                String text = editText.getText().toString().trim();
////                Contact contact = new Contact();
////                contact.setName(text);
////                contact.setPhoheNumber(null);
////                db.addContact(contact);
//            }
//        });

        return rootView;
    }

}
