package com.liaichi.gatracker.database.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.liaichi.gatracker.R;

public class GATViewHolder extends RecyclerView.ViewHolder{

  private final TextView GATViewItem;

  private GATViewHolder(View gymLogView) {
    super(gymLogView);
    GATViewItem = gymLogView.findViewById(R.id.recyclerItemTextView);

  }

  public void bind(String text) {
    GATViewItem.setText(text);
  }

  static GATViewHolder create(ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.gat_recycler_item, parent, false);
    return new GATViewHolder(view);
  }
}
