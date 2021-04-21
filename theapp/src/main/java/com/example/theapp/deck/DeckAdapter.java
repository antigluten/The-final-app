package com.example.theapp.deck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.R;

import java.util.ArrayList;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckViewHolder> {

    private ArrayList<Deck> items;
    int rowIndex = -1;

    public DeckAdapter(ArrayList<Deck> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        DeckViewHolder deckViewHolder = new DeckViewHolder(view);
        return deckViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        Deck currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = position;
                notifyDataSetChanged();
            }
        });

        if (rowIndex == position) {
            holder.linearLayout.setBackgroundResource(R.drawable.recycler_shape);
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.recycler_view_selected);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class DeckViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public DeckViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_recycler_text);
            imageView = itemView.findViewById(R.id.recycler_item_image);
            linearLayout = itemView.findViewById(R.id.recycler_view_item);
        }
    }
}
