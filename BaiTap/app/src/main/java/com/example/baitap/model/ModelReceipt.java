package com.example.baitap.model;


import java.util.ArrayList;
import java.util.List;

public class ModelReceipt {
    private String username;
    private String email;
    private List<ModelReciptDetail> listProduct;

    public ModelReceipt( String customerUsername, String email, List<ModelReciptDetail> listProduct){
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

    public List<ModelReciptDetail> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ModelReciptDetail> listProduct) {
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
