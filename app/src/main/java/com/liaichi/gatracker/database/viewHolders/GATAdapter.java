package com.liaichi.gatracker.database.viewHolders;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.liaichi.gatracker.database.entities.GrAsTr;

public class GATAdapter extends
    ListAdapter<GrAsTr, GATViewHolder> {

  public GATAdapter(@NonNull DiffUtil.ItemCallback<GrAsTr> diffCallback) {
    super(diffCallback);
  }

  @NonNull
  @Override
  public GATViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return GATViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(@NonNull GATViewHolder holder, int position) {
    GrAsTr current = getItem(position);
    holder.bind(current.toString());
  }

  public static class GATDiff extends DiffUtil.ItemCallback<GrAsTr> {

    @Override
    public boolean areItemsTheSame(@NonNull GrAsTr oldItem,
        @NonNull GrAsTr newItem) {
      return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull GrAsTr oldItem,
        @NonNull GrAsTr newItem) {
      return oldItem.equals(newItem);
    }
  }
}
