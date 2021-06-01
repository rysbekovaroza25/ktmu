package com.example.ktmuyoklama.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lesson {

    @SerializedName("coachId")
    @Expose
    private CoachId coachId;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kod")
    @Expose
    private String kod;
    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("week")
    @Expose
    private Integer week;


    public Lesson(CoachId coachId, String department, String faculty, String kod, Integer semester, Boolean status, Integer week) {
        this.coachId = coachId;
        this.department = department;
        this.faculty = faculty;
        this.kod = kod;
        this.semester = semester;
        this.status = status;
        this.week = week;
    }

    public CoachId getCoachId() {
        return coachId;
    }

    public void setCoachId(CoachId coachId) {
        this.coachId = coachId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

}