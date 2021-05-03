package com.example.theapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;


public class DeckButtonDialogActivity extends AppCompatDialogFragment implements DialogInterface.OnDismissListener {
    private EditText deckName;
    private DialogInterface.OnDismissListener onDismissListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.deck_dialog, null);

        builder.setView(view);

        Button buttonAdd = view.findViewById(R.id.dialogButtonAdd);
        Button buttonCancel = view.findViewById(R.id.dialogButtonCancel);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = deckName.getText().toString().trim();
                if (!name.isEmpty()) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    boolean success = databaseHelper.addDeck(new Deck(name));
                    if (success) {
                        Toast.makeText(getContext(), "Success adding the deck", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
}
