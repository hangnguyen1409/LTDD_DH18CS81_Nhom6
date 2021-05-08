package com.example.baitap.model;

public class ModelReciptDetail {
    private Integer product_id;
    private Integer quantity_S_size;
    private Integer quantity_M_size;
    private Integer quantity_L_size;
    private Integer quantity_XL_size;
    private Float price;

    public ModelReciptDetail(Integer id, Integer quantity_s_size, Integer quantity_m_size, Integer quantity_l_size, Integer quantity_xl_size, Float totalPriceAllSize) {
        this.product_id = id;
        this.quantity_S_size = quantity_s_size;
        this.quantity_M_size = quantity_m_size;
        this.quantity_L_size = quantity_l_size;
        this.quantity_XL_size = quantity_xl_size;
        this.price = totalPriceAllSize;
    }

    @Override
    public String toString() {
        return "ModelReciptDetail{" +
                "productId=" + product_id +
                ", quantitySizeS=" + quantity_S_size +
                ", quantitySizeM=" + quantity_M_size +
                ", quantitySizeL=" + quantity_L_size +
                ", quantitySizeXL=" + quantity_XL_size +
                ", price=" + price +
                '}';
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity_S_size() {
        return quantity_S_size;
    }

    public void setQuantity_S_size(Integer quantity_S_size) {
        this.quantity_S_size = quantity_S_size;
    }

    public Integer getQuantity_M_size() {
        return quantity_M_size;
    }

    public void setQuantity_M_size(Integer quantity_M_size) {
        this.quantity_M_size = quantity_M_size;
    }

    public Integer getQuantity_L_size() {
        return quantity_L_size;
    }

    public void setQuantity_L_size(Integer quantity_L_size) {
        this.quantity_L_size = quantity_L_size;
    }

    public Integer getQuantity_XL_size() {
        return quantity_XL_size;
    }

    public void setQuantity_XL_size(Integer quantity_XL_size) {
        this.quantity_XL_size = quantity_XL_size;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
