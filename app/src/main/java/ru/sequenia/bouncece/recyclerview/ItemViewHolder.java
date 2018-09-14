package ru.sequenia.bouncece.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.sequenia.bouncece.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView nameItem;

    ItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item, parent, false));
        nameItem = itemView.findViewById(R.id.name);
    }

    public void bind(String item) {
        nameItem.setText(item);
    }
}
