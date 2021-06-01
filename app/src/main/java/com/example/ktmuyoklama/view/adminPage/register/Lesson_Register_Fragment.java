package com.example.ktmuyoklama.view.adminPage.register;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.LessonRegister;
import com.example.ktmuyoklama.data.model.TeacherList;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.view.MainActivity;
import com.example.ktmuyoklama.viewModel.adminViewModel.LessonRegisterViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lesson_Register_Fragment extends Fragment {

    private Spinner fac_spinner, dep_spinner, status_spinner;
    private View view;
    private AutoCompleteTextView searchTeacher;
    public static int coach_id = 0;
    private String faculty, department, status;
    public ArrayAdapter<TeacherList> arrayAdapter;
    public LessonRegisterViewModel lessonRegisterViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lesson__register_, container, false);

        EditText lesson_kod = view.findViewById(R.id.lesson_kod);
        EditText weeks = view.findViewById(R.id.lesson_week);
        searchTeacher = view.findViewById(R.id.teacher_search);
        fac_spinner = view.findViewById(R.id.fac_sp);
        dep_spinner = view.findViewById(R.id.dep_sp);
        status_spinner = view.findViewById(R.id.status_sp);
        EditText semester = view.findViewById(R.id.semester);
        Button lesson_submit = view.findViewById(R.id.lesson_submit);
        lessonRegisterViewModel = ViewModelProviders.of(this).get(LessonRegisterViewModel.class);

        lesson_submit.setOnClickListener(v -> {
            String lessonKod_ = lesson_kod.getText().toString().trim();
            Integer week_ = Integer.parseInt(weeks.getText().toString());
            Integer semester_ = Integer.parseInt(semester.getText().toString());
            Boolean status_ = Boolean.parseBoolean(status);

            if (!TextUtils.isEmpty(lessonKod_) && !TextUtils.isEmpty(faculty) && !TextUtils.isEmpty(department) && !TextUtils.isEmpty(status)) {
                lessonRegister(lessonKod_, week_, faculty, department, status_, semester_);
                Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Бош талааларлы толтурунуз", Toast.LENGTH_SHORT).show();
            }
        });

        initSpinner();

        coach_list();

        searchTeacher.setOnItemClickListener((parent, view, position, id) -> {
            TeacherList coach = (TeacherList) parent.getItemAtPosition(position);
            System.out.println(coach);
            coach_id = coach.getCoachId();
        });

        return view;
    }

    private void lessonRegister(String lessonKod, Integer weekS, String faculty, String department, Boolean status, Integer semester) {
        LessonRegister lessonRegister = new LessonRegister(coach_id, department, faculty, lessonKod, semester, status, weekS);
        lessonRegisterViewModel.getLiveDataLessonRegister(lessonRegister).observe(getViewLifecycleOwner(), new Observer<LessonRegister>() {
            @Override
            public void onChanged(LessonRegister data) {
                System.out.println("");
            }
        });
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<CharSequence> status_adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.status,
                android.R.layout.simple_spinner_item);
        status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        status_spinner.setAdapter(status_adapter);
        status_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = status_spinner.getSelectedItem().toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void coach_list() {
        Call<List<TeacherList>> allCategoriesListCall = ApiRequest.getAPIService().teacherList(MainActivity.getToken());
        allCategoriesListCall.enqueue(new Callback<List<TeacherList>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TeacherList>> call, Response<List<TeacherList>> response) {
                try {
                    if (!response.isSuccessful()) {
                        Log.d("LOG", "Ответ сервера: " + response.code());
                        return;
                    } else {
                        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, response.body());
                        searchTeacher.setAdapter(arrayAdapter);
                    }
                }catch (Exception e){
                    Log.d("LOG", "Error: " + e);
                }
            }

            @Override
            public void onFailure(Call<List<TeacherList>> call, Throwable throwable) {
                Log.d("LOG", throwable.getMessage());
            }
        });
    }

}