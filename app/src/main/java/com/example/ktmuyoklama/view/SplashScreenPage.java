package com.example.ktmuyoklama.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ktmuyoklama.R;

public class SplashScreenPage extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_page);

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashScreenPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}