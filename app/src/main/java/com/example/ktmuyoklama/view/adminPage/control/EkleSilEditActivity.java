package com.example.ktmuyoklama.view.adminPage.control;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

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

public class EkleSilEditActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ekle_sil_edit);
        // Inflate the layout for this fragment

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

        ESStartDate.setInputType(InputType.TYPE_NULL);
        ESEndDate.setInputType(InputType.TYPE_NULL);


        ESStartDate.setOnClickListener(v -> {
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
                            ESStartDate.setText(year1 + "-" + m +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth < 10)
                        {
                            d = "0";
                            ESStartDate.setText(year1 + "-" +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 < 10 && dayOfMonth > 10)
                        {
                            m = "0";
                            ESStartDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth > 10)
                        {
                            ESStartDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, day);
            picker.show();
        });

        ESEndDate.setOnClickListener(v -> {
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
                            ESEndDate.setText(year1 + "-" + m +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth < 10)
                        {
                            d = "0";
                            ESEndDate.setText(year1 + "-" +(monthOfYear + 1) + "-" + d +dayOfMonth);
                        }
                        else if(monthOfYear + 1 < 10 && dayOfMonth > 10)
                        {
                            m = "0";
                            ESEndDate.setText(year1 + "-" + m + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                        else if(monthOfYear + 1 > 10 && dayOfMonth > 10)
                        {
                            ESEndDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, day);
            picker.show();
        });


        editDate.setOnClickListener(v -> {

            String ESSDate = ESStartDate.getText().toString().trim();
            String ESEDate = ESEndDate.getText().toString().trim();

            ESSDate = ESSDate + "T09:01:01Z";
            ESEDate = ESEDate + "T17:01:01Z";
            System.out.println(ESEDate);


            if(!TextUtils.isEmpty(ESSDate) && !TextUtils.isEmpty(ESEDate)){
                updateRegDate(ESSDate, ESEDate);
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void updateRegDate(String essDate, String eseDate) {
        Period periodReg = new Period(eseDate, "EKLE_SIL", essDate);

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
        reg.setVisibility(View.GONE);
        reg.setVisibility(View.INVISIBLE);
        lss.setVisibility(View.GONE);
        lss.setVisibility(View.INVISIBLE);
        regStartDate.setVisibility(View.GONE);
        regStartDate.setVisibility(View.INVISIBLE);
        regEndDate.setVisibility(View.GONE);
        regEndDate.setVisibility(View.INVISIBLE);
        LSSStartDate.setVisibility(View.GONE);
        LSSStartDate.setVisibility(View.INVISIBLE);
        LSSEndDate.setVisibility(View.GONE);
        LSSEndDate.setVisibility(View.INVISIBLE);
    }
}