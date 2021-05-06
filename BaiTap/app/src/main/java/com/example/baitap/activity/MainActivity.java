package com.example.baitap.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.baitap.R;
import com.example.baitap.adapter.AdapterProductSeller;
import com.example.baitap.model.Cart;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //declare whole variable
    ViewFlipper viewFlipperGirl,viewFlipperBoy;
    private TextView nameTV,tvShopName,tvTabProducts,tvTabOrders,filterTV;
    private ImageButton editProfileBtn,addProductBtn,filterProductBtn;
    private RelativeLayout RLProducts,RLOrders;
    private EditText SearchProductsEdtText;
    private RecyclerView productShowRV;
    private ArrayList<ModelProducts>productList;
    private AdapterProductSeller adapterProductSeller;
    public static ArrayList<Cart> cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Reference
        inint();
        //Adapter for ViewFlipperGirl,ViewFlipperBoy
        viewFlipperGirl.setFlipInterval(3000);
        viewFlipperGirl.setAutoStart(true);
        viewFlipperBoy.setFlipInterval(3000);
        viewFlipperBoy.setAutoStart(true);
        loadAllProducts();
        showProductsTab();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose Category");
            }
        });
    }

    private void inint() {
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
        SearchProductsEdtText = findViewById(R.id.SearchProductsEdtText);
        filterProductBtn = findViewById(R.id.filterProductBtn);
        filterTV = findViewById(R.id.filterTV);
        productShowRV = findViewById(R.id.productShowRV);
        if( cart!=null){

        }else {
            cart = new ArrayList<>();
        }
    }

    private void loadAllProducts() {
        productList = new ArrayList<>();

        /* Use database to load data
        DatabaseReference reference = FirebaseDatabase
                .getInstance
                .getReference("User");
        reference.child(firebaseAuth.getUid()).child("Product")
                .addValueEventListener(new ValueEventListener()){
                ....
        }
        */

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


    //Loading info
    private void loadMyInfo(){

    }
}