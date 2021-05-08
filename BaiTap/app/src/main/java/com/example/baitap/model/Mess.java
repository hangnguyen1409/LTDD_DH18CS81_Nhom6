package com.example.baitap.model;

public class Mess {
    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "Mess{" +
                "mess='" + mess + '\'' +
                '}';
    }

    private String mess;
}
