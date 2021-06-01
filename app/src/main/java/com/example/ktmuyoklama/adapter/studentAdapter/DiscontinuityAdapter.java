package com.example.ktmuyoklama.adapter.studentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.studentPageModel.StudentPercentage;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiscontinuityAdapter extends RecyclerView.Adapter<DiscontinuityAdapter.ViewHolder> {
    private List<StudentPercentage> dataList;
    private Context context;

    public DiscontinuityAdapter(Context context, List<StudentPercentage> listData) {
        this.context = context;
        this.dataList = listData;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.devamsizlik, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.lessonKod.setText(dataList.get(position).getName());
        holder.devamsizlik.setText(dataList.get(position).getPercentage().toString());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lessonKod;
        public TextView devamsizlik;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.lessonKod = itemView.findViewById(R.id.itemKod);
            this.devamsizlik = itemView.findViewById(R.id.itemStatus);
            this.cardView = itemView.findViewById(R.id.cv);

        }
    }
}


