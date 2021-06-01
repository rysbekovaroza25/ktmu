package com.example.ktmuyoklama.adapter.teacherAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.MyLessonList;
import com.example.ktmuyoklama.view.teacherPage.home.GechtiGechmediFragment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ControlTeacherPanelAdapter extends RecyclerView.Adapter<ControlTeacherPanelAdapter.ViewHolder> {
    private List<MyLessonList> dataList;
    private Context context;
    public static int kod_id = 0;
    public static String lessonKod;

    public ControlTeacherPanelAdapter(Context context, List<MyLessonList> listData) {
        this.context = context;
        this.dataList = listData;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.my_lesson_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.lessonKod.setText(dataList.get(position).getKod());

        if (dataList.get(position).getStatus().toString() == "true")
            holder.lessonStatus.setText("Theory");
        else
            holder.lessonStatus.setText("Practice");

        holder.imageButton.setOnClickListener(view -> {

            System.out.println(dataList.get(position).getId());
            kod_id = dataList.get(position).getId();
            setKod_id(kod_id);

            lessonKod = dataList.get(position).getKod();
            setLessonKod(lessonKod);

            Fragment fragment = new GechtiGechmediFragment();
            FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.container_teacher, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            Toast.makeText(context, dataList.get(position).getKod(), Toast.LENGTH_LONG).show();

        });

        holder.itemView.setOnClickListener(view -> {

            System.out.println(dataList.get(position).getId());
            kod_id = dataList.get(position).getId();
            setKod_id(kod_id);
            lessonKod = dataList.get(position).getKod();
            setLessonKod(lessonKod);

            Fragment fragment = new GechtiGechmediFragment();
            FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.container_teacher, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            Toast.makeText(context, dataList.get(position).getKod(), Toast.LENGTH_LONG).show();

        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lessonKod;
        public TextView lessonStatus;
        public CardView cardView;
        public ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            this.lessonKod = itemView.findViewById(R.id.itemKod);
            this.lessonStatus = itemView.findViewById(R.id.itemStatus);
            this.imageButton = itemView.findViewById(R.id.checkBox_my_lesson);
            this.cardView = itemView.findViewById(R.id.cv);

        }
    }

    public static int getKod_id() {
        return kod_id;
    }

    public static void setKod_id(int kod_id) {
        ControlTeacherPanelAdapter.kod_id = kod_id;
    }

    public static String getLessonKod() {
        return lessonKod;
    }

    public static void setLessonKod(String lessonKod) {
        ControlTeacherPanelAdapter.lessonKod = lessonKod;
    }
}


