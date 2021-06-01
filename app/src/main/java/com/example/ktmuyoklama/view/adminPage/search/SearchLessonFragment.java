package com.example.ktmuyoklama.view.adminPage.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.adminAdapter.LessonListAdapter;
import com.example.ktmuyoklama.adapter.adminAdapter.TeacherListAdapter;
import com.example.ktmuyoklama.data.model.CoachId;
import com.example.ktmuyoklama.data.model.TeacherList;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;
import com.example.ktmuyoklama.viewModel.adminViewModel.LessonViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchLessonFragment extends Fragment {


    private LessonListAdapter adapter;
    private RecyclerView recyclerView;
    public ArrayList<CoachId> dataArrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    LessonViewModel lessonViewModel;
    View view;
    ImageView backHome;
    SearchView searchViewStudent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_lesson, container, false);
        recyclerView = view.findViewById(R.id.listLessonRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);

        backHome = view.findViewById(R.id.back);
        searchViewStudent = view.findViewById(R.id.search);
        backHome.setOnClickListener(v -> {
            Intent backIntent = new Intent(getActivity(), HomePageAdmin.class);
            startActivity(backIntent);
        });

        searchViewStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchResult(newText);
                return false;
            }
        });

        getData();

        return view;
    }

    private void getData() {
        lessonViewModel.getLiveDataLessonList().observe(getViewLifecycleOwner(), new Observer<List<CoachId>>() {
            @Override
            public void onChanged(List<CoachId> data) {
                dataArrayList = (ArrayList<CoachId>) data;
                adapter = new LessonListAdapter(dataArrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void searchResult(String query) {
        String q = query.toLowerCase();

        List<CoachId> coachIds = new ArrayList<>();

        for (CoachId coachId : dataArrayList) {
            if (coachId.toString().toLowerCase().contains(q)) {
                coachIds.add(coachId);
            }
        }
        adapter = new LessonListAdapter(coachIds);
        recyclerView.setAdapter(adapter);

    }

}