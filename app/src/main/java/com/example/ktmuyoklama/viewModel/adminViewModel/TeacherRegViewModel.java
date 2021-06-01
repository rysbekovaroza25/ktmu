package com.example.ktmuyoklama.viewModel.adminViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.data.model.Teacher;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherRegViewModel extends ViewModel {

    public TeacherRegViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<Teacher> getLiveDataTeacherReg(Teacher teacher) {
        MutableLiveData<Teacher> liveData = new MutableLiveData<>();
        Call<Teacher> getDataCall = RetrofitService.getInstance().getApiService().teacherRegister(MainActivity.getToken(), teacher);
        getDataCall.enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
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
            public void onFailure(Call<Teacher> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }
}
