package com.example.ktmuyoklama.viewModel.adminViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.data.model.LessonRegister;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonRegisterViewModel extends ViewModel {
    public LessonRegisterViewModel(){
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<LessonRegister> getLiveDataLessonRegister(LessonRegister lesson) {
        MutableLiveData<LessonRegister> liveData = new MutableLiveData<>();
        Call<LessonRegister> getDataCall = RetrofitService.getInstance().getApiService().lessonRegister(MainActivity.getToken(), lesson);
        getDataCall.enqueue(new Callback<LessonRegister>() {
            @Override
            public void onResponse(Call<LessonRegister> call, Response<LessonRegister> response) {
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
            public void onFailure(Call<LessonRegister> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }

}
