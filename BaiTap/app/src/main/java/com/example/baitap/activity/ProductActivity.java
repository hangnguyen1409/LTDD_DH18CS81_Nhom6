package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.R;
import com.example.baitap.adapter.AdapterProductSeller;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.ModelUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {


    private ImageButton addProductBtn,filterProductBtn,resetbtn;
    RecyclerView productShowRV;
    EditText btn_search;
    DrawerLayout drawerLayout;

    AdapterProductSeller adapterProductSeller;
    public static List<ModelProducts> listProduct;
    ApiInterface apiInterface;
    public static Boolean isAuthenticated = false;
    public static ModelUser Login = new ModelUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productShowRV = findViewById(R.id.productShowRV);
        filterProductBtn = findViewById(R.id.filterProductBtn);
        resetbtn = findViewById(R.id.Resetbtn);
        btn_search = findViewById(R.id.btnSearch);
        drawerLayout = findViewById(R.id.drawer_layout);

        listProduct = new ArrayList<>();



        // lấy ID cate
        Intent i = getIntent();
        int idCate = i.getIntExtra("Id_Cate", 0);
        if (idCate == 0) {
            loadProducts();
        } else {
            apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
            Call<List<ModelProducts>> callProductById = apiInterface.getAllProductById(idCate);
            callProductById.enqueue(new Callback<List<ModelProducts>>() {
                @Override
                public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                    getAllProducts(response.body());
                }

                @Override
                public void onFailure(Call<List<ModelProducts>> call, Throwable t) {
                    Log.i("mess", "err");
                }
            });
        }
        filterProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductActivity.this, AllCategories.class);
                startActivity(i);

            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProducts();
            }
        });
        btn_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    try
                    {
                        ProductActivity.this.adapterProductSeller.getFilter().filter(s);

                    }catch (IndexOutOfBoundsException index){
                        index.printStackTrace();
                    }

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        MainActivity.redirectActivity(this,MainActivity.class);
    }
    public void ClickProduct(View view){
        recreate();
    }
    public void ClickUser(View view){
        MainActivity.redirectActivity(this,UserProfile.class);
    }
    public void ClickCart(View view){
        MainActivity.redirectActivity(this,ShowCartActivity.class);
    }
    public void ClickLogout(View view){
        MainActivity.redirectActivity(this,LoginActivity.class);
    }
    public void ClickExit(View view){
        MainActivity.logout(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }


    private void loadProducts(){
        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<ModelProducts>> call = apiInterface.getAllProducts();

        call.enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                listProduct = response.body();
                getAllProducts(listProduct);
            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {

            }
        });
    }
    public void getAllProducts(List<ModelProducts> productsList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        productShowRV.setLayoutManager(layoutManager);
        adapterProductSeller = new AdapterProductSeller(this,productsList);
        productShowRV.setAdapter(adapterProductSeller);
        adapterProductSeller.notifyDataSetChanged();

    }
}