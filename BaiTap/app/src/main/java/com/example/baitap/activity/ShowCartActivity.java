package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitap.R;
import com.example.baitap.adapter.CartAdapter;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.Mess;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.ModelReceipt;
import com.example.baitap.model.ModelReciptDetail;

import org.json.JSONException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCartActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    CartAdapter cartAdapter;
    ListView listViewProduct;
    Button btnPay, btnReset;
    TextView tvEmpty, totalPrice, totalText, discountText, discountPrice, paymentText, paymentPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        drawerLayout = findViewById(R.id.drawer_layout);

        if(ProductActivity.cart!=null){
        }
        else
        {
            ProductActivity.cart = new ArrayList<>();
        }
        mapping();
        hide();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noProductAlert();
                ProductActivity.cart.clear();
                hide();
                cartAdapter.notifyDataSetChanged();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProductActivity.isAuthenticated){
                    try {
                        if(creatReceipt()){
                            ProductActivity.cart.clear();
                            hide();
                            cartAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    startActivity(new Intent(ShowCartActivity.this, LoginActivity.class));
                }
            }
        });


        cartAdapter = new CartAdapter(ProductActivity.cart);

        listViewProduct.setAdapter(cartAdapter);

    }


    private int total() {
        int total = 0;
        for (ModelProducts products : ProductActivity.cart
        ) {
            total+=products.totalPriceAllSize();
        }
        return total;
    }
    private Float discounted(){
        float dc = 0;
        for (Float i: ProductActivity.listDiscount
        ) {
            if (i!= null){
                dc+= i;
            }

        }
        return dc;
    }


    private boolean creatReceipt() throws JSONException {
        ModelReceipt obj = new ModelReceipt(ProductActivity.Login.getUsername(),ProductActivity.Login.getEmail(),
                mappingCartIntoReceipDetail());
        ApiInterface apiInterface;
        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<Mess> call = apiInterface.creatBill(obj);

        call.enqueue(new Callback<Mess>() {
            @Override
            public void onResponse(Call<Mess> call, Response<Mess> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(ShowCartActivity.this,response.body().getMess(),Toast.LENGTH_SHORT).show();
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(ShowCartActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Mess> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Oh no, Have an error!!! Please try again",Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }

    private ArrayList<ModelReciptDetail> mappingCartIntoReceipDetail() {
        ArrayList<ModelReciptDetail> listP = new ArrayList<>();
        for (ModelProducts p: ProductActivity.cart){
            ModelReciptDetail product = new ModelReciptDetail(p.getId(),
                    p.getQuantity_S_size(), p.getQuantity_M_size(),
                    p.getQuantity_L_size(), p.getQuantity_XL_size(),
                    p.totalPriceAllSize());
            listP.add(product);
        }
        return listP;
    }


    private void noProductAlert() {
        if (ProductActivity.cart.isEmpty()){
            Toast.makeText(getApplicationContext(),"no product in cart!!!",Toast.LENGTH_LONG).show();
        }
    }
    private void hide() {
        if (ProductActivity.cart.isEmpty()){
            tvEmpty.setVisibility(View.VISIBLE);
            totalText.setVisibility(View.INVISIBLE);
            totalPrice.setVisibility(View.INVISIBLE);
            discountText.setVisibility(View.INVISIBLE);
            discountPrice.setVisibility(View.INVISIBLE);
            paymentText.setVisibility(View.INVISIBLE);
            paymentPrice.setVisibility(View.INVISIBLE);
            btnPay.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.INVISIBLE);
        }else {
            tvEmpty.setVisibility(View.INVISIBLE);
            totalText.setVisibility(View.VISIBLE);
            totalPrice.setVisibility(View.VISIBLE);
            discountText.setVisibility(View.VISIBLE);
            discountPrice.setVisibility(View.VISIBLE);
            paymentText.setVisibility(View.VISIBLE);
            paymentPrice.setVisibility(View.VISIBLE);
            btnPay.setVisibility(View.VISIBLE);
            btnReset.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.totalPrice)).setText(String.valueOf(total()));
            discountPrice.setText(String.valueOf(total()- discounted()));
            paymentPrice.setText(String.valueOf(discounted()));
        }
    }

    private void mapping() {
        listViewProduct = findViewById(R.id.listproduct);
//        btnBack = findViewById(R.id.backBtn);
        btnPay = findViewById(R.id.btnPay);
        btnReset = findViewById(R.id.btnReset);
        tvEmpty = findViewById(R.id.tv_Empty);
        totalPrice = findViewById(R.id.totalPrice);
        totalText = findViewById(R.id.totalText);
        drawerLayout=findViewById(R.id.drawer_layout);
        discountText = findViewById(R.id.distcountText);
        discountPrice = findViewById(R.id.discountPrice);
        paymentPrice = findViewById(R.id.paymentPrice);
        paymentText = findViewById(R.id.paymentText);
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
        MainActivity.redirectActivity(this,ProductActivity.class);
    }
    public void ClickCart(View view){
      recreate();
    }
    public void ClickUser(View view){
        MainActivity.redirectActivity(this,UserProfile.class);
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

}