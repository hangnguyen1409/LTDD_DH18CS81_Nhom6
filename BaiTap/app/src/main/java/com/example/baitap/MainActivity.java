package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.baitap.adapter.DiscountClothesAdapter;
import com.example.baitap.model.DiscountClothes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declare whole variable
    RecyclerView discountRecyclerView;
    DiscountClothesAdapter discountClothesAdapter;
    List<DiscountClothes> discountClothesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding data to model
        discountClothesList = new ArrayList<>();
        discountClothesList.add(new DiscountClothes(1,R.drawable.discountset));
        discountClothesList.add(new DiscountClothes(2,R.drawable.discountdress));
        discountClothesList.add(new DiscountClothes(3,R.drawable.discountjean));
        discountClothesList.add(new DiscountClothes(4,R.drawable.discountmaxi));
        discountClothesList.add(new DiscountClothes(5,R.drawable.discountsport));


        //Reference
        discountRecyclerView = findViewById(R.id.recycler_view_discount);

        setDiscountRecycler(discountClothesList);
    }

    private void setDiscountRecycler(List<DiscountClothes> dataList) {
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        discountRecyclerView.setLayoutManager(layoutManager);

        discountClothesAdapter = new DiscountClothesAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountClothesAdapter);
    }
}