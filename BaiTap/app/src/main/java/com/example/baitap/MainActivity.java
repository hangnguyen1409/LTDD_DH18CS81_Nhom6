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
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declare whole variable

    ViewFlipper viewFlipperGirl,viewFlipperBoy;
    private TextView nameTV,tvShopName,tvTabProducts,tvTabOrders;
    private ImageButton editProfileBtn,addProductBtn;
    private LinearLayout LLProducts,LLOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference
        nameTV = findViewById(R.id.nameTV);
        tvShopName = findViewById(R.id.tvShopName);
        addProductBtn = findViewById(R.id.addProductBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        tvTabProducts = findViewById(R.id.tvTabProducts);
        tvTabOrders = findViewById(R.id.tvTabOrders);
        LLProducts = findViewById(R.id.LLProducts);
        LLOrders = findViewById(R.id.LLOrders);
        viewFlipperGirl = findViewById(R.id.view_flipper_girl);
        viewFlipperBoy = findViewById(R.id.view_flipper_boy);

        //Adapter for ViewFlipperGirl,ViewFlipperBoy
        viewFlipperGirl.setFlipInterval(3000);
        viewFlipperGirl.setAutoStart(true);
        viewFlipperBoy.setFlipInterval(3000);
        viewFlipperBoy.setAutoStart(true);

        showProducts();
        //ProgressDialog for Login

        //add Product
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddProductActivity.class));
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


    //Loading info
    private void loadMyInfo(){

    }
}