package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.baitap.R;
import com.example.baitap.adapter.CartAdapter;
import com.example.baitap.model.Cart;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    CartAdapter cartAdapter;

    ListView listViewProduct;
    Button btnPay, btnReset;
    ImageButton btnBack;
    TextView tvEmpty;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mapping();
        tvEmpty.setVisibility(View.INVISIBLE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cart.clear();
                tvEmpty.setVisibility(View.VISIBLE);
                cartAdapter.notifyDataSetChanged();
            }
        });

        MainActivity.cart.add(new Cart(1, "Áo Khoác Classic Activewear M5 Màu Xám Trắng",5, "S"
                ,"https://product.hstatic.net/200000254587/product/anh_cuong_thi_ffff3c15ed8c4113ada243c96c9a829f_master.jpg",
                "250000"));

        MainActivity.cart.add(new Cart(2, "Áo J09 Màu Xám Trắng",7, "M"
                ,"https://cf.shopee.vn/file/b555dc466312242d15a59e022c40e2a3",
                "180000"));


        cartAdapter = new CartAdapter(  MainActivity.cart);


        listViewProduct.setAdapter(cartAdapter);

    }

    public void addItemsOnSpinner() {
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.sizeClothes,
                android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void mapping() {
        listViewProduct = findViewById(R.id.listproduct);
        btnBack = findViewById(R.id.backBtn);
        btnPay = findViewById(R.id.btnPay);
        btnReset = findViewById(R.id.btnReset);
        tvEmpty = findViewById(R.id.tv_Empty);
        spinner = findViewById(R.id.spinner);
    }
}