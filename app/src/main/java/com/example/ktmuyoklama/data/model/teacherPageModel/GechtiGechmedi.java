package com.example.ktmuyoklama.data.model.teacherPageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GechtiGechmedi {

    @SerializedName("enrollId")
    @Expose
    private Integer enrollId;
    @SerializedName("newEnrollState")
    @Expose
    private String newEnrollState;

    public GechtiGechmedi(Integer enrollId, String newEnrollState) {
        this.enrollId = enrollId;
        this.newEnrollState = newEnrollState;
    }

    public Integer getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Integer enrollId) {
        this.enrollId = enrollId;
    }

    public String getNewEnrollState() {
        return newEnrollState;
    }

    public void setNewEnrollState(String newEnrollState) {
        this.newEnrollState = newEnrollState;
    }

}