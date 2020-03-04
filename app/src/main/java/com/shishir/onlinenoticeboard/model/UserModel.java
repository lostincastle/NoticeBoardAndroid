package com.shishir.onlinenoticeboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("temporary_address")
    @Expose
    private String temporary_addreess;
    @SerializedName("permanent_address")
    @Expose
    private String permanentAddress;
    @SerializedName("token")
    @Expose
    private String token;

    public UserModel() {
    }

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }
//
//    public UserModel(String username,String email,String mobileNumber){
//        this.username = username;
//        this.email = email;
//        this.mobileNumber = mobileNumber;}

    public UserModel(String username, String email, String password, String image, String mobileNumber, String temporary_address, String permanentAddress) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.mobileNumber = mobileNumber;
        this.temporary_addreess = temporary_address;
        this.permanentAddress = permanentAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTemporaryAddreess() {
        return temporary_addreess;
    }

    public void setTemporaryAddreess(String temporary_addreess) {
        this.temporary_addreess = temporary_addreess;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getToken() {
        return token;
    }

//    public void setToken(String token) {
//        this.token = token;
//    }

}
