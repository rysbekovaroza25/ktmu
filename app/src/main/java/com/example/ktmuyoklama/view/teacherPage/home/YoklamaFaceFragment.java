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

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.teacherAdapter.YoklamaFaceAdapter;
import com.example.ktmuyoklama.data.model.MyLessonList;
import com.example.ktmuyoklama.viewModel.teacherViewModel.MyLessonListViewModel;

import java.util.ArrayList;
import java.util.List;

public class YoklamaFaceFragment extends Fragment {

    private RecyclerView recyclerView;
    private YoklamaFaceAdapter yoklamaFaceAdapter;
    private ArrayList<MyLessonList> arrayList;
    private MyLessonListViewModel myLessonListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yoklama_face, container, false);

        recyclerView = view.findViewById(R.id.myLessonListRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myLessonListViewModel = ViewModelProviders.of(this).get(MyLessonListViewModel.class);

        getData();


        return view;
    }

    private void getData() {
        myLessonListViewModel.getLiveDataLessonRegister().observe(getViewLifecycleOwner(), new Observer<List<MyLessonList>>() {
            @Override
            public void onChanged(List<MyLessonList> myLessonLists) {
                arrayList = (ArrayList<MyLessonList>) myLessonLists;
                yoklamaFaceAdapter = new YoklamaFaceAdapter(getContext(), arrayList);
                recyclerView.setAdapter(yoklamaFaceAdapter);
            }
        });
    }
}