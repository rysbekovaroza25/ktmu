package com.example.ktmuyoklama.viewModel.adminViewModel;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.ktmuyoklama.data.model.TeacherList;
import com.example.ktmuyoklama.data.response.LoginResponse;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherViewModel extends ViewModel {
    public TeacherViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<TeacherList>> getLiveDataTeacher() {
        MutableLiveData<List<TeacherList>> liveData = new MutableLiveData<>();
        Call<List<TeacherList>> getDataCall = RetrofitService.getInstance().getApiService().teacherList(MainActivity.getToken());
        getDataCall.enqueue(new Callback<List<TeacherList>>() {
            @Override
            public void onResponse(Call<List<TeacherList>> call, Response<List<TeacherList>> response) {
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
            public void onFailure(Call<List<TeacherList>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }
}
