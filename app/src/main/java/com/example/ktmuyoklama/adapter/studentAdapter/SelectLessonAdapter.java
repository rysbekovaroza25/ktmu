package com.example.ktmuyoklama.adapter.studentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.response.SelectLessonStudent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SelectLessonAdapter extends RecyclerView.Adapter<SelectLessonAdapter.ViewHolder> {

    private List<SelectLessonStudent> dataList;
    private Context context;
    private List<Integer> selectMyEnrollId = new ArrayList<>();

    public SelectLessonAdapter(Context context, List<SelectLessonStudent> listData) {
        this.context = context;
        this.dataList = listData;
    }

    @NonNull
    @NotNull
    @Override
    public SelectLessonAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item_select_lesson, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.kod.setText(dataList.get(position).getLessonKod());

        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectMyEnrollId.add(dataList.get(position).getEnrollId());
                }
                if(!isChecked){
                    selectMyEnrollId.remove(dataList.get(position).getEnrollId());
                }
            }
        });
        setSelectMyEnrollId(selectMyEnrollId);
        System.out.println("Lesson id list : " + selectMyEnrollId);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView kod;
        public CardView cardView;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            this.kod = itemView.findViewById(R.id.itemName);
            this.checkBox = itemView.findViewById(R.id.checkBox_select);
            cardView = itemView.findViewById(R.id.cv);
        }
    }

    public List<Integer> getSelectMyEnrollId() {
        return selectMyEnrollId;
    }

    public void setSelectMyEnrollId(List<Integer> selectMyEnrollId) {
        this.selectMyEnrollId = selectMyEnrollId;
    }
}
