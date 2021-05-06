package com.example.baitap.api;

import android.view.Display;

import com.example.baitap.model.ModelCate;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.ModelPromotion;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/get_all_product")
    Call<List<ModelProducts>> getAllProducts();

    @GET("api/get-all-category")
    Call<List<ModelCate>> getAllCate();


    @GET("api/get_promotion_by_id")
    Call<List<ModelPromotion>> getAllPromotions();


}
