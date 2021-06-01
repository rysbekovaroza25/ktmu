package com.example.ktmuyoklama.viewModel.studentViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.data.model.studentPageModel.StudentPercentage;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscontinuityViewModel extends ViewModel {
    public DiscontinuityViewModel(){
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<StudentPercentage>> getLiveDataLessonRegister() {
        MutableLiveData<List<StudentPercentage>> liveData = new MutableLiveData<>();
        Call<List<StudentPercentage>> getDataCall = RetrofitService.getInstance().getApiService().studentPercentage(MainActivity.getToken());
        getDataCall.enqueue(new Callback<List<StudentPercentage>>(){
            @Override
            public void onResponse(Call<List<StudentPercentage>> call, Response<List<StudentPercentage>> response) {
                try {
                    if (response.isSuccessful()) {
                        liveData.setValue(response.body());
                    } else {
                        Log.d("TAG", "Response from the server: " + response.code());
                    }
                } catch (Exception e) {
                    Log.d("TAG", "error: " + e);
                }
            }

            @Override
            public void onFailure(Call<List<StudentPercentage>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }
}
