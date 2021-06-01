package com.example.ktmuyoklama.viewModel.adminViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.data.model.CoachId;
import com.example.ktmuyoklama.data.model.TeacherList;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonViewModel extends ViewModel {
    public LessonViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<CoachId>> getLiveDataLessonList() {
        MutableLiveData<List<CoachId>> liveData = new MutableLiveData<>();
        Call<List<CoachId>> getDataCall = RetrofitService.getInstance().getApiService().lessonList(MainActivity.getToken());
        getDataCall.enqueue(new Callback<List<CoachId>>() {
            @Override
            public void onResponse(Call<List<CoachId>> call, Response<List<CoachId>> response) {
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
            public void onFailure(Call<List<CoachId>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }
}
