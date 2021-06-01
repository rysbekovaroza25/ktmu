package com.example.ktmuyoklama.view.adminPage.register;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.Student;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;
import com.example.ktmuyoklama.view.MainActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Register_Fragment extends Fragment {

    ImageView student_image;
    EditText student_number, student_name, student_surname, student_semester;
    TextView choose_photo;
    Spinner fac_spinner, dep_spinner;
    Button student_submit;
    View view;
    ApiService apiService;
    String faculty, department;

    private static final int CAMERA_REQUEST = 1;
    final int REQUEST_GALLERY = 2;
    private Bitmap bitmap;
    String part_image = "com/example/ktmuyoklama/images/qrcode.png";
    File imagefile = new File(part_image);
    private Uri path;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_student__register_, container, false);

        apiService = ApiRequest.getAPIService();

        student_image = view.findViewById(R.id.student_photo);
        student_number = view.findViewById(R.id.student_number);
        student_name = view.findViewById(R.id.student_name);
        student_surname = view.findViewById(R.id.student_surname);
        student_semester = view.findViewById(R.id.student_semester);

        choose_photo = view.findViewById(R.id.choose_photo);
        fac_spinner = view.findViewById(R.id.fac_sp);
        dep_spinner = view.findViewById(R.id.dep_sp);
        student_submit = view.findViewById(R.id.student_submit);

        student_image.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        choose_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY);
            }
        });

        initSpinner();

        student_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File imagefile = new File(part_image);
                String number = student_number.getText().toString().trim();
                String name = student_name.getText().toString().trim();
                String surname = student_surname.getText().toString().trim();
                Integer semester_ = Integer.parseInt(student_semester.getText().toString());
                String sem = String.valueOf(semester_);

                if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(sem)) {
                    regStudent(department, faculty, number, name, semester_, surname);
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Бош талааларлы толтурунуз", Toast.LENGTH_SHORT).show();
                }
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY && data != null) {
            Uri dataimage = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(dataimage,imageprojection,null,null,null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                part_image = cursor.getString(indexImage);

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), dataimage);
                    student_image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(part_image != null)
                {
                    imagefile = new File(part_image);
                    student_image.setImageBitmap(BitmapFactory.decodeFile(imagefile.getAbsolutePath()));
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
        }

        }


    public void regStudent(String department, String faculty, String number, String name, Integer semester_, String surname) {

        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"),imagefile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imagefile.getName(),reqBody);

//        String filepath = "src/main/java/com/example/ktmuyoklama/images/qrcode.png";
//
//        File file = new File(filepath);
        RequestBody namePart = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody surnamePart = RequestBody.create(MediaType.parse("text/plain"), surname);
        RequestBody semesterPart = RequestBody.create(MediaType.parse("text/plain"), semester_.toString());
        RequestBody departmentPart = RequestBody.create(MediaType.parse("text/plain"), department);
        RequestBody facultyPart = RequestBody.create(MediaType.parse("text/plain"), faculty);
        RequestBody numberPart = RequestBody.create(MediaType.parse("text/plain"), number);

        Call<Student> call = apiService.studentRegister1(MainActivity.getToken(), departmentPart, facultyPart, numberPart, namePart, semesterPart, surnamePart, partImage);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(@NonNull Call<Student> call, @NonNull Response<Student> response) {

                if (response.isSuccessful()) {
                    System.out.println("Successful");
                } else
                    System.out.println("error");
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public Uri getPath() {
        return path;
    }

    public void setPath(Uri path) {
        this.path = path;
    }
}