package com.example.theapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.adapters.RecyclerViewAdapterDecks;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;

import java.util.ArrayList;

public class DeckButtonDialogActivity extends AppCompatDialogFragment implements DialogInterface.OnDismissListener {
    private EditText deckName;
    private DialogInterface.OnDismissListener onDismissListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.deck_dialog, null);

        builder.setView(view).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = deckName.getText().toString().trim();
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                boolean success = databaseHelper.addDeck(new Deck(name));
                if (success) {
                    Toast.makeText(getContext(), "Success adding the deck", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
//                updateDecks(new View(getContext()));

            }
        });

        deckName = view.findViewById(R.id.deckNameDialog);
        return builder.create();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    public void updateDecks(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Deck> decks = (ArrayList<Deck>) databaseHelper.getAllDecks();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDeck);
        RecyclerViewAdapterDecks adapterDecks = new RecyclerViewAdapterDecks(getContext(), decks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterDecks);
//            adapterDecks.updateDeckList(decks);
//            adapterDecks.notifyDataSetChanged();
    }

}
