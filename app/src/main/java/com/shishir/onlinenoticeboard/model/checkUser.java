package com.shishir.onlinenoticeboard.model;

import android.content.Context;
import android.widget.Toast;

import com.shishir.onlinenoticeboard.api.RetrofitApi;
import com.shishir.onlinenoticeboard.api.RetrofitInterface;
import com.shishir.onlinenoticeboard.response.LoginRegisterResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class checkUser {
    private String username;
    private String password;
    private Context context;
    LoginRegisterResponse result;

    public checkUser() {
    }

    public checkUser(String username, String password, Context context) {
        this.username = username;
        this.password = password;
        this.context = context;
    }

    public checkUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRegisterResponse checkUser() {
        RetrofitInterface api = RetrofitApi.getInstance().create(RetrofitInterface.class);

        UserModel model = new UserModel(username, password);

        Call<LoginRegisterResponse> call = api.checkUser(model);


        try {
            Response<LoginRegisterResponse> loginResponse = call.execute();
            if (loginResponse.code()!=200) {
                return null;
            } else {
                Toast.makeText(context, "error token", Toast.LENGTH_SHORT).show();
//
                result = loginResponse.body();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
