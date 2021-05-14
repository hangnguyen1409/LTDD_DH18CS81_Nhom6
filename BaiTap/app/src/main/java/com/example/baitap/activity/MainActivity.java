package com.example.baitap.activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.baitap.R;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    public static ArrayList<ModelProducts> cart;

    public static ArrayList<Float> listDiscount;
    public static ArrayList<Float> listCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        viewFlipper= findViewById(R.id.view_flipper);

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        if(cart!=null){
        }
        else
        {
            listDiscount = new ArrayList<>();
            listCost = new ArrayList<>();
            cart = new ArrayList<>();
        }

    }
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);

    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        recreate();
    }
    public void ClickProduct(View view){
        redirectActivity(this,ProductActivity.class);
    }
    public void ClickUser(View view){
        redirectActivity(this,UserProfile.class);
    }
    public void ClickCart(View view){
        MainActivity.redirectActivity(this,ShowCartActivity.class);
    }
    public void ClickLogout(View view){
        redirectActivity(this,LoginActivity.class);
    }
    public void ClickExit(View view){
        logout(this);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit");
        builder.setTitle("Do you still want to exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aclass) {
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}

