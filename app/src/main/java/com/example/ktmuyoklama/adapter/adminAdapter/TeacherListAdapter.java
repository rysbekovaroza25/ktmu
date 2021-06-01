package com.example.ktmuyoklama.adapter.adminAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.TeacherList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> {
    private List<TeacherList> dataList;

    public TeacherListAdapter(List<TeacherList> listData) {
        this.dataList = listData;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getCoachName() + " " + dataList.get(position).getCoachSurname());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.itemName);
            cardView = itemView.findViewById(R.id.cv);
        }
    }
}
