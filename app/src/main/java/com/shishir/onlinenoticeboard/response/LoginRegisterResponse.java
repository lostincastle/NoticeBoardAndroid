package com.shishir.onlinenoticeboard.response;

import com.shishir.onlinenoticeboard.model.UserModel;

public class LoginRegisterResponse {

    private String message;
    private String token="";
    private UserModel result;

    public LoginRegisterResponse(String message, String token, UserModel result) {
        this.message = message;
        this.token = token;
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getResult() {
        return result;
    }

    public void setResult(UserModel result) {
        this.result = result;
    }
}
