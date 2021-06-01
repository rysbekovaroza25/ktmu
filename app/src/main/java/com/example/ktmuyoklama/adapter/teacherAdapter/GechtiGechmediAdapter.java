package com.example.ktmuyoklama.adapter.teacherAdapter;

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
import com.example.ktmuyoklama.data.response.MyLessonStudentsList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GechtiGechmediAdapter extends RecyclerView.Adapter<GechtiGechmediAdapter.ViewHolder> {
    private List<MyLessonStudentsList> dataList;
    private List<Integer> enrollIdListSUCCEED = new ArrayList<>();
    private List<String> newStateEnrollSUCCEED = new ArrayList<>();
    private List<Integer> enrollIdListFAILED = new ArrayList<>();
    private List<String> newStateEnrollFAILED = new ArrayList<>();

    private Context context;
    private boolean isSelectedAll = false;

    public GechtiGechmediAdapter(Context context, List<MyLessonStudentsList> listData) {
        this.context = context;
        this.dataList = listData;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item_lesson, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName() + " " + dataList.get(position).getSurname());
        holder.checkBox.setTag(position);

        if (!isSelectedAll) {
            isSelectedAll = true;
            holder.checkBox.setChecked(false);
            for(int i=0; i<dataList.size(); i++){

                enrollIdListSUCCEED.remove(dataList.get(position).getEnrollId());
                newStateEnrollSUCCEED.remove("SUCCEED");
                enrollIdListFAILED.add(dataList.get(i).getEnrollId());
                newStateEnrollFAILED.add("FAILED");
            }

        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    enrollIdListSUCCEED.add(dataList.get(position).getEnrollId());
                    newStateEnrollSUCCEED.add("SUCCEED");

                    enrollIdListFAILED.remove(dataList.get(position).getEnrollId());
                    newStateEnrollFAILED.remove("FAILED");
                }
                if(!isChecked){
                    enrollIdListSUCCEED.remove(dataList.get(position).getEnrollId());
                    newStateEnrollSUCCEED.remove("SUCCEED");

                    enrollIdListFAILED.add(dataList.get(position).getEnrollId());
                    newStateEnrollFAILED.add("FAILED");

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public CardView cardView;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.itemName);
            cardView = itemView.findViewById(R.id.cv);
            this.checkBox = itemView.findViewById(R.id.checkBox_select);
        }
    }


    public List<Integer> getEnrollIdListSUCCEED() {
        return enrollIdListSUCCEED;
    }

    public void setEnrollIdListSUCCEED(List<Integer> enrollIdListSUCCEED) {
        this.enrollIdListSUCCEED = enrollIdListSUCCEED;
    }

    public List<String> getNewStateEnrollSUCCEED() {
        return newStateEnrollSUCCEED;
    }

    public void setNewStateEnrollSUCCEED(List<String> newStateEnrollSUCCEED) {
        this.newStateEnrollSUCCEED = newStateEnrollSUCCEED;
    }

    public List<Integer> getEnrollIdListFAILED() {
        return enrollIdListFAILED;
    }

    public void setEnrollIdListFAILED(List<Integer> enrollIdListFAILED) {
        this.enrollIdListFAILED = enrollIdListFAILED;
    }

    public List<String> getNewStateEnrollFAILED() {
        return newStateEnrollFAILED;
    }

    public void setNewStateEnrollFAILED(List<String> newStateEnrollFAILED) {
        this.newStateEnrollFAILED = newStateEnrollFAILED;
    }
}