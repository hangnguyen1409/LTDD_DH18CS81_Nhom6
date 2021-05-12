package com.example.baitap.model;

public class LoginResponse {
    private String mess;
    public ModelUser modelUser;

    public LoginResponse(String mess, ModelUser modelUser) {
        this.mess = mess;
        this.modelUser = modelUser;
    }


    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public ModelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(ModelUser modelUser) {
        this.modelUser = modelUser;
    }
}
