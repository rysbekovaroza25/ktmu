package com.example.ktmuyoklama.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

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
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("studentSemester")
    @Expose
    private Integer studentSemester;
    @SerializedName("surname")
    @Expose
    private String surname;
//    @SerializedName("file")
//    @Expose
//    private String file;
//

    public Student(String department, String faculty, String kod, String name, Integer studentSemester, String surname) {
        this.department = department;
        this.faculty = faculty;
        this.kod = kod;
        this.name = name;
        this.studentSemester = studentSemester;
        this.surname = surname;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentSemester() {
        return studentSemester;
    }

    public void setStudentSemester(Integer studentSemester) {
        this.studentSemester = studentSemester;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
//
//    public String getFile() {
//        return file;
//    }
//
//    public void setFile(String file) {
//        this.file = file;
//    }
}