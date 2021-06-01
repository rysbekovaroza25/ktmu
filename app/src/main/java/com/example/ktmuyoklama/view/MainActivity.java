package com.example.ktmuyoklama.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.Login;
import com.example.ktmuyoklama.data.response.LoginResponse;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;
import com.example.ktmuyoklama.view.studentPage.home.HomePageStudent;
import com.example.ktmuyoklama.view.teacherPage.home.HomePageTeacher;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService apiService;

    private static String status, token;
    String MyPREFERENCES, usernameSP, passwordSP;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        EditText username = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);
        TextView forget_me = findViewById(R.id.login_forget_text);
        TextView register = findViewById(R.id.login_register_text);
        Button login = findViewById(R.id.login_button);

        apiService = ApiRequest.getAPIService();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        login.setOnClickListener(v -> {
            String username_ = username.getText().toString().trim();
            String password_ = password.getText().toString().trim();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(usernameSP, username_);
            editor.putString(passwordSP, password_);
            editor.apply();
            editor.commit();

            if (!TextUtils.isEmpty(username_) && !TextUtils.isEmpty(password_)) {
                login(username_, password_);

                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Бош талааларлы толтурунуз", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(v -> {
            Intent login_register = new Intent(MainActivity.this, RegisterPage.class);
            startActivity(login_register);
        });
    }

    private void login(String username, String password){

        Login login = new Login(username, password);

        Call<LoginResponse> allCategoriesListCall = apiService.login(login);

        allCategoriesListCall.enqueue(new Callback<LoginResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if (response.code() == 200) {

                    LoginResponse loginResponse = response.body();
//                    assert response.body() != null;
                    token = response.body().getToken();
//                    assert loginResponse != null;
                    status = loginResponse.getStatus();
                    setToken(token);
                    selectLogin(status);
                    System.out.println(token);
                    System.out.println(status);
                }
                else{
                    System.out.println(response.headers());
                    Log.d("LOG", "Ответ сервера: " + response.code());
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable throwable) {
                Log.d("LOG1", throwable.getMessage());
            }
        });
    }

    public void selectLogin(String status){
        switch (status) {
            case "ADMIN":
                Intent login_admin = new Intent(MainActivity.this, HomePageAdmin.class);
                startActivity(login_admin);
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                break;
            case "COACH":
                Intent login_teacher = new Intent(MainActivity.this, HomePageTeacher.class);
                startActivity(login_teacher);
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                break;
            case "STUDENT":
                Intent login_student = new Intent(MainActivity.this, HomePageStudent.class);
                startActivity(login_student);
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public static String getToken(){
        return token;
    }
    public static void setToken(String token) {
        MainActivity.token = token;
    }

    public static String getStatus(){
        return status;
    }
    public static void setStatus(String status) {
        MainActivity.status = status;
    }

}