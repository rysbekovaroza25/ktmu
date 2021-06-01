package com.example.ktmuyoklama.service;

import com.example.ktmuyoklama.utils.RetrofitService;

public class ApiRequest {

//    public static final String BASE_URL = "https://quiet-brushlands-36713.herokuapp.com/";
    public static final String BASE_URL = "https://8dbd2c68853c.ngrok.io/";

    public static ApiService getAPIService() {
        return RetrofitService.getApiClient(BASE_URL).create(ApiService.class);
    }

}
