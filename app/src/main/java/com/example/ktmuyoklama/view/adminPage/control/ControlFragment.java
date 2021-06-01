package com.example.ktmuyoklama.view.adminPage.control;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.response.GetDateRegResponse;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;
import com.example.ktmuyoklama.view.MainActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ControlFragment extends Fragment implements ControlBottomSheetFragment.ItemClickListener {

    public TextView regStartDate;
    public TextView regEndDate;
    public TextView ESStartDate;
    public TextView ESEndDate;
    public TextView LSSStartDate;
    public TextView LSSEndDate;
    public Button editDate;
    private String regSD;
    private String regED;
    private String ESSD;
    private String ESED;
    private String LSSSD;
    private String LSSED;
    public ApiService apiService;

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control, container, false);

        apiService = ApiRequest.getAPIService();
        editDate = view.findViewById(R.id.control_edit);

        regStartDate = view.findViewById(R.id.regStartData);
        regEndDate = view.findViewById(R.id.regEndData);
        ESStartDate = view.findViewById(R.id.ESStartData);
        ESEndDate = view.findViewById(R.id.ESEndData);
        LSSStartDate = view.findViewById(R.id.LSSStartData);
        LSSEndDate = view.findViewById(R.id.LSSEndData);

        getDateRegister();
        getDateEkleSil();
        getDateLSS();

        editDate.setOnClickListener(v -> {
            ControlBottomSheetFragment addPhotoBottomDialogFragment =
                    ControlBottomSheetFragment.newInstance();
            addPhotoBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                    ControlBottomSheetFragment.TAG);
        });

        return view;
    }

    public void getDateRegister() {

        Call<GetDateRegResponse> getDateRegResponseCall = apiService.getDataReg(MainActivity.getToken(), "REGISTRATION");
        getDateRegResponseCall.enqueue(new Callback<GetDateRegResponse>() {
            @Override
            public void onResponse(Call<GetDateRegResponse> call, Response<GetDateRegResponse> response) {
                try {
                    if (response.code() == 200 || response.body() == null) {
                        GetDateRegResponse getDateRegResponse = response.body();

                        String start = getDateRegResponse.getStartDateTime();
                        String end = getDateRegResponse.getEndDateTime();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        LocalDateTime formatDateTimeS = LocalDateTime.parse(start);
                        LocalDateTime formatDateTimeE = LocalDateTime.parse(end);

                        regStartDate.setText(formatDateTimeS.format(formatter));
                        regEndDate.setText(formatDateTimeE.format(formatter));
                        setRegSD(getDateRegResponse.getStartDateTime());
                        setRegED(getDateRegResponse.getEndDateTime());

                        Log.d("TAG", "Response from the server: " + response.code());

                    }
                } catch (Exception e) {
                    Log.d("TAG", "error: " + e);
                }
            }

            @Override
            public void onFailure(Call<GetDateRegResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
    }

    private void getDateEkleSil() {

        Call<GetDateRegResponse> getDateRegResponseCall = apiService.getDataReg(MainActivity.getToken(), "EKLE_SIL");
        getDateRegResponseCall.enqueue(new Callback<GetDateRegResponse>() {
            @Override
            public void onResponse(Call<GetDateRegResponse> call, Response<GetDateRegResponse> response) {
                try {
                    if (response.code() == 200 || response.body() == null) {
                        GetDateRegResponse getDateRegResponse = response.body();

                        String start = getDateRegResponse.getStartDateTime();
                        String end = getDateRegResponse.getEndDateTime();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        LocalDateTime formatDateTimeS = LocalDateTime.parse(start);
                        LocalDateTime formatDateTimeE = LocalDateTime.parse(end);

                        ESStartDate.setText(formatDateTimeS.format(formatter));
                        ESEndDate.setText(formatDateTimeE.format(formatter));
                        setLSSSD(getDateRegResponse.getStartDateTime());
                        setLSSED(getDateRegResponse.getEndDateTime());

                        Log.d("TAG", "Response from the server: " + response.code());
                    }
                } catch (Exception e) {
                    Log.d("TAG", "error: " + e);
                }
            }

            @Override
            public void onFailure(Call<GetDateRegResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
    }

    private void getDateLSS() {

        Call<GetDateRegResponse> getDateRegResponseCall = apiService.getDataReg(MainActivity.getToken(), "LESSON_SUCCESS_SUBMISSION");
        getDateRegResponseCall.enqueue(new Callback<GetDateRegResponse>() {
            @Override
            public void onResponse(Call<GetDateRegResponse> call, Response<GetDateRegResponse> response) {
                try {
                    if (response.code() == 200 || response.body() == null) {
                        GetDateRegResponse getDateRegResponse = response.body();

                        String start = getDateRegResponse.getStartDateTime();
                        String end = getDateRegResponse.getEndDateTime();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        LocalDateTime formatDateTimeS = LocalDateTime.parse(start);
                        LocalDateTime formatDateTimeE = LocalDateTime.parse(end);

                        LSSStartDate.setText(formatDateTimeS.format(formatter));
                        LSSEndDate.setText(formatDateTimeE.format(formatter));
                        setESSD(getDateRegResponse.getStartDateTime());
                        setESED(getDateRegResponse.getEndDateTime());
                        Log.d("TAG", "Response from the server: " + response.code());
                    }
                } catch (Exception e) {
                    Log.d("TAG", "error: " + e);
                }
            }

            @Override
            public void onFailure(Call<GetDateRegResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(String item) {

    }

    public String getRegSD() {
        return regSD;
    }

    public void setRegSD(String regSD) {
        this.regSD = regSD;
    }

    public String getRegED() {
        return regED;
    }

    public void setRegED(String regED) {
        this.regED = regED;
    }

    public String getESSD() {
        return ESSD;
    }

    public void setESSD(String ESSD) {
        this.ESSD = ESSD;
    }

    public String getESED() {
        return ESED;
    }

    public void setESED(String ESED) {
        this.ESED = ESED;
    }

    public String getLSSSD() {
        return LSSSD;
    }

    public void setLSSSD(String LSSSD) {
        this.LSSSD = LSSSD;
    }

    public String getLSSED() {
        return LSSED;
    }

    public void setLSSED(String LSSED) {
        this.LSSED = LSSED;
    }
}