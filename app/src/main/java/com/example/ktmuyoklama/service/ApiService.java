package com.example.ktmuyoklama.service;

import com.example.ktmuyoklama.data.model.CoachId;
import com.example.ktmuyoklama.data.model.LessonRegister;
import com.example.ktmuyoklama.data.model.Login;
import com.example.ktmuyoklama.data.model.MyLessonList;
import com.example.ktmuyoklama.data.model.Period;
import com.example.ktmuyoklama.data.model.RegisterUser;
import com.example.ktmuyoklama.data.model.Student;
import com.example.ktmuyoklama.data.model.StudentInfo;
import com.example.ktmuyoklama.data.model.StudentList;
import com.example.ktmuyoklama.data.model.Teacher;
import com.example.ktmuyoklama.data.model.TeacherList;
import com.example.ktmuyoklama.data.model.studentPageModel.RegisterLessonStudents;
import com.example.ktmuyoklama.data.model.studentPageModel.StudentPercentage;
import com.example.ktmuyoklama.data.model.teacherPageModel.GechtiGechmedi;
import com.example.ktmuyoklama.data.model.teacherPageModel.Yoklama;
import com.example.ktmuyoklama.data.response.GetDateRegResponse;
import com.example.ktmuyoklama.data.response.LoginResponse;
import com.example.ktmuyoklama.data.response.MyLessonStudentsList;
import com.example.ktmuyoklama.data.response.SelectLessonStudent;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @Headers({ "Content-Type: application/json; charset=utf-8",
            "x-ms-logging-context: com.microsoft.azure.management.servicebus.Namespaces regenerateKeys" })
    @POST("login")
    Call<LoginResponse> login(@Body Login login);

    @POST("user/save")
    Call<RegisterUser> registerUser(@Body RegisterUser registerUser);

    //admin
    @POST("coach/register")
    Call<Teacher> teacherRegister(@Header("Authorization") String token,
                                  @Body Teacher teacherRegister);

    @POST("lesson/register2")
    Call<LessonRegister> lessonRegister(@Header("Authorization") String token,
                                        @Body LessonRegister lessonRegister);

    @GET("coach/list")
    Call<List<TeacherList>> teacherList(@Header("Authorization") String token);

    @POST("lesson/register1")
    Call<List<StudentInfo>> studentSearch(@Header("Authorization") String token,
                                          @Body JsonObject lesson);

    @GET("lesson/list")
    Call<List<CoachId>> lessonList(@Header("Authorization") String token);

    @POST("student/register")
    Call<Student> studentRegister(@Header("Authorization") String token,
                                  @Body Student studentRegister);


    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @POST("student/register")
    Call<Student> studentRegister1(@Header("Authorization") String token,
                                       @Part("department") RequestBody  department,
                                       @Part("faculty") RequestBody faculty,
                                       @Part("kod") RequestBody  number,
                                       @Part("name") RequestBody  name,
                                       @Part("studentSemester") RequestBody semester_,
                                       @Part("surname") RequestBody surname,
                                       @Part MultipartBody.Part part
    );

    @GET("student/list")
    Call<List<StudentList>> studentList(@Header("Authorization") String token);

    @POST("period/createOrUpdate")
    Call<Period> periodCreate(@Header("Authorization") String token,
                        @Body Period periodCreate);

    @POST("period/getByPurpose/{purpose}")
    Call<GetDateRegResponse> getDataReg(@Header("Authorization") String token,
                                        @Path("purpose") String purpose);

    //user
    @GET("coach/lessons")
    Call<List<MyLessonList>> myLessonList(@Header("Authorization") String token);

    @GET("enroll/studentList/{lessonId}/{state}")
    Call<List<MyLessonStudentsList>> myLessonStudentList(@Header("Authorization") String token, @Path("lessonId") int lessonId, @Path("state") String state);

    @POST("coach/submitSuccessOfStudents")
    Call<GechtiGechmedi> gechtiGechmedi(@Header("Authorization") String token, @Body List<GechtiGechmedi> gechtiGechmedi);

    //student
    @GET("student/listLessons/enrollment")
    Call<List<SelectLessonStudent>> selectLessonStudent(@Header("Authorization") String token);

    @POST("student/submit/enrollment")
    Call<RegisterLessonStudents> registerLessonStudents(@Header("Authorization") String token, @Body RegisterLessonStudents registerLessonStudents);

    @GET("student/percentage")
    Call<List<StudentPercentage>> studentPercentage(@Header("Authorization") String token);

    //yoklama
    @POST("yoklama/submit")
    Call<Yoklama> yoklama(@Header("Authorization") String token, @Body Yoklama yoklama);
    
}
