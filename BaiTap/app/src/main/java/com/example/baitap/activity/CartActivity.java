package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
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
import com.example.baitap.model.Mess;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.ModelReceipt;
import com.example.baitap.model.ModelReciptDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {
    CartAdapter cartAdapter;
    DrawerLayout drawerLayout;
    ListView listViewProduct;
    Button btnPay, btnReset;
    ImageButton btnBack;
    TextView tvEmpty, totalPrice, totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        drawerLayout = findViewById(R.id.drawer_layout);

        if(ProductActivity.cart!=null){
        }
        else
        {
            ProductActivity.cart = new ArrayList<>();
        }
//          Tạo đối tượng để test, khi nào các hoạt động khác hoàn thành sẽ xóa
//        Con phần xóa sản phẩm trong Cart,
//                chuyển Trang khi chưa login,
//        phần add sản phẩm vào cart(Lấy thông tin từ sản phẩm khi được click)
        ProductActivity.isAuthenticated = false;
        ProductActivity.Login.setId(1);
        ProductActivity.Login.setUsername("tuan");
        ProductActivity.Login.setEmail("1851050167tuan@ou.edu.vn");
        ProductActivity.cart.add(new ModelProducts(1,"áo khoác",1
                ,"https://cdn3.yame.vn/pimg/ao-thun-nam-y2010-basic-bf01-0019691/435b20ea-0323-1700-c5fe-001742e83abe.jpg?w=440",
                "Áo Khoác Classic Activewear M5 Màu Xám Trắng", (float) 200000, null,5,5,5,5));

        ProductActivity.cart.add(new ModelProducts(1,"áo khoác",2
                ,"https://cdn3.yame.vn/pimg/ao-khoac-du-co-non-y2010-f04-0019699/98fdfb6e-0d53-0900-0600-0017214fa534.jpg?w=440",
                "Áo Khoác Classic Activewear M5 Màu Xám Trắng", (float) 200000, null,2,2,2,2));

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
                ProductActivity.cart.clear();
                hide();
                cartAdapter.notifyDataSetChanged();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProductActivity.cart.toArray().length > 0){
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
                        //Nếu chưa đăng nhập thì chuyển qua Login activity
                    startActivity(new Intent(CartActivity.this, LoginActivity.class));
                    }
                }
                else {
                    noProductAlert();
                }

//
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
    //About Us - Didn't create form

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



    private boolean creatReceipt() throws JSONException {

        ModelReceipt obj = new ModelReceipt(ProductActivity.Login.getUsername(),ProductActivity.Login.getEmail(),
                mappingCartIntoReceipDetail());
        System.out.println(obj);
        ApiInterface apiInterface;
        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<Mess> call = apiInterface.creatBill(obj);

        call.enqueue(new Callback<Mess>() {
            @Override
            public void onResponse(Call<Mess> call, Response<Mess> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.body().getMess(),Toast.LENGTH_SHORT).show();
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mess> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Oh no, Have an error!!! Please try again",Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }

    private List<ModelReciptDetail> mappingCartIntoReceipDetail() {
        List<ModelReciptDetail> listP = new ArrayList<>();
        for (ModelProducts p:ProductActivity.cart){
            ModelReciptDetail product = new ModelReciptDetail(
                    p.getId(),
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
        }else {
            tvEmpty.setVisibility(View.INVISIBLE);
            totalText.setVisibility(View.VISIBLE);
            totalPrice.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.totalPrice)).setText(String.valueOf(total()));
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