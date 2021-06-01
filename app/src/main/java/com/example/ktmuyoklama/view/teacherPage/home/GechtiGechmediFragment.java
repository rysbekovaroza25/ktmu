package com.example.ktmuyoklama.view.teacherPage.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.teacherAdapter.ControlTeacherPanelAdapter;
import com.example.ktmuyoklama.adapter.teacherAdapter.GechtiGechmediAdapter;
import com.example.ktmuyoklama.data.model.teacherPageModel.GechtiGechmedi;
import com.example.ktmuyoklama.data.response.MyLessonStudentsList;
import com.example.ktmuyoklama.viewModel.teacherViewModel.GechtiGechmediViewModel;

import java.util.ArrayList;
import java.util.List;

public class GechtiGechmediFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button submitResult;
    private TextView lessonKod;
    private GechtiGechmediAdapter gechtiGechmediAdapter;
    private ArrayList<MyLessonStudentsList> arrayList;
    private GechtiGechmediViewModel gechtiGechmediViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gechti_gechmedi, container, false);
        submitResult = view.findViewById(R.id.submit_result);
        lessonKod = view.findViewById(R.id.lessonKod);
        lessonKod.setText(ControlTeacherPanelAdapter.getLessonKod());
        recyclerView = view.findViewById(R.id.resultRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gechtiGechmediViewModel = ViewModelProviders.of(this).get(GechtiGechmediViewModel.class);
        getData();

        submitResult.setOnClickListener(v -> {

            List<Integer> copy = new ArrayList<>();
            copy.addAll(gechtiGechmediAdapter.getEnrollIdListSUCCEED());
            copy.addAll(gechtiGechmediAdapter.getEnrollIdListFAILED());

            System.out.println(copy);

            List<String> copy2 = new ArrayList<>();
            copy2.addAll(gechtiGechmediAdapter.getNewStateEnrollSUCCEED());
            copy2.addAll(gechtiGechmediAdapter.getNewStateEnrollFAILED());

            System.out.println(copy2);

            System.out.println("fragmentten succeed " + gechtiGechmediAdapter.getEnrollIdListSUCCEED());
            System.out.println("fragmentten new state succeed " + gechtiGechmediAdapter.getNewStateEnrollSUCCEED());
            System.out.println("fragmentten failed " + gechtiGechmediAdapter.getEnrollIdListFAILED());
            System.out.println("fragmentten new state failed " + gechtiGechmediAdapter.getNewStateEnrollFAILED());

            gechtiGechmedi(copy, copy2);

            Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();

        });

        return view;
    }

    private void gechtiGechmedi(List<Integer> c1, List<String> c2) {
        GechtiGechmedi gechtiGechmedi;
        List<GechtiGechmedi> gechtiGechmediList = new ArrayList<>();

        for (int i = 0; i<c1.size(); i++){
            gechtiGechmedi = new GechtiGechmedi(c1.get(i), c2.get(i));
            gechtiGechmediList.add(gechtiGechmedi);
        }

        gechtiGechmediViewModel.getLiveDataGechtiGechmedi(gechtiGechmediList).observe(getViewLifecycleOwner(), new Observer<GechtiGechmedi>() {
            @Override
            public void onChanged(GechtiGechmedi gechtiGechmedi) {

            }
        });
    }

    private void getData() {
        gechtiGechmediViewModel.getLiveDataLessonRegister().observe(getViewLifecycleOwner(), new Observer<List<MyLessonStudentsList>>() {
            @Override
            public void onChanged(List<MyLessonStudentsList> myLessonStudentsLists) {
                arrayList = (ArrayList<MyLessonStudentsList>) myLessonStudentsLists;
                gechtiGechmediAdapter = new GechtiGechmediAdapter(getContext(), arrayList);
                recyclerView.setAdapter(gechtiGechmediAdapter);
            }
        });

    }
}