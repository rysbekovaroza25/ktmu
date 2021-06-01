package com.example.ktmuyoklama.adapter.adminAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.StudentInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
    private List<StudentInfo> dataList;

    public StudentListAdapter(List<StudentInfo> listData) {
        this.dataList = listData;
    }

    public void filterList(List<StudentInfo> filterList) {
        dataList = filterList;
        notifyDataSetChanged();
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
        holder.name.setText(new StringBuilder().append(dataList.get(position).getStudentName()).append(" ").append(dataList.get(position).getStudentSurname()).toString());
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
