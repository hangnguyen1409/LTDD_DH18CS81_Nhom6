package com.example.baitap.api;

import com.example.baitap.model.ModelCate;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.Promotion;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/get_all_product")
    Call<List<ModelProducts>> getAllProducts();

    @GET("api/get-all-category")
    Call<List<ModelCate>> getAllCate();


    @GET("api/get_promotion_today")
    Call<List<Promotion>> getAllPromotions();

    @GET("api/get_promotion_by_id/{id}")
    Call<Promotion> getPromotioById(@Path("id") int id);
}
