package com.example.ktmuyoklama.viewModel.adminViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.data.model.StudentInfo;
import com.example.ktmuyoklama.data.model.StudentList;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListViewModel extends ViewModel {
    public StudentListViewModel(){
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<StudentInfo>> getLiveDataStudentList(JsonObject lesson){

        MutableLiveData<List<StudentInfo>> liveData = new MutableLiveData<>();
        Call<List<StudentInfo>> getDataCall = RetrofitService.getInstance().getApiService().studentSearch(MainActivity.getToken(), lesson);
        getDataCall.enqueue(new Callback<List<StudentInfo>>() {
            @Override
            public void onResponse(@NotNull Call<List<StudentInfo>> call, @NotNull Response<List<StudentInfo>> response) {
                try {
                    if (response.code() == 200) {
                        liveData.setValue(response.body());

                    } else {
                        Log.d("TAG", "Response from the server: " + response.code());
                    }
                } catch (Exception e) {
                    Log.d("TAG", "error: " + e);
                }
            }

            @Override
            public void onFailure(Call<List<StudentInfo>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });

        return liveData;
    }
}
