package com.example.baitap.model;

public class Cart {
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private String productImage;
    private String originalPrice;
    private String size;

    public Cart(int id, int productQuantity){
        this.productId = id;
        this.productQuantity = productQuantity;
    }

    public Cart(int id, String name, int quantity, String size, String image, String price){
        this.productId = id;
        this.productName = name;
        this.productQuantity = quantity;
        this.productImage = image;
        this.originalPrice = price;
        this.size = size;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
