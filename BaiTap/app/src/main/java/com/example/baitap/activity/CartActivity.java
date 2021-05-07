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
import android.widget.Toast;

import com.example.baitap.R;
import com.example.baitap.adapter.CartAdapter;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.ModelReceipt;

import retrofit2.Call;

public class CartActivity extends AppCompatActivity {
    CartAdapter cartAdapter;

    ListView listViewProduct;
    Button btnPay, btnReset;
    ImageButton btnBack;
    TextView tvEmpty, totalPrice, totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mapping();
        hide();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noProductAlert();
                MainActivity.cart.clear();
                hide();
                cartAdapter.notifyDataSetChanged();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noProductAlert();
//                checkLogin();
//                updateDB();
//                if(creatReceipt())
                //if success -> clear cart, reset productList
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        MainActivity.cart.add(new ModelProducts(1,"áo khoác",1
                ,"https://cdn3.yame.vn/pimg/ao-thun-nam-y2010-basic-bf01-0019691/435b20ea-0323-1700-c5fe-001742e83abe.jpg?w=440",
                "Áo Khoác Classic Activewear M5 Màu Xám Trắng", (float) 200000, null,5,5,5,5));

        MainActivity.cart.add(new ModelProducts(1,"áo khoác",1
                ,"https://cdn3.yame.vn/pimg/ao-khoac-du-co-non-y2010-f04-0019699/98fdfb6e-0d53-0900-0600-0017214fa534.jpg?w=440",
                "Áo Khoác Classic Activewear M5 Màu Xám Trắng", (float) 200000, null,2,2,2,2));

        cartAdapter = new CartAdapter(MainActivity.cart);


        listViewProduct.setAdapter(cartAdapter);

    }

//    private boolean creatReceipt() {
//
//        ApiInterface apiInterface;
//        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
//        Call<ModelReceipt> call = apiInterface.creatBill();
//
//        return false;
//    }

    private void noProductAlert() {
        if (MainActivity.cart.isEmpty()){
            Toast.makeText(getApplicationContext(),"no product in cart!!!",Toast.LENGTH_LONG).show();
        }
    }

    private void hide() {
        if (MainActivity.cart.isEmpty()){
            tvEmpty.setVisibility(View.VISIBLE);
            totalText.setVisibility(View.INVISIBLE);
            totalPrice.setVisibility(View.INVISIBLE);
        }else {
            tvEmpty.setVisibility(View.INVISIBLE);
            totalText.setVisibility(View.VISIBLE);
            totalPrice.setVisibility(View.VISIBLE);
        }
    }


    private void mapping() {
        listViewProduct = findViewById(R.id.listproduct);
        btnBack = findViewById(R.id.backBtn);
        btnPay = findViewById(R.id.btnPay);
        btnReset = findViewById(R.id.btnReset);
        tvEmpty = findViewById(R.id.tv_Empty);
        totalPrice = findViewById(R.id.totalPrice);
        totalText = findViewById(R.id.totalText);
    }
}