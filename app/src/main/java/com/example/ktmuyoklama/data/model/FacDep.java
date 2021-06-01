package com.example.ktmuyoklama.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacDep {

    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("faculty")
    @Expose
    private String faculty;

    public String getDepartment() {
        return department;
    }


    public FacDep(String department, String faculty) {
        this.department = department;
        this.faculty = faculty;
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

}