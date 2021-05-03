package com.example.baitap.model;

public class ModelProducts {
    private Integer productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Integer productQuantityS;
    private Integer productQuantityM;
    private Integer productQuantityL;
    private Integer productQuantityXL;
    private String productImage;
    private String originalPrice;
    private String userID;
    public ModelProducts(Integer productId, String productName, String productDescription,
                         String productCategory, Integer productQuantityS, Integer productQuantityM,
                         Integer productQuantityL, Integer productQuantityXL, String productImage,
                         String originalPrice, String userID) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productQuantityS = productQuantityS;
        this.productQuantityM = productQuantityM;
        this.productQuantityL = productQuantityL;
        this.productQuantityXL = productQuantityXL;
        this.productImage = productImage;
        this.originalPrice = originalPrice;
        this.userID = userID;
    }

    public ModelProducts() {
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getProductQuantityS() {
        return productQuantityS;
    }

    public void setProductQuantityS(Integer productQuantityS) {
        this.productQuantityS = productQuantityS;
    }

    public Integer getProductQuantityM() {
        return productQuantityM;
    }

    public void setProductQuantityM(Integer productQuantityM) {
        this.productQuantityM = productQuantityM;
    }

    public Integer getProductQuantityL() {
        return productQuantityL;
    }

    public void setProductQuantityL(Integer productQuantityL) {
        this.productQuantityL = productQuantityL;
    }

    public Integer getProductQuantityXL() {
        return productQuantityXL;
    }

    public void setProductQuantityXL(Integer productQuantityXL) {
        this.productQuantityXL = productQuantityXL;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}