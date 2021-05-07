package com.example.baitap.model;

public class ModelReciptDetail {
    private Integer id;
    private Integer productId;
    private Integer quantitySizeS;
    private Integer quantitySizeM;
    private Integer quantitySizeL;
    private Integer quantitySizeXL;
    private Float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantitySizeS() {
        return quantitySizeS;
    }

    public void setQuantitySizeS(Integer quantitySizeS) {
        this.quantitySizeS = quantitySizeS;
    }

    public Integer getQuantitySizeM() {
        return quantitySizeM;
    }

    public void setQuantitySizeM(Integer quantitySizeM) {
        this.quantitySizeM = quantitySizeM;
    }

    public Integer getQuantitySizeL() {
        return quantitySizeL;
    }

    public void setQuantitySizeL(Integer quantitySizeL) {
        this.quantitySizeL = quantitySizeL;
    }

    public Integer getQuantitySizeXL() {
        return quantitySizeXL;
    }

    public void setQuantitySizeXL(Integer quantitySizeXL) {
        this.quantitySizeXL = quantitySizeXL;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
