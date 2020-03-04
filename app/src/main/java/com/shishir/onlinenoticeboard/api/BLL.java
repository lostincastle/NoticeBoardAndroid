package com.shishir.onlinenoticeboard.api;

import com.shishir.onlinenoticeboard.StrictMode.StrictModeC;
import com.shishir.onlinenoticeboard.model.UserModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Response;


public class BLL {
    boolean isSuccess = false;

    public boolean LoginBLL(UserModel userModel) {

        RetrofitInterface api = RetrofitApi.getInstance().create(RetrofitInterface.class);
        Call<UserModel> call = api.Login(userModel.getUsername(),userModel.getPassword());

        try{
            StrictModeC.StrictMode();
            Response<UserModel> loginRespose = call.execute();
              RetrofitApi.token = loginRespose.body().getToken();
//              token = token + loginRespose.body().getToken()
            if(loginRespose.code()==200){
                return true;
            }else {
                return false;
            }
        }catch (Exception exception){
            return false;
        }
    }
    public  boolean RegisterBLL(UserModel user){
        RetrofitInterface api = RetrofitApi.getInstance().create(RetrofitInterface.class);
        Call<UserModel> Register = api.Register(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getImage(),
                user.getMobileNumber(),
                user.getTemporaryAddreess(),
                user.getPermanentAddress()
        );
        try{
            StrictModeC.StrictMode();
            Response<UserModel> registerRespose = Register.execute();
            if(registerRespose.code() == 200){
                return true;
            }else {
                return false;
            }
        }catch (Exception exception){
//           Log.d("data",exception.getMessage());
            return  false;
        }
    }



}
