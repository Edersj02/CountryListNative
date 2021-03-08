package com.example.countrylistnative.entities;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ErrorResponse {
    @SerializedName("message")
    String Message;

    public String getMessage() {
        return Message;
    }

    public static ErrorResponse fromErrorBody(ResponseBody errorBody) {
        try {
            return new Gson()
                    .fromJson(errorBody.string(), ErrorResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ErrorResponse();
    }
}
