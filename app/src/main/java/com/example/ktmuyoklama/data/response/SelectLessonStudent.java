package com.example.ktmuyoklama.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectLessonStudent {

    @SerializedName("enrollId")
    @Expose
    private Integer enrollId;
    @SerializedName("enrollState")
    @Expose
    private String enrollState;
    @SerializedName("lessonId")
    @Expose
    private Integer lessonId;
    @SerializedName("lessonKod")
    @Expose
    private String lessonKod;
    @SerializedName("lessonSemester")
    @Expose
    private Integer lessonSemester;

    public Integer getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Integer enrollId) {
        this.enrollId = enrollId;
    }

    public String getEnrollState() {
        return enrollState;
    }

    public void setEnrollState(String enrollState) {
        this.enrollState = enrollState;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonKod() {
        return lessonKod;
    }

    public void setLessonKod(String lessonKod) {
        this.lessonKod = lessonKod;
    }

    public Integer getLessonSemester() {
        return lessonSemester;
    }

    public void setLessonSemester(Integer lessonSemester) {
        this.lessonSemester = lessonSemester;
    }

}
