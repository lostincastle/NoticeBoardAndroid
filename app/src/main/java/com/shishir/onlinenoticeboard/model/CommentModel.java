package com.shishir.onlinenoticeboard.model;

public class CommentModel {
    private Integer c_Id;
    private String comments ,postId;
    private boolean status;

    public CommentModel(Integer c_Id, String comments, String postId) {
        this.c_Id = c_Id;
        this.comments = comments;
        this.postId = postId;
    }

    public Integer getC_Id() {
        return c_Id;
    }

    public void setC_Id(Integer c_Id) {
        this.c_Id = c_Id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
