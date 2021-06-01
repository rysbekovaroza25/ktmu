package com.example.ktmuyoklama.viewModel.teacherViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ktmuyoklama.adapter.teacherAdapter.ControlTeacherPanelAdapter;
import com.example.ktmuyoklama.adapter.teacherAdapter.MyLessonListAdapter;
import com.example.ktmuyoklama.data.model.teacherPageModel.GechtiGechmedi;
import com.example.ktmuyoklama.data.model.teacherPageModel.Yoklama;
import com.example.ktmuyoklama.data.response.MyLessonStudentsList;
import com.example.ktmuyoklama.utils.RetrofitService;
import com.example.ktmuyoklama.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GechtiGechmediViewModel extends ViewModel {

    public GechtiGechmediViewModel(){
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<MyLessonStudentsList>> getLiveDataLessonRegister() {
        MutableLiveData<List<MyLessonStudentsList>> liveData = new MutableLiveData<>();
        Call<List<MyLessonStudentsList>> getDataCall = RetrofitService.getInstance().getApiService().myLessonStudentList(MainActivity.getToken(), ControlTeacherPanelAdapter.getKod_id(), "IN_PROCESS");
        getDataCall.enqueue(new Callback<List<MyLessonStudentsList>>(){
            @Override
            public void onResponse(Call<List<MyLessonStudentsList>> call, Response<List<MyLessonStudentsList>> response) {
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
            public void onFailure(Call<List<MyLessonStudentsList>> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }


    public LiveData<GechtiGechmedi> getLiveDataGechtiGechmedi(List<GechtiGechmedi> gechtiGechmedi) {
        MutableLiveData<GechtiGechmedi> liveData = new MutableLiveData<>();
        Call<GechtiGechmedi> getDataCall = RetrofitService.getInstance().getApiService().gechtiGechmedi(MainActivity.getToken(), gechtiGechmedi);
        getDataCall.enqueue(new Callback<GechtiGechmedi>(){
            @Override
            public void onResponse(Call<GechtiGechmedi> call, Response<GechtiGechmedi> response) {
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
            public void onFailure(Call<GechtiGechmedi> call, Throwable t) {
                Log.d("TAG", "onFailure: Data " + t.getMessage());
            }
        });
        return liveData;
    }

}
