package com.example.baitap.model;


import java.util.ArrayList;

public class ModelReceipt {
    private String username;
    private String email;
    private ArrayList<ModelReciptDetail> listProduct;

    public ModelReceipt( String customerUsername, String email, ArrayList<ModelReciptDetail> listProduct){
        this.username = customerUsername;
        this.email = email;
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return "ModelReceipt{" +
                "username='" + username + '\'' +
                ", mail='" + email + '\'' +
                ", listProduct=" + listProduct +
                '}';
    }

    public ArrayList<ModelReciptDetail> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<ModelReciptDetail> listProduct) {
        this.listProduct = listProduct;
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
}
