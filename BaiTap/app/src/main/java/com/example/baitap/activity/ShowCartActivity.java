package com.example.baitap.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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

import static com.example.baitap.activity.MainActivity.cart;
import static com.example.baitap.activity.MainActivity.listCost;
import static com.example.baitap.activity.MainActivity.listDiscount;

public class ShowCartActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    CartAdapter cartAdapter;
    ListView listViewProduct;
    public static Button btnPay, btnReset;
    public static TextView tvEmpty, totalPrice, totalText, discountText, discountPrice, paymentText, paymentPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        drawerLayout = findViewById(R.id.drawer_layout);
        if(cart!=null){
        }
        else
        {
            listDiscount = new ArrayList<>();
            listCost = new ArrayList<>();
            cart = new ArrayList<>();
        }

        mapping();
        hide();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noProductAlert();
                cart.clear();
                listDiscount.clear();
                listCost.clear();
                hide();
                cartAdapter.notifyDataSetChanged();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProductActivity.isAuthenticated){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowCartActivity.this);
                    builder.setTitle("Submit");
                    builder.setTitle("Do you still want to pay ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                if(creatReceipt()){
                                    cart.clear();
                                    listDiscount.clear();
                                    listCost.clear();
                                    hide();
                                    cartAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }else {
                    startActivity(new Intent(ShowCartActivity.this, LoginActivity.class));
                }
            }
        });
        cartAdapter = new CartAdapter(cart);
        listViewProduct.setAdapter(cartAdapter);
    }


    public static Float total(){
        float tt = 0;
        for (Float i: listCost
        ) {
            if (i!= null){
                tt+= i;
            }

        }
        return tt;
    }
    private static Float discounted(){
        float dc = 0;
        for (Float i: MainActivity.listDiscount
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
                if(response.isSuccessful()&response.body().getMess().equals("mua hàng thành công ,cảm ơn bạn đã mua hàng <3")) {
                    Toast.makeText(ShowCartActivity.this,"mua hàng thành công ,cảm ơn bạn đã mua hàng <3",Toast.LENGTH_LONG).show();
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(ShowCartActivity.this,response.body().getMess(),Toast.LENGTH_SHORT).show();
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
        for (ModelProducts p: cart){
            ModelReciptDetail product = new ModelReciptDetail(p.getId(),
                    p.getQuantity_S_size(), p.getQuantity_M_size(),
                    p.getQuantity_L_size(), p.getQuantity_XL_size(),
                    p.totalPriceAllSize());
            listP.add(product);
        }
        return listP;
    }


    private void noProductAlert() {
        if (cart.isEmpty()){
            Toast.makeText(getApplicationContext(),"no product in cart!!!",Toast.LENGTH_LONG).show();
        }
    }
    public static void hide() {
        if (cart.isEmpty()){
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
            totalPrice.setText(String.valueOf(total()));
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