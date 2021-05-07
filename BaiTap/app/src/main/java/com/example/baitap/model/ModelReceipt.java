package com.example.baitap.model;


public class ModelReceipt {
    private Integer id;
    private Integer customerId;

    public ModelReceipt(int id, int customerId){
        this.id = id;
        this.customerId = customerId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
