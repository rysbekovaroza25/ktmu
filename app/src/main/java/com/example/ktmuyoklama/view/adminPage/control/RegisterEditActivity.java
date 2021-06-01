package com.example.ktmuyoklama.view.adminPage.control;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.Period;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;
import com.example.ktmuyoklama.view.MainActivity;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;
import com.example.ktmuyoklama.view.adminPage.register.Student_Register_Fragment;
import com.example.ktmuyoklama.view.teacherPage.home.GechtiGechmediFragment;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterEditActivity extends AppCompatActivity {

    public TextView reg, es, lss;
    public EditText regStartDate;
    public EditText regEndDate;
    public EditText ESStartDate;
    public EditText ESEndDate;
    public EditText LSSStartDate;
    public EditText LSSEndDate;
    public Button saveDate;
    private ApiService apiService;

    private ImageView backHome;

    DatePickerDialog picker;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register_edit);

        apiService = ApiRequest.getAPIService();

        saveDate = findViewById(R.id.control_save);
        reg = findViewById(R.id.reg);
        es = findViewById(R.id.ekle_sil);
        lss = findViewById(R.id.LSS);
        regStartDate = findViewById(R.id.regStartData);
        regEndDate = findViewById(R.id.regEndData);
        ESStartDate = findViewById(R.id.ESStartData);
        ESEndDate = findViewById(R.id.ESEndData);
        LSSStartDate = findViewById(R.id.LSSStartData);
        LSSEndDate = findViewById(R.id.LSSEndData);
        backHome = findViewById(R.id.back);

        backHome.setOnClickListener(v -> {
            Intent back = new Intent(this, HomePageAdmin.class);
            startActivity(back);
            finish();
        });

        check();

        regStartDate.setInputType(InputType.TYPE_NULL);
        regEndDate.setInputType(InputType.TYPE_NULL);


        regStartDate.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            cldr.set(year, month, day);

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // date picker dialog

            picker = new DatePickerDialog(this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        String m;
                        String d;

                        if (monthOfYear + 1 < 10 && dayOfMonth < 10) {
                            m = "0";
                            d = "0";
                            regStartDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + d + dayOfMonth);
                        } else if (monthOfYear + 1 > 10 && dayOfMonth < 10) {
                            d = "0";
                            regStartDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + d + dayOfMonth);
                        } else if (monthOfYear + 1 < 10 && dayOfMonth > 10) {
                            m = "0";
                            regStartDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else if (monthOfYear + 1 > 10 && dayOfMonth > 10) {
                            regStartDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, day);
            picker.show();
        });

        regEndDate.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            cldr.set(year, month, day);

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // date picker dialog

            picker = new DatePickerDialog(this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        String m;
                        String d;

                        if (monthOfYear + 1 < 10 && dayOfMonth < 10) {
                            m = "0";
                            d = "0";
                            regEndDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + d + dayOfMonth);
                        } else if (monthOfYear + 1 > 10 && dayOfMonth < 10) {
                            d = "0";
                            regEndDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + d + dayOfMonth);
                        } else if (monthOfYear + 1 < 10 && dayOfMonth > 10) {
                            m = "0";
                            regEndDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else if (monthOfYear + 1 > 10 && dayOfMonth > 10) {
                            regEndDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, day);
            picker.show();
        });


        saveDate.setOnClickListener(v -> {

            String regSDate = regStartDate.getText().toString().trim();
            String regEDate = regEndDate.getText().toString().trim();

            regSDate = regSDate + "T09:01:01Z";
            regEDate = regEDate + "T17:01:01Z";
            System.out.println(regSDate);


            if (!TextUtils.isEmpty(regSDate) && !TextUtils.isEmpty(regEDate)) {

                updateRegDate(regSDate, regEDate);
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show();

                String now = "2021-05-13T09:01:01Z";
                ZonedDateTime zdt = ZonedDateTime.parse(now);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDateTime formatDateTime = zdt.toLocalDateTime();

                System.out.println("Before : " + now);

                System.out.println("After : " + formatDateTime);

                System.out.println("After : " + formatDateTime.format(formatter));

            }

        });
    }

    private void updateRegDate(String regSDate1, String regEDate1) {
        Period periodReg = new Period(regEDate1, "REGISTRATION", regSDate1);

        Call<Period> periodCall = apiService.periodCreate(MainActivity.getToken(), periodReg);

        periodCall.enqueue(new Callback<Period>() {
            @Override
            public void onResponse(Call<Period> call, Response<Period> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Failed");
                } else {
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Period> call, Throwable t) {
                System.out.println("on Failure");
            }
        });

    }

    public void check() {
        es.setVisibility(View.GONE);
        es.setVisibility(View.INVISIBLE);
        lss.setVisibility(View.GONE);
        lss.setVisibility(View.INVISIBLE);
        ESStartDate.setVisibility(View.GONE);
        ESStartDate.setVisibility(View.INVISIBLE);
        ESEndDate.setVisibility(View.GONE);
        ESEndDate.setVisibility(View.INVISIBLE);
        LSSStartDate.setVisibility(View.GONE);
        LSSStartDate.setVisibility(View.INVISIBLE);
        LSSEndDate.setVisibility(View.GONE);
        LSSEndDate.setVisibility(View.INVISIBLE);
    }
}