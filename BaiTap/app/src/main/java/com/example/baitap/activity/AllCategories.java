package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.baitap.R;
import com.example.baitap.adapter.AdapterProductSeller;
import com.example.baitap.adapter.AllCategoryAdapter;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.CategoryModel;
import com.example.baitap.model.ModelCate;
import com.example.baitap.model.ModelProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategories extends AppCompatActivity {

    RecyclerView all_category;
    AllCategoryAdapter allCategoryAdapter;
    List<ModelCate> categoryModelList;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        all_category = findViewById(R.id.all_category);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategories.this, ProductActivity.class));
            }
        });



        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<ModelCate>> CallAPIGetAllCat = apiInterface.getAllCate();
        CallAPIGetAllCat.enqueue(new Callback<List<ModelCate>>() {
            @Override
            public void onResponse(Call<List<ModelCate>> call, Response<List<ModelCate>> response) {
                categoryModelList = response.body();
                setCategoryRecycler(categoryModelList);

            }

            @Override
            public void onFailure(Call<List<ModelCate>> call, Throwable t) {

            }
        });
    }

    private void setCategoryRecycler(List<ModelCate> cateList) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            all_category.setLayoutManager(layoutManager);
            allCategoryAdapter = new AllCategoryAdapter(this, cateList);
            all_category.setAdapter(allCategoryAdapter);
            allCategoryAdapter.notifyDataSetChanged();
    }
}