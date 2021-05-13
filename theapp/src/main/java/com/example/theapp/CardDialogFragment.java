package com.example.theapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CardDialogFragment extends DialogFragment {
    private DialogInterface.OnDismissListener onDismissListener;
    private String deckName;

    public CardDialogFragment(String deckName) {
        this.deckName = deckName;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Translucent_NoTitleBar);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.ThemeOverlay_App_MaterialAlertDialog);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_card, null);
        builder.setView(view);

        EditText cardFront = view.findViewById(R.id.dialogCardFront);
        EditText cardTranslation = view.findViewById(R.id.dialogCardTranslation);
        EditText cardContext = view.findViewById(R.id.dialogCardContext);

        Button cardAdd = view.findViewById(R.id.dialogCardButtonAdd);
        Button cardCancel = view.findViewById(R.id.dialogCardButtonCancel);

        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String front = cardFront.getText().toString().trim();
                String translation = cardTranslation.getText().toString().trim();
                String sentence = cardContext.getText().toString().trim();

                if (!front.isEmpty() && !translation.isEmpty()) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    ArrayList<Card> cards = (ArrayList<Card>) databaseHelper.getAllCardWithDeckName(getName());
                    boolean cardExist = true;
                    for (Card card : cards) {
                        if (front.equals(card.getFrontWord())) {
                            cardFront.requestFocus();
                            Toast.makeText(getContext(), "You have card with this front side", Toast.LENGTH_SHORT).show();
                            cardExist = false;
                            break;
                        }
                    }
                    if (cardExist) {
                        Card card = new Card(front, translation, sentence, 0,
                                getToday(), getToday(), deckName);
                        boolean success = databaseHelper.addCard(card);
                        if (success) {
//                            databaseHelper.addToTotal(card);
                            Toast.makeText(getContext(), "Success adding the card", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Failed adding the card", Toast.LENGTH_SHORT).show();
                        }
                    }




                } else if (!front.isEmpty()) {
                    cardTranslation.requestFocus();
                    Toast.makeText(getContext(), "Enter the translation", Toast.LENGTH_SHORT).show();
                }
                else if (!translation.isEmpty()) {
                    cardFront.requestFocus();
                    Toast.makeText(getContext(), "Enter deck name", Toast.LENGTH_SHORT).show();
                } else {
                    cardFront.requestFocus();
                    cardTranslation.requestFocus();
                    Toast.makeText(getContext(), "Enter front side and translation", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

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

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getName() {
        return deckName;
    }

    public String getToday() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.UK);
        Log.d("ANTIGLUTEN", "Adding card using method getToday: " + dateFormat.format(calendar.getTime()));
        return dateFormat.format(calendar.getTime());
    }
}
