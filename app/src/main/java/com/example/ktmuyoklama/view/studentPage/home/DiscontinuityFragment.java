package com.example.ktmuyoklama.view.studentPage.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.studentAdapter.DiscontinuityAdapter;
import com.example.ktmuyoklama.data.model.studentPageModel.StudentPercentage;
import com.example.ktmuyoklama.viewModel.studentViewModel.DiscontinuityViewModel;

import java.util.ArrayList;
import java.util.List;

public class DiscontinuityFragment extends Fragment {

    private RecyclerView recyclerView;

    private DiscontinuityViewModel discontinuityViewModel;
    private ArrayList<StudentPercentage> arrayList;
    private DiscontinuityAdapter discontinuityAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discontinuity, container, false);
        recyclerView = view.findViewById(R.id.devamsizlikRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        discontinuityViewModel = ViewModelProviders.of(this).get(DiscontinuityViewModel.class);
        getData();

        return view;
    }

    private void getData() {

        discontinuityViewModel.getLiveDataLessonRegister().observe(getViewLifecycleOwner(), new Observer<List<StudentPercentage>>() {
            @Override
            public void onChanged(List<StudentPercentage> studentPercentages) {
                arrayList = (ArrayList<StudentPercentage>) studentPercentages;
                discontinuityAdapter = new DiscontinuityAdapter(getContext(), arrayList);
                recyclerView.setAdapter(discontinuityAdapter);
            }
        });

    }

}