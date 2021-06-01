package com.example.ktmuyoklama.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyLessonStudentsList {

    @SerializedName("enrollId")
    @Expose
    private Integer enrollId;
    @SerializedName("enrollState")
    @Expose
    private String enrollState;
    @SerializedName("kod")
    @Expose
    private String kod;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("studentId")
    @Expose
    private Integer studentId;
    @SerializedName("surname")
    @Expose
    private String surname;

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

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}
