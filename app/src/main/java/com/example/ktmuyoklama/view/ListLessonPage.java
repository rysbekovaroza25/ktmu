package com.example.ktmuyoklama.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.adminAdapter.LessonListAdapter;
import com.example.ktmuyoklama.data.model.CoachId;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;
import com.example.ktmuyoklama.viewModel.adminViewModel.LessonViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListLessonPage extends AppCompatActivity {
    private LessonListAdapter adapter;
    private RecyclerView recyclerView;
    public ArrayList<CoachId> dataArrayList = new ArrayList<>();
    LessonViewModel lessonViewModel;
    ImageView backHome;
    SearchView searchViewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_lesson);

        recyclerView = findViewById(R.id.listLessonRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);

        backHome = findViewById(R.id.back);
        searchViewStudent = findViewById(R.id.search);
        backHome.setOnClickListener(v -> {
            Intent backIntent = new Intent(this, HomePageAdmin.class);
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
    }

    private void getData() {
        lessonViewModel.getLiveDataLessonList().observe(this, new Observer<List<CoachId>>() {
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