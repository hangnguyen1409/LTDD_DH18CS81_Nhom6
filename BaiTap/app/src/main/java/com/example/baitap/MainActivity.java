package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baitap.adapter.DiscountClothesAdapter;
import com.example.baitap.model.DiscountClothes;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declare whole variable
    RecyclerView discountRecyclerView;
    DiscountClothesAdapter discountClothesAdapter;
    List<DiscountClothes> discountClothesList;
    public static ArrayList<ModelProducts> cart;
    private TextView nameTV,tvShopName,tvTabProducts,tvTabOrders;
    private ImageButton editProfileBtn,addProductBtn;
    private LinearLayout LLProducts,LLOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference
        inint();


        showProducts();
        //ProgressDialog for Login

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
                                                showProducts();
                                             }
                                         });
        tvTabOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showOrders();
                    //Load Orders
            }
        });

        //Adding data to model
        discountClothesList = new ArrayList<>();
        discountClothesList.add(new DiscountClothes(1,R.drawable.discountset));
        discountClothesList.add(new DiscountClothes(2,R.drawable.discountdress));
        discountClothesList.add(new DiscountClothes(3,R.drawable.discountjean));
        discountClothesList.add(new DiscountClothes(4,R.drawable.discountmaxi));
        discountClothesList.add(new DiscountClothes(5,R.drawable.discountsport));


        //Reference
        discountRecyclerView = findViewById(R.id.recycler_view_discount);
        setDiscountRecycler(discountClothesList);
    }

    private void inint() {
        nameTV = findViewById(R.id.nameTV);
        tvShopName = findViewById(R.id.tvShopName);
        addProductBtn = findViewById(R.id.addProductBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        tvTabProducts = findViewById(R.id.tvTabProducts);
        tvTabOrders = findViewById(R.id.tvTabOrders);
        LLProducts = findViewById(R.id.LLProducts);
        LLOrders = findViewById(R.id.LLOrders);

        if( cart!=null){

        }else {
            cart = new ArrayList<>();
        }
    }


    private void showProducts() {
        LLProducts.setVisibility(View.VISIBLE);
        LLOrders.setVisibility(View.GONE);

        tvTabProducts.setBackgroundResource(R.drawable.shape_tab_product_order_fill);
        tvTabOrders.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
    private void showOrders() {
        LLOrders.setVisibility(View.VISIBLE);
        LLProducts.setVisibility(View.GONE);

        tvTabOrders.setBackgroundResource(R.drawable.shape_tab_product_order_fill);
        tvTabProducts.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    private void setDiscountRecycler(List<DiscountClothes> dataList) {
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        discountRecyclerView.setLayoutManager(layoutManager);

        discountClothesAdapter = new DiscountClothesAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountClothesAdapter);
    }

    //Loading info
    private void loadMyInfo(){

    }
}