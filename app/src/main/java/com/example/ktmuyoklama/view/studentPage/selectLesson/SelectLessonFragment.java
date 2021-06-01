package com.example.ktmuyoklama.view.studentPage.selectLesson;

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

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.studentAdapter.SelectLessonAdapter;
import com.example.ktmuyoklama.data.model.studentPageModel.RegisterLessonStudents;
import com.example.ktmuyoklama.data.response.SelectLessonStudent;
import com.example.ktmuyoklama.viewModel.studentViewModel.SelectLessonViewModel;

import java.util.ArrayList;
import java.util.List;

public class SelectLessonFragment extends Fragment {

    private RecyclerView recyclerView;
    private SelectLessonViewModel selectLessonViewModel;
    private ArrayList<SelectLessonStudent> arrayList;
    private SelectLessonAdapter selectLessonAdapter;
    private Button selectLessonSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_lesson, container, false);
        selectLessonSubmit = view.findViewById(R.id.submit_select_lesson);
        recyclerView = view.findViewById(R.id.selectLessonRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        selectLessonViewModel = ViewModelProviders.of(this).get(SelectLessonViewModel.class);
        getData();

        selectLessonSubmit.setOnClickListener(v -> {
            System.out.println(selectLessonAdapter.getSelectMyEnrollId());
            selectLesson();
        });

        return view;
    }

    private void selectLesson() {
        RegisterLessonStudents registerLessonStudents = new RegisterLessonStudents(selectLessonAdapter.getSelectMyEnrollId());
        selectLessonViewModel.getLiveDataSelectLesson(registerLessonStudents).observe(getViewLifecycleOwner(), new Observer<RegisterLessonStudents>() {
            @Override
            public void onChanged(RegisterLessonStudents myLessonLists) {
            }
        });
    }

    private void getData() {
        selectLessonViewModel.getLiveDataLessonRegister().observe(getViewLifecycleOwner(), new Observer<List<SelectLessonStudent>>() {
            @Override
            public void onChanged(List<SelectLessonStudent> myLessonLists) {
                arrayList = (ArrayList<SelectLessonStudent>) myLessonLists;
                selectLessonAdapter = new SelectLessonAdapter(getContext(), arrayList);
                recyclerView.setAdapter(selectLessonAdapter);
            }
        });
    }
}