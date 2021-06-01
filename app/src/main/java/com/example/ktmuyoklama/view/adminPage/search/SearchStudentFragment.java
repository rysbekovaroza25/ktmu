package com.example.ktmuyoklama.view.adminPage.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.adapter.adminAdapter.StudentListAdapter;
import com.example.ktmuyoklama.data.model.StudentInfo;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;
import com.example.ktmuyoklama.view.adminPage.home.HomePageAdmin;
import com.example.ktmuyoklama.viewModel.adminViewModel.StudentListViewModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchStudentFragment extends Fragment {
    private View view;
    private Spinner fac_spinner, dep_spinner;
    private String faculty, department;
    private ApiService apiService;
    private StudentListViewModel studentListViewModel;
    private StudentListAdapter adapter;
    private RecyclerView recyclerView;
    public ArrayList<StudentInfo> dataArrayList = new ArrayList<>();
    public ArrayList<String> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_student, container, false);
        ImageView backHome = view.findViewById(R.id.back);
        SearchView searchViewStudent = view.findViewById(R.id.search);
        fac_spinner = view.findViewById(R.id.fac_sp);
        dep_spinner = view.findViewById(R.id.dep_sp);
        apiService = ApiRequest.getAPIService();
        recyclerView = view.findViewById(R.id.listStudentRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        studentListViewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);
        initSpinner();

        backHome.setOnClickListener(v -> {
            Intent backIntent = new Intent(getActivity(), HomePageAdmin.class);
            startActivity(backIntent);
            getActivity().finish();
        });

        searchViewStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchResult(newText);
                return false;
            }
        });

        return view;
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> faculty_adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.faculty,
                android.R.layout.simple_spinner_item);
        faculty_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fac_spinner.setAdapter(faculty_adapter);
        fac_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                faculty = fac_spinner.getSelectedItem().toString();
                searchStudent(faculty, department);
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<CharSequence> dep_adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.department,
                android.R.layout.simple_spinner_item);
        dep_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dep_spinner.setAdapter(dep_adapter);
        dep_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = dep_spinner.getSelectedItem().toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

                searchStudent(faculty, department);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void searchStudent(String facultyString, String departmentString) {
        JsonObject lesson = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("faculty",facultyString);
            jsonObj_.put("department",departmentString);
            JsonParser jsonParser = new JsonParser();
            lesson = (JsonObject) jsonParser.parse(jsonObj_.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        studentListViewModel.getLiveDataStudentList(lesson).observe(getViewLifecycleOwner(), new Observer<List<StudentInfo>>() {
            @Override
            public void onChanged(List<StudentInfo> studentLists) {
                dataArrayList = (ArrayList<StudentInfo>) studentLists;
                adapter = new StudentListAdapter(dataArrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void searchResult(String query)
    {
        String q = query.toLowerCase();

        List<StudentInfo> studentLists = new ArrayList<>();

        for (StudentInfo studentInfo : dataArrayList)
        {
            if(studentInfo.toString().toLowerCase().contains(q))
            {
                studentLists.add(studentInfo);
            }
        }
        adapter = new StudentListAdapter(studentLists);
        recyclerView.setAdapter(adapter);
    }

}