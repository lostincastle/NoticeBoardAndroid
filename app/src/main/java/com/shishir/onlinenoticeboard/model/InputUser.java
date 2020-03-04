package com.shishir.onlinenoticeboard.model;

public class InputUser {
private String username;
private String email;
private String password;
private  String image;
private String mobile_no;
private String  per_address;
private String temp_address;

    public InputUser( String per_address, String temp_address) {
        this.per_address = per_address;
        this.temp_address = temp_address;
    }

    public InputUser(String username, String email, String password, String image,String mobile_no) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.mobile_no = mobile_no;
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

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPer_address() {
        return per_address;
    }

    public void setPer_address(String per_address) {
        this.per_address = per_address;
    }

    public String getTemp_address() {
        return temp_address;
    }

    public void setTemp_address(String temp_address) {
        this.temp_address = temp_address;
    }
}
