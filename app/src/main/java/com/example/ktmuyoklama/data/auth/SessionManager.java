package com.example.ktmuyoklama.data.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.view.MainActivity;

public class SessionManager {

    Context context;
    private static final String USER_TOKEN = "token";

    private SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);

    public SessionManager(MainActivity mainActivity) {
        context = mainActivity;
    }

    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String fetchAuthToken(){
        return pref.getString(USER_TOKEN, null);
    }
}
