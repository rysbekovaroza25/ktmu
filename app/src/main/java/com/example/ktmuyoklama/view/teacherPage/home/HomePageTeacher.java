package com.example.ktmuyoklama.view.teacherPage.home;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ktmuyoklama.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_teacher);
        final BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_teacher);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_teacher, new MyLessonListFragment())
                .commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectItem = null;
            switch (item.getItemId()){
                case R.id.home_page_teacher:
                    selectItem = new MyLessonListFragment();
                    break;
                case R.id.yoklama_page:
                    selectItem = new YoklamaFaceFragment();
                    break;
                case R.id.control_teacher_page:
                    selectItem = new ControlTeacherPanelFragment();
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_teacher, selectItem)
                    .commit();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_home_page_teacher, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
