package com.example.baitap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProducts {
    private Integer category;
    private String description;
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    private String name;
    private Float price;
    private Integer promotion_id;
    private Integer quantity_L_size;
    private Integer quantity_M_size;
    private Integer quantity_S_size;
    private Integer quantity_XL_size;

    public ModelProducts(Integer category, String description,
                         Integer id, String image, String name, Float price,
                         Integer promotion_id, Integer quantity_L_size,
                         Integer quantity_M_size, Integer quantity_S_size, Integer quantity_XL_size) {
        this.category = category;
        this.description = description;
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.promotion_id = promotion_id;
        this.quantity_L_size = quantity_L_size;
        this.quantity_M_size = quantity_M_size;
        this.quantity_S_size = quantity_S_size;
        this.quantity_XL_size = quantity_XL_size;
    }

    public Float totalPriceAllSize(){
        return (this.quantity_S_size + this.quantity_L_size + this.quantity_M_size + this.quantity_XL_size)*this.price;
    }

    public int totalQuantitySize(){
        return this.quantity_S_size + this.quantity_L_size + this.quantity_M_size + this.quantity_XL_size;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(Integer promotion_id) {
        this.promotion_id = promotion_id;
    }

    public Integer getQuantity_L_size() {
        return quantity_L_size;
    }

    public void setQuantity_L_size(Integer quantity_L_size) {
        this.quantity_L_size = quantity_L_size;
    }

    public Integer getQuantity_M_size() {
        return quantity_M_size;
    }

    public void setQuantity_M_size(Integer quantity_M_size) {
        this.quantity_M_size = quantity_M_size;
    }

    public Integer getQuantity_S_size() {
        return quantity_S_size;
    }

    public void setQuantity_S_size(Integer quantity_S_size) {
        this.quantity_S_size = quantity_S_size;
    }

    public Integer getQuantity_XL_size() {
        return quantity_XL_size;
    }

    public void setQuantity_XL_size(Integer quantity_XL_size) {
        this.quantity_XL_size = quantity_XL_size;
    }

    @Override
    public String toString() {
        return "ModelProducts{" +
                "category=" + category +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", promotion_id=" + promotion_id +
                ", quantity_L_size=" + quantity_L_size +
                ", quantity_M_size=" + quantity_M_size +
                ", quantity_S_size=" + quantity_S_size +
                ", quantity_XL_size=" + quantity_XL_size +
                '}';
    }
}