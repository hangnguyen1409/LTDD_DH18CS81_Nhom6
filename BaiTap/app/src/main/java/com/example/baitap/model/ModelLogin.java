package com.example.baitap.model;

public class ModelLogin {
    private String password;

    public String getPassword() {
        return password;
    }

    public ModelLogin(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
