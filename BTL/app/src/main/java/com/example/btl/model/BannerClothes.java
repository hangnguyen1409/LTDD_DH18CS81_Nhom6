package com.example.btl.model;

public class BannerClothes {

    Integer id;
    String clothName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClothName() {
        return clothName;
    }

    public void setClothName(String clothName) {
        this.clothName = clothName;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    Integer imageUrl;
    String cateName;

    public BannerClothes(Integer id, String clothName, Integer imageUrl, String cateName) {
        this.id = id;
        this.clothName = clothName;
        this.imageUrl = imageUrl;
        this.cateName = cateName;
    }


}
