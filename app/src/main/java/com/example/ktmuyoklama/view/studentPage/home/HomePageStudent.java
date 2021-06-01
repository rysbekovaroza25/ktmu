package com.example.ktmuyoklama.view.studentPage.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.view.studentPage.selectLesson.SelectLessonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_student);
        final BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_student);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_student, new DiscontinuityFragment())
                .commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectItem = null;
            switch (item.getItemId()){
                case R.id.yoklama_page:
                    selectItem = new DiscontinuityFragment();
                    break;
                case R.id.control_student_page:
                    selectItem = new SelectLessonFragment();
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_student, selectItem)
                    .commit();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_home_page_student, menu);
        return super.onCreateOptionsMenu(menu);
    }
}