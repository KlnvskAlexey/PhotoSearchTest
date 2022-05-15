package com.example.retrofitagain2.searchHistory;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.R;

import java.util.ArrayList;

public class SearchHistoryRecyclerViewAdapter extends RecyclerView.Adapter<SearchHistoryRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> photoSearchList = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    void updatePhotosListHistory(@NonNull ArrayList<String> updatedListHistory) {
        photoSearchList.addAll(updatedListHistory);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(photoSearchList.get(position));
    }

    @Override
    public int getItemCount() {
        return photoSearchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.historyItemTV);
        }
    }
}
