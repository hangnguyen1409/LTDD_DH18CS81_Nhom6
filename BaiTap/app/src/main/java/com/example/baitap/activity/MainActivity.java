package com.example.baitap.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;
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

public class MainActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    private TextView tvShopName,filterTV;
    private ImageButton editProfileBtn,addProductBtn,filterProductBtn,resetbtn;
    private RelativeLayout RLProducts,RLOrders;
    RecyclerView productShowRV;
    EditText btn_search;

    public static Boolean isAuthenticated = false;

    public static ModelUser Login = new ModelUser();

    AdapterProductSeller adapterProductSeller;
    public static ArrayList<ModelProducts> cart;
    public static List<ModelProducts> listProduct;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listProduct = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Reference
        init();

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

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
        //add Product
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        filterProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AllCategories.class);
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
                        MainActivity.this.adapterProductSeller.getFilter().filter(s);

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
        tvShopName = findViewById(R.id.tvShopName);
        addProductBtn = findViewById(R.id.addProductBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        RLProducts = findViewById(R.id.RLProducts);
        RLOrders = findViewById(R.id.RLOrders);
        viewFlipper= findViewById(R.id.view_flipper);
        productShowRV = findViewById(R.id.productShowRV);
        filterTV = findViewById(R.id.filterTV);
        filterProductBtn = findViewById(R.id.filterProductBtn);
        resetbtn = findViewById(R.id.Resetbtn);
        btn_search = findViewById(R.id.btnSearch);;
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

}