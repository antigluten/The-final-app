package com.example.theapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theapp.R;
import com.example.theapp.data.Card;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Card> arrayList;

    private OnItemClickListener listener;

    public RecyclerViewAdapter(Context context, ArrayList<Card> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = arrayList.get(position);
        holder.word.setText(card.getFrontWord());
        holder.translation.setText(card.getTranslationWord());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void update(ArrayList<Card> arrayList) {
        this.arrayList = arrayList;
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView word;
        public TextView translation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.recyclerViewWord);
            translation = itemView.findViewById(R.id.recyclerViewTranslation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
