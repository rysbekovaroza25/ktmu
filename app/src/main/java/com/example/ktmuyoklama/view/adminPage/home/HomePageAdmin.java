package com.example.ktmuyoklama.view.adminPage.home;

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
import android.widget.ImageView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.view.adminPage.control.ControlFragment;
import com.example.ktmuyoklama.view.adminPage.register.Lesson_Register_Fragment;
import com.example.ktmuyoklama.view.adminPage.register.Student_Register_Fragment;
import com.example.ktmuyoklama.view.adminPage.register.Teacher_Register_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageAdmin extends AppCompatActivity implements BottomSheetFragment.ItemClickListener {

    ImageView menu, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_admin);
        final BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_admin, new Student_Register_Fragment())
                .commit();

        menu = findViewById(R.id.menu);
        search = findViewById(R.id.search);

        menu.setOnClickListener(v -> {
//            Intent profile = new Intent(HomePageAdmin.this, Profile.class);
//            startActivity(profile);
        });

        search.setOnClickListener(v -> {
            BottomSheetFragment addPhotoBottomDialogFragment =
                    BottomSheetFragment.newInstance();
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    BottomSheetFragment.TAG);
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectItem = null;
            switch (item.getItemId()){
               case R.id.student_reg_page:
                   selectItem = new Student_Register_Fragment();
                   break;
               case R.id.teacher_reg_page:
                   selectItem = new Teacher_Register_Fragment();
                   break;
               case R.id.lesson_reg_page:
                   selectItem = new Lesson_Register_Fragment();
                   break;
                case R.id.control_page:
                   selectItem = new ControlFragment();
                   break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_admin, selectItem)
                    .commit();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_home_page, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void onItemClick(String item) {

    }
}