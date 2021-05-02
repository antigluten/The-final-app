package com.example.theapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class DeckButtonDialogActivity extends AppCompatDialogFragment {
    private EditText deckName;
    private DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.deck_dialog, null);

        builder.setView(view).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = deckName.getText().toString().trim();
                Intent i = new Intent();
                i.putExtra("deckName", name);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
            }
        });

        deckName = view.findViewById(R.id.deckNameDialog);
        return builder.create();
    }


    public interface DialogListener {
        void transferText(String deckName);
    }
}
