package com.example.ktmuyoklama.view.adminPage.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.Teacher;
import com.example.ktmuyoklama.viewModel.adminViewModel.TeacherRegViewModel;

public class Teacher_Register_Fragment extends Fragment {

    private EditText teacherNumber, teacherName, teacherSurname, teacherLogin, teacherPassword;
    private TeacherRegViewModel teacherRegViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_teacher__register_, container, false);

        teacherNumber = view.findViewById(R.id.teacher_number);
        teacherName = view.findViewById(R.id.teacher_name);
        teacherSurname = view.findViewById(R.id.teacher_surname);
        teacherLogin = view.findViewById(R.id.teacher_login);
        teacherPassword = view.findViewById(R.id.teacher_password);
        Button teacherSubmit = view.findViewById(R.id.teacher_submit);
        teacherRegViewModel = ViewModelProviders.of(this).get(TeacherRegViewModel.class);

        teacherSubmit.setOnClickListener(v -> {
            String kod_t = teacherNumber.getText().toString().trim();
            String login_t = teacherLogin.getText().toString().trim();
            String name_t = teacherName.getText().toString().trim();
            String password_t = teacherPassword.getText().toString().trim();
            String surname_t = teacherSurname.getText().toString().trim();

            if(!TextUtils.isEmpty(kod_t) && !TextUtils.isEmpty(name_t) && !TextUtils.isEmpty(surname_t) && !TextUtils.isEmpty(login_t) && !TextUtils.isEmpty(password_t)) {
                teacherRegister(kod_t, login_t, name_t, password_t, surname_t);
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(),"Бош талааларлы толтурунуз",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void teacherRegister(String kod_t, String name_t, String surname_t, String login_t, String password_t) {
        Teacher teacher = new Teacher(kod_t, name_t, surname_t, login_t, password_t);
        teacherRegViewModel.getLiveDataTeacherReg(teacher).observe(getViewLifecycleOwner(), teacher1 -> {});
    }

}