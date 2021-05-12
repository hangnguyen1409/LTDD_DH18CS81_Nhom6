package com.example.baitap.model;

public class LoginResponse {
    private String mess;
        public ModelUser user_login;

    public LoginResponse(String mess, ModelUser modelUser) {
        this.mess = mess;
        this.user_login = modelUser;
    }


    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public ModelUser getModelUser() {
        return user_login;
    }

    public void setModelUser(ModelUser modelUser) {
        this.user_login = modelUser;
    }
}
