package com.example.ktmuyoklama.view.teacherPage.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.teacherAdapter.MyLessonListAdapter;
import com.example.ktmuyoklama.adapter.teacherAdapter.MyLessonStudentsListAdapter;
import com.example.ktmuyoklama.data.model.teacherPageModel.Yoklama;
import com.example.ktmuyoklama.data.response.MyLessonStudentsList;
import com.example.ktmuyoklama.viewModel.teacherViewModel.MyLessonStudentsListViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyLessonStudentsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyLessonStudentsListViewModel myLessonStudentsListViewModel;
    private ArrayList<MyLessonStudentsList> arrayList;
    private MyLessonStudentsListAdapter myLessonStudentsListAdapter;
    private Button submitYoklama;
    private EditText name;
    private TextView lessonKod;
    private MyLessonListAdapter myLessonListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_lesson_students_list, container, false);
        name = view.findViewById(R.id.name);
        lessonKod = view.findViewById(R.id.lessonKod);
        lessonKod.setText(MyLessonListAdapter.getLessonKod());
        submitYoklama = view.findViewById(R.id.submit_yoklama);
        recyclerView = view.findViewById(R.id.myLessonStudentsListRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myLessonStudentsListViewModel = ViewModelProviders.of(this).get(MyLessonStudentsListViewModel.class);
        getData();

        submitYoklama.setOnClickListener(v -> {
            String name_ = name.getText().toString().trim();
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            yoklama(date, myLessonListAdapter.getKod_id(), name_, myLessonStudentsListAdapter.getStudent_id_list());
        });

        return view;
    }

    private void yoklama(String date, int kod_id, String name_, List<Integer> student_id_list) {
        Yoklama yoklama = new Yoklama(date, kod_id, name_, student_id_list);
        myLessonStudentsListViewModel.getLiveDataYoklama(yoklama).observe(getViewLifecycleOwner(), new Observer<Yoklama>() {
            @Override
            public void onChanged(Yoklama yoklama) {}
        });
    }

    private void getData() {
        myLessonStudentsListViewModel.getLiveDataLessonRegister().observe(getViewLifecycleOwner(), new Observer<List<MyLessonStudentsList>>() {
            @Override
            public void onChanged(List<MyLessonStudentsList> myLessonStudentsLists) {
                arrayList = (ArrayList<MyLessonStudentsList>) myLessonStudentsLists;
                myLessonStudentsListAdapter = new MyLessonStudentsListAdapter(getContext(), arrayList);
                recyclerView.setAdapter(myLessonStudentsListAdapter);
            }
        });
    }


}