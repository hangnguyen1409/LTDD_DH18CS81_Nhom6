package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitap.R;
import com.example.baitap.SessionManagement;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.LoginResponse;
import com.example.baitap.model.ModelUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button butnchangpass,butnchangeinfor;
    TextView showtextuser,showtextname,showtextemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        butnchangpass = findViewById(R.id.butnchangpass);
        butnchangeinfor = findViewById(R.id.butnchangeinfor);
        showtextuser = findViewById(R.id.showtextuser);
        showtextname = findViewById(R.id.showtextname);
        showtextemail = findViewById(R.id.showtextemail);
    }
    @Override
    protected void onStart() {
        super.onStart();
        butnchangeinfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ChangeInforActivity.class));
            }
        });
        butnchangpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ChangePassActivity.class));
            }
        });
        checkSession();

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
    public void ClickUser(View view){
       recreate();
    }
    public void ClickLogout(View view){
        MainActivity.redirectActivity(this,LoginActivity.class);
    }
    public void ClickExit(View view){
        MainActivity.logout(this);
    }
    public void ClickCart(View view){
        MainActivity.redirectActivity(this,ShowCartActivity.class);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(this);
        int userID = sessionManagement.getSession();

        if(userID != -1){
            //user id logged in and so move to mainActivity
            ApiInterface apiInterface;
            apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);

            Call<ModelUser> call = apiInterface.get_user_id(sessionManagement.getSession());
            call.enqueue(new Callback<ModelUser>() {
                @Override
                public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                    if(response.isSuccessful()){
                        showtextuser.setText("username:  "+ response.body().getUsername());
                        showtextname.setText("name:  "+ response.body().getName());
                        showtextemail.setText("email:  "+ response.body().getEmail());
                    }
                }

                @Override
                public void onFailure(Call<ModelUser> call, Throwable t) {

                }
            });
        }
        else{
            //do nothing
            startActivity(new Intent(UserProfile.this, LoginActivity.class));
        }
    }




}