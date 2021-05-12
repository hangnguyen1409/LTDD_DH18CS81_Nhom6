package com.example.baitap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.baitap.R;

public class ShowCartActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        drawerLayout = findViewById(R.id.drawer_layout);

        //Hiển thị cart ở đây

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