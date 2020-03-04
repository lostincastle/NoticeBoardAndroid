package com.shishir.onlinenoticeboard.api;

import com.shishir.onlinenoticeboard.model.CommentModel;
import com.shishir.onlinenoticeboard.model.NoticeModel;
import com.shishir.onlinenoticeboard.model.UserModel;
import com.shishir.onlinenoticeboard.response.LoginRegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("/users/login/")
    Call<UserModel> Login(
            @Field("username") String username,
            @Field("password") String password);

    @POST("/users/login")
    Call<LoginRegisterResponse> checkUser(
            @Body UserModel model
    );

    @GET("/api/posts/")
    Call<List<NoticeModel>> getNotice(
                    @Header("auth-token")String token
            );

    @GET("/api/posts/mine")
    Call<List<NoticeModel>> getMyNotice(
            @Header("auth-token")String token
    );

//    @GET("/api/...")
//    Call<Response> token(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/register")
    Call<UserModel> Register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("image") String image,
            @Field("mobile_number") String mobile_number,
            @Field("temporary_address") String temporary_addrrss,
            @Field("permanent_address") String permanent_address);

    @FormUrlEncoded
    @POST("/api/posts/")
    Call<NoticeModel> postNotice(
            @Header("auth-token") String token,
            @Field("title") String username,
            @Field("description") String password);

    @GET("users/me/")
    Call<UserModel> getMyProfile(@Header("auth-token") String token);


    @FormUrlEncoded
    @POST("api/web/auth/comments/{id}/posts/")
    Call<CommentModel> postComments(@Header("auth-token") String token,@Field("comments") String comments,@Path("id") String postId);


}






