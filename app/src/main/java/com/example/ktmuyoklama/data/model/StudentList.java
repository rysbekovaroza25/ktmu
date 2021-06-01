package com.example.ktmuyoklama.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentList {

    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image1")
    @Expose
    private String image1;
    @SerializedName("image2")
    @Expose
    private String image2;
    @SerializedName("image3")
    @Expose
    private String image3;
    @SerializedName("image4")
    @Expose
    private String image4;
    @SerializedName("image5")
    @Expose
    private String image5;
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
    @SerializedName("userId")
    @Expose
    private UserId userId;

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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
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

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

}