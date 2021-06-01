package com.example.ktmuyoklama.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDateRegResponse {

    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

}
