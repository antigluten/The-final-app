package com.example.theapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.theapp.R;
import com.example.theapp.data.Card;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Card> {

    LayoutInflater inflater;

    public MyAdapter(@NonNull Context context, List<Card> data) {
        super(context, 0, data);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return new Card().getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        MyViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_test, null);

            viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }

        Card card = getItem(position);
        viewHolder.wordToLearn.setText(card.getFrontWord());
        viewHolder.translation.setText(card.getTranslationWord());
        if (card.getContext().isEmpty()) {
            viewHolder.context.setText("");
        }
        {
            viewHolder.context.setText(card.getContext());
        }

        return view;
    }
}

