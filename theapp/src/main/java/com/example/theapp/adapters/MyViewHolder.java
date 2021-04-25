package com.example.theapp.adapters;

import android.view.View;
import android.widget.TextView;

import com.example.theapp.R;

public class MyViewHolder {
    private TextView wordToLearn;
    private TextView translation;
    private TextView context;

    public MyViewHolder(View view) {
        wordToLearn = view.findViewById(R.id.item_wordToLearn);
        translation = view.findViewById(R.id.item_translation);
        context = view.findViewById(R.id.context);
    }
}
