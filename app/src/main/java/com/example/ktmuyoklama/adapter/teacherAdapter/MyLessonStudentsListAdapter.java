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
import com.example.ktmuyoklama.data.request.MyListSelectedStudent;
import com.example.ktmuyoklama.data.response.MyLessonStudentsList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyLessonStudentsListAdapter extends RecyclerView.Adapter<MyLessonStudentsListAdapter.ViewHolder> {
    private List<MyLessonStudentsList> dataList;
    private List<Integer> student_id_list = new ArrayList<>();
    private Context context;
    private MyListSelectedStudent myListSelectedStudent;
    public MyLessonStudentsListAdapter(Context context, List<MyLessonStudentsList> listData) {
        this.context = context;
        this.dataList = listData;
    }

    @NonNull
    @NotNull
    @Override
    public MyLessonStudentsListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item_lesson, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyLessonStudentsListAdapter.ViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName() + " " + dataList.get(position).getSurname());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_id_list.add(dataList.get(position).getStudentId());
                }
                if(!isChecked){
                    student_id_list.remove(dataList.get(position).getStudentId());
                }

                System.out.println("student list : " + student_id_list);

            }
        });

        setStudent_id_list(student_id_list);
        System.out.println("student list in set student id list : " + student_id_list);
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


    public List<Integer> getStudent_id_list() {
        return student_id_list;
    }

    public void setStudent_id_list(List<Integer> student_id_list) {
        this.student_id_list = student_id_list;
    }
}

