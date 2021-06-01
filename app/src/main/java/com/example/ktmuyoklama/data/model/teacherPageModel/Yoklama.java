package com.example.ktmuyoklama.data.model.teacherPageModel;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Yoklama {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("lessonId")
    @Expose
    private Integer lessonId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("studentId")
    @Expose
    private List<Integer> studentId = null;

    public Yoklama(String date, Integer lessonId, String name, List<Integer> studentId) {
        this.date = date;
        this.lessonId = lessonId;
        this.name = name;
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<Integer> studentId) {
        this.studentId = studentId;
    }

}