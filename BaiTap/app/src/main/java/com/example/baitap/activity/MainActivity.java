package com.example.baitap.activity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.example.baitap.R;
import com.example.baitap.adapter.AdapterProductSeller;
import com.example.baitap.model.Cart;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //declare whole variable
    ViewFlipper viewFlipperGirl,viewFlipperBoy;
    private TextView nameTV,tvShopName,tvTabProducts,tvTabOrders,filterTV;
    private ImageButton editProfileBtn,addProductBtn,filterProductBtn;
    private RelativeLayout RLProducts,RLOrders;
    private EditText SearchProductsEt;
    RecyclerView productShowRV;

    public static ModelUser Login;

    AdapterProductSeller adapterProductSeller;
    public static ArrayList<Cart> cart;
    public static List<ModelProducts> listProduct;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listProduct = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Reference
        init();

        //Adapter for ViewFlipperGirl,ViewFlipperBoy
        viewFlipperGirl.setFlipInterval(3000);
        viewFlipperGirl.setAutoStart(true);
        viewFlipperBoy.setFlipInterval(3000);
        viewFlipperBoy.setAutoStart(true);
        // lấy ID cate
        Intent i = getIntent();
        int idCate = i.getIntExtra("Id_Cate",0);
        if(idCate==0){
            loadProducts();
        }
        else {
            apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
            Call<List<ModelProducts>> callProductById = apiInterface.getAllProductById(idCate);
            callProductById.enqueue(new Callback<List<ModelProducts>>() {
                @Override
                public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                    getAllProducts(response.body());
                }

                @Override
                public void onFailure(Call<List<ModelProducts>> call, Throwable t) {
                    Log.i("mess","err");
                }
            });
        }
        //add Product
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        //Tab Products,Tab Orders
        tvTabProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load Products
                showProductsTab();
            }
        });
        tvTabOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showOrdersTab();
                    //Load Orders
            }
        });

        filterProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AllCategories.class);
                startActivity(i);

            }
        });
    }

    private void loadFilter(String select) {
        //Still doing
        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<ModelProducts>> call = apiInterface.getAllProducts();

        call.enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                List<ModelProducts> productsList = response.body();
                getAllProducts(productsList);
            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {

            }
        });
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

    private void init() {
        nameTV = findViewById(R.id.nameTV);
        tvShopName = findViewById(R.id.tvShopName);
        addProductBtn = findViewById(R.id.addProductBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        tvTabProducts = findViewById(R.id.tvTabProducts);
        tvTabOrders = findViewById(R.id.tvTabOrders);
        RLProducts = findViewById(R.id.RLProducts);
        RLOrders = findViewById(R.id.RLOrders);
        viewFlipperGirl = findViewById(R.id.view_flipper_girl);
        viewFlipperBoy = findViewById(R.id.view_flipper_boy);
        productShowRV = findViewById(R.id.productShowRV);
        filterTV = findViewById(R.id.filterTV);
        filterProductBtn = findViewById(R.id.filterProductBtn);
        SearchProductsEt = findViewById(R.id.SearchProductsEt);
        if(cart!=null){

        }else {
            cart = new ArrayList<>();
        }
    }
    public void getAllProducts(List<ModelProducts> productsList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        productShowRV.setLayoutManager(layoutManager);
        adapterProductSeller = new AdapterProductSeller(this,productsList);
        productShowRV.setAdapter(adapterProductSeller);
        adapterProductSeller.notifyDataSetChanged();

    }

    private void showProductsTab() {
        RLProducts.setVisibility(View.VISIBLE);
        RLOrders.setVisibility(View.GONE);

        tvTabProducts.setBackgroundResource(R.drawable.shape_tab_product_order_fill);
        tvTabOrders.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
    private void showOrdersTab() {
        RLOrders.setVisibility(View.VISIBLE);
        RLProducts.setVisibility(View.GONE);
        tvTabOrders.setBackgroundResource(R.drawable.shape_tab_product_order_fill);
        tvTabProducts.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
}