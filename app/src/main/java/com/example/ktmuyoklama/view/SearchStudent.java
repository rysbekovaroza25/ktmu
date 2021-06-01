package com.example.ktmuyoklama.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;

public class SearchStudent extends AppCompatActivity {

    ImageView backHome;
    SearchView searchViewStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student);

        backHome = findViewById(R.id.back);
        searchViewStudent = findViewById(R.id.search);

        backHome.setOnClickListener(v -> {
            Intent backIntent = new Intent(SearchStudent.this, HomePageAdmin.class);
            startActivity(backIntent);

        });
    }
}