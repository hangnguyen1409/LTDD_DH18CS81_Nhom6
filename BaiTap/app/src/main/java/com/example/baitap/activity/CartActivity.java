package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.baitap.R;
import com.example.baitap.adapter.CartAdapter;
import com.example.baitap.model.ModelProducts;

public class CartActivity extends AppCompatActivity {
//    ArrayList<String> listProduct;
    CartAdapter cartAdapter;

    ListView listViewProduct;
    Button btnPay, btnReset;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mapping();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

        /*MainActivity.cart.add(new ModelProducts(
                1,"Áo Khoác Classic Activewear M5 Màu Xám Trắng", "áo khoác","4","4",
                "https://product.hstatic.net/200000254587/product/anh_cuong_thi_ffff3c15ed8c4113ada243c96c9a829f_master.jpg",
                "400000","5","6","1"));
        MainActivity.cart.add(new ModelProducts(2,"Áo J09 Màu Xám Trắng",
                "áo khoác","4","3",
                "https://cf.shopee.vn/file/b555dc466312242d15a59e022c40e2a3",
                "250000","5","6","1"));

         */

        cartAdapter = new CartAdapter( /*listProduct*/ MainActivity.cart);


        listViewProduct.setAdapter(cartAdapter);

    }

    public void addListProduct(ModelProducts product){

    }

    private void mapping() {
        listViewProduct = findViewById(R.id.listproduct);
        btnBack = findViewById(R.id.backBtn);
        btnPay = findViewById(R.id.btnPay);
        btnReset = findViewById(R.id.btnReset);
    }
}