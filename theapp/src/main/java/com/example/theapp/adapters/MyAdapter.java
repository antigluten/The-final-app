package com.example.theapp.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        MyViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_test, null);
// Do some initialization
//Retrieve the view on the item layout and set the value.
            viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);
        }
        //TODO
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
//Retrieve your object
        Card data = getItem(position);
        viewHolder..setTypeface(m_Font);
        viewHolder.txt.setText(data.text);
        viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(data.imageAddr));
    }
}

