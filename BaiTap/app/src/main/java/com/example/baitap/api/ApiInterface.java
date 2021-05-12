package com.example.baitap.api;

import com.example.baitap.model.LoginResponse;
import com.example.baitap.model.Mess;
import com.example.baitap.model.ModelCate;
import com.example.baitap.model.ModelLogin;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.ModelReceipt;
import com.example.baitap.model.Promotion;
import com.example.baitap.model.SignupResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET ("api/get_product_by_category_id/category={id}")
    Call<List<ModelProducts>> getAllProductById(@Path("id") int id_cate);

    @POST("saleapp/create_bills")
    Call<Mess> creatBill(@Body ModelReceipt receipt);

    @GET ("api/get_cate_by_id/{id}")
    Call<ModelCate> getAllCateById(@Path("id") int id_cate);

    @POST("api/signup_user")
    Call<Mess> signupUser(@Body SignupResponse response);

    @POST("api/login")
    Call<LoginResponse> userLogin(@Body ModelLogin login);

}
