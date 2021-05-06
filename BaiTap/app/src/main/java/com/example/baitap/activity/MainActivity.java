package com.example.baitap.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.example.baitap.R
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
    private TextView nameTV,tvShopName,tvTabProducts,tvTabOrders;
    private ImageButton editProfileBtn,addProductBtn;
    private RelativeLayout RLProducts,RLOrders;
    public static ArrayList<ModelProducts> cart;
    public static ArrayList<Cart> cart;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<ModelProducts>> call = apiInterface.getAllProducts();

       call.enqueue(new Callback<List<ModelProducts>>() {
           @Override
           public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
               List<ModelProducts> productsList = response.body();

           }

           @Override
           public void onFailure(Call<List<ModelProducts>> call, Throwable t) {

           }
       });
        //Reference
        inint();
        //Adapter for ViewFlipperGirl,ViewFlipperBoy
        viewFlipperGirl.setFlipInterval(3000);
        viewFlipperGirl.setAutoStart(true);
        viewFlipperBoy.setFlipInterval(3000);
        viewFlipperBoy.setAutoStart(true);

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
        if(cart!=null){

        }else {
            cart = new ArrayList<>();
        }
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