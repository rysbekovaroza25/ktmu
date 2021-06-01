package com.example.ktmuyoklama.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.RegisterUser;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {
    EditText reg_username, reg_password, reg_re_password;
    Button reg_register;
    TextView reg_login;
    Spinner statusReg_spin;
    String statusReg;

    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        apiService = ApiRequest.getAPIService();

        reg_username = findViewById(R.id.reg_username);
        reg_password = findViewById(R.id.reg_password);
        reg_re_password = findViewById(R.id.reg_re_password);
        reg_register = findViewById(R.id.reg_button);
        reg_login = findViewById(R.id.reg_login_text);
        statusReg_spin = findViewById(R.id.reg_status);

        initSpinner();

        reg_register.setOnClickListener(v -> {
            String regUsername = reg_username.getText().toString().trim();
            String regPassword = reg_password.getText().toString().trim();
            String regRePassword = reg_re_password.getText().toString().trim();
            if (!TextUtils.isEmpty(regUsername) && !TextUtils.isEmpty(regPassword) && !TextUtils.isEmpty(regRePassword))
            register(regUsername, regPassword, statusReg);
        });

        reg_login.setOnClickListener(v -> {
            Intent login_intent = new Intent(RegisterPage.this, MainActivity.class);
            startActivity(login_intent);
        });

    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> faculty_adapter = ArrayAdapter.createFromResource(this, R.array.statusRegister,
                android.R.layout.simple_spinner_item);
        faculty_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusReg_spin.setAdapter(faculty_adapter);
        statusReg_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusReg = statusReg_spin.getSelectedItem().toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void register(String regUsername, String regPassword, String statusReg){

        RegisterUser registerUser = new RegisterUser(regUsername, regPassword, statusReg);

        Call<RegisterUser> allCategoriesListCall = apiService.registerUser(registerUser);

        allCategoriesListCall.enqueue(new Callback<RegisterUser>() {
            @Override
            public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                if (response.code() == 200) {
                    System.out.println("response body " + response.body().getUsername());
                    Intent register_intent = new Intent(RegisterPage.this, MainActivity.class);
                    startActivity(register_intent);
                }
                else{
                    System.out.println(response.headers());
                    Log.d("LOG", "Ответ сервера: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RegisterUser> call, Throwable throwable) {
                Log.d("LOG1", throwable.getMessage());
            }
        });
    }

}