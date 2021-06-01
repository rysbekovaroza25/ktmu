package com.example.ktmuyoklama.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ktmuyoklama.R;
import com.example.ktmuyoklama.data.model.Student;
import com.example.ktmuyoklama.service.ApiRequest;
import com.example.ktmuyoklama.service.ApiService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class image extends AppCompatActivity {

    ImageView student_image;
    EditText student_number, student_name, student_surname, student_semester;
    TextView choose_photo;
    Spinner fac_spinner, dep_spinner;
    Button student_submit;
    ImageView imgHolder;
    String part_image = "";
    ProgressDialog pd;
    final int REQUEST_GALLERY = 9544;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student__register_);

        ApiService apiService = ApiRequest.getAPIService();
        student_image = findViewById(R.id.student_photo);
        student_number = findViewById(R.id.student_number);
        student_name = findViewById(R.id.student_name);
        student_surname = findViewById(R.id.student_surname);
        student_semester = findViewById(R.id.student_semester);
        pd = new ProgressDialog(this);
        pd.setMessage("loading ... ");


        choose_photo = findViewById(R.id.choose_photo);
        fac_spinner = findViewById(R.id.fac_sp);
        dep_spinner = findViewById(R.id.dep_sp);
        student_submit = findViewById(R.id.student_submit);


        choose_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"open gallery"),REQUEST_GALLERY);
            }
        });

        student_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                File imagefile = new File(part_image);
                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"),imagefile);
                MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imagefile.getName(),reqBody);
                RequestBody namePart = RequestBody.create(MediaType.parse("text/plain"), "name");
                RequestBody surnamePart = RequestBody.create(MediaType.parse("text/plain"), "surname");
                RequestBody semesterPart = RequestBody.create(MediaType.parse("text/plain"), "1");
                RequestBody departmentPart = RequestBody.create(MediaType.parse("text/plain"), "department");
                RequestBody facultyPart = RequestBody.create(MediaType.parse("text/plain"), "faculty");
                RequestBody numberPart = RequestBody.create(MediaType.parse("text/plain"), "number");

                Call<Student> upload = apiService.studentRegister1(MainActivity.getToken(), departmentPart, facultyPart, numberPart, namePart, semesterPart, surnamePart, partImage);
                upload.enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        pd.dismiss();
                        Log.d("RETRO", "ON RESPONSE  : " + response.body().toString());

                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                        pd.dismiss();
                    }
                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if(requestCode == REQUEST_GALLERY)
            {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage,imageprojection,null,null,null);

                if (cursor != null)
                {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image = cursor.getString(indexImage);

                    if(part_image != null)
                    {
                        File image = new File(part_image);
                        student_image.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                    }
                }
            }
        }
    }
}
