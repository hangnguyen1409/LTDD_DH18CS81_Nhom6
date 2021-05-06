package com.example.baitap.model;



public class ModelPromotion {
    private String description;
    private Integer discount;
    private String name;
    private String start_date;
    private String end_date;
    private Integer id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelPromotion(String description, Integer discount, String name, String start_date, String end_date, Integer id) {
        this.description = description;
        this.discount = discount;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.id = id;
    }
}