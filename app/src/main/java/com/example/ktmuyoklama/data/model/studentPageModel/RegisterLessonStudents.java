package com.example.ktmuyoklama.data.model.studentPageModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterLessonStudents {

    @SerializedName("list")
    @Expose
    private List<Integer> list = null;


    public RegisterLessonStudents(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

}