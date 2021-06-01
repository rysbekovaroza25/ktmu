package com.example.ktmuyoklama.viewModel.studentViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.adapter.studentAdapter.SelectLessonAdapter;
import com.example.ktmuyoklama.data.model.studentPageModel.RegisterLessonStudents;
import com.example.ktmuyoklama.data.response.SelectLessonStudent;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectLessonViewModel extends ViewModel {
    public SelectLessonViewModel(){
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<SelectLessonStudent>> getLiveDataLessonRegister() {
        MutableLiveData<List<SelectLessonStudent>> liveData = new MutableLiveData<>();
        Call<List<SelectLessonStudent>> getDataCall = RetrofitService.getInstance().getApiService().selectLessonStudent(MainActivity.getToken());
        getDataCall.enqueue(new Callback<List<SelectLessonStudent>>(){
            @Override
            public void onResponse(Call<List<SelectLessonStudent>> call, Response<List<SelectLessonStudent>> response) {
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
            public void onFailure(Call<List<SelectLessonStudent>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }


    public LiveData<RegisterLessonStudents> getLiveDataSelectLesson(RegisterLessonStudents registerLessonStudents) {
        MutableLiveData<RegisterLessonStudents> liveData = new MutableLiveData<>();
        Call<RegisterLessonStudents> getDataCall = RetrofitService.getInstance().getApiService().registerLessonStudents(MainActivity.getToken(), registerLessonStudents);
        getDataCall.enqueue(new Callback<RegisterLessonStudents>(){
            @Override
            public void onResponse(Call<RegisterLessonStudents> call, Response<RegisterLessonStudents> response) {
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
            public void onFailure(Call<RegisterLessonStudents> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }

}