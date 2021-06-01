package com.example.ktmuyoklama.view.adminPage.control;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.Period;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;
import com.example.ktmuyoklama.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LSSEditActivity extends AppCompatActivity {

    public TextView reg, es, lss;
    public EditText regStartDate;
    public EditText regEndDate;
    public EditText ESStartDate;
    public EditText ESEndDate;
    public EditText LSSStartDate;
    public EditText LSSEndDate;
    public Button editDate;

    private ApiService apiService;

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_l_s_s_edit);

        apiService = ApiRequest.getAPIService();
        editDate = findViewById(R.id.control_edit);
        reg = findViewById(R.id.reg);
        es = findViewById(R.id.ekle_sil);
        lss = findViewById(R.id.LSS);
        regStartDate = findViewById(R.id.regStartData);
        regEndDate = findViewById(R.id.regEndData);
        ESStartDate = findViewById(R.id.ESStartData);
        ESEndDate = findViewById(R.id.ESEndData);
        LSSStartDate = findViewById(R.id.LSSStartData);
        LSSEndDate = findViewById(R.id.LSSEndData);

        check();
        LSSStartDate.setInputType(InputType.TYPE_NULL);
        LSSEndDate.setInputType(InputType.TYPE_NULL);


        LSSStartDate.setOnClickListener(v -> {
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

                        if(monthOfYear + 1 < 10 && dayOfMonth < 10)
                        {
                            m = "0";
                            d = "0";
                            LSSStartDate.setText(year1 + "-" + m +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth < 10)
                        {
                            d = "0";
                            LSSStartDate.setText(year1 + "-" +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 < 10 && dayOfMonth > 10)
                        {
                            m = "0";
                            LSSStartDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth > 10)
                        {
                            LSSStartDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, day);
            picker.show();
        });

        LSSEndDate.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            // date picker dialog

            picker = new DatePickerDialog(this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        String m;
                        String d;

                        if(monthOfYear + 1 < 10 && dayOfMonth < 10)
                        {
                            m = "0";
                            d = "0";
                            LSSEndDate.setText(year1 + "-" + m +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth < 10)
                        {
                            d = "0";
                            LSSEndDate.setText(year1 + "-" +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 < 10 && dayOfMonth > 10)
                        {
                            m = "0";
                            LSSEndDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth > 10)
                        {
                            LSSEndDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, day);
            picker.show();
        });


        editDate.setOnClickListener(v -> {

            String LSSDate = LSSStartDate.getText().toString().trim();
            String LSEDate = LSSEndDate.getText().toString().trim();

            LSSDate = LSSDate + "T09:01:01Z";
            LSEDate = LSEDate + "T17:01:01Z";
            System.out.println(LSEDate);


            if(!TextUtils.isEmpty(LSSDate) && !TextUtils.isEmpty(LSEDate)){
                updateRegDate(LSSDate, LSEDate);
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void updateRegDate(String lssDate, String lseDate) {
        Period periodReg = new Period(lseDate, "LESSON_SUCCESS_SUBMISSION", lssDate);

        Call<Period> periodCall = apiService.periodCreate(MainActivity.getToken(), periodReg);

        periodCall.enqueue(new Callback<Period>() {
            @Override
            public void onResponse(Call<Period> call, Response<Period> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Failed");
                }
                else {
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Period> call, Throwable t) {
                System.out.println("on Failure");
            }
        });

    }
    public void check(){
        es.setVisibility(View.GONE);
        es.setVisibility(View.INVISIBLE);
        reg.setVisibility(View.GONE);
        reg.setVisibility(View.INVISIBLE);
        ESStartDate.setVisibility(View.GONE);
        ESStartDate.setVisibility(View.INVISIBLE);
        ESEndDate.setVisibility(View.GONE);
        ESEndDate.setVisibility(View.INVISIBLE);
        regStartDate.setVisibility(View.GONE);
        regStartDate.setVisibility(View.INVISIBLE);
        regEndDate.setVisibility(View.GONE);
        regEndDate.setVisibility(View.INVISIBLE);
    }
}