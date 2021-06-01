package com.example.ktmuyoklama.viewModel.teacherViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.data.model.LessonRegister;
import com.example.ktmuyoklama.data.model.MyLessonList;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLessonListViewModel extends ViewModel {

    public MyLessonListViewModel(){
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<MyLessonList>> getLiveDataLessonRegister() {
        MutableLiveData<List<MyLessonList>> liveData = new MutableLiveData<>();
        Call<List<MyLessonList>> getDataCall = RetrofitService.getInstance().getApiService().myLessonList(MainActivity.getToken());
        getDataCall.enqueue(new Callback<List<MyLessonList>> (){
            @Override
            public void onResponse(Call<List<MyLessonList>> call, Response<List<MyLessonList>> response) {
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
            public void onFailure(Call<List<MyLessonList>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }

}
