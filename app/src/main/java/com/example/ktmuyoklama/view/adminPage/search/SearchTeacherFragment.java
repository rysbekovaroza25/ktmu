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
import com.example.ktmuyoklama.adapter.adminAdapter.StudentListAdapter;
import com.example.ktmuyoklama.adapter.adminAdapter.TeacherListAdapter;
import com.example.ktmuyoklama.data.model.StudentInfo;
import com.example.ktmuyoklama.data.model.TeacherList;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;
import com.example.ktmuyoklama.viewModel.adminViewModel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchTeacherFragment extends Fragment {

    private TeacherListAdapter adapter;
    private RecyclerView recyclerView;
    public ArrayList<TeacherList> dataArrayList = new ArrayList<>();
    public ArrayAdapter<String> arrayAdapter;
    private TeacherViewModel teacherViewModel;
    private SearchView searchViewTeacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_teacher, container, false);
        recyclerView = view.findViewById(R.id.listTeacherRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel.class);

        ImageView backHome = view.findViewById(R.id.back);
        searchViewTeacher = view.findViewById(R.id.search);

        backHome.setOnClickListener(v -> {
            Intent backIntent = new Intent(getActivity(), HomePageAdmin.class);
            startActivity(backIntent);
        });
        searchViewTeacher.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        teacherViewModel.getLiveDataTeacher().observe(getViewLifecycleOwner(), new Observer<List<TeacherList>>() {
            @Override
            public void onChanged(List<TeacherList> data) {
                dataArrayList = (ArrayList<TeacherList>) data;
                adapter = new TeacherListAdapter(dataArrayList);
                recyclerView.setAdapter(adapter);


            }
        });
    }

    private void searchResult(String query) {
        String q = query.toLowerCase();

        List<TeacherList> teacherLists = new ArrayList<>();

        for (TeacherList teacherList : dataArrayList) {
            if (teacherList.toString().toLowerCase().contains(q)) {
                teacherLists.add(teacherList);
            }
        }
        adapter = new TeacherListAdapter(teacherLists);
        recyclerView.setAdapter(adapter);

    }
}