package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.btl.adapter.BannerClothesPagerAdapter;
import com.example.btl.model.BannerClothes;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BannerClothesPagerAdapter bannerClothesPagerAdapter;
    TabLayout tabLayout;
    ViewPager bannerClothesListPager;
    List<BannerClothes> bannerClothesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerClothesList = new ArrayList<>();
        /*
        bannerClothesList.add(new BannerClothes(1,"","",""));
        bannerClothesList.add(new BannerClothes(2,"","",""));
        bannerClothesList.add(new BannerClothes(3,"","",""));
        bannerClothesList.add(new BannerClothes(4,"","",""));
        bannerClothesList.add(new BannerClothes(5,"","",""));
        */
        setBannerClothesPagerAdapter(bannerClothesList);
    }
    //Set adapter for Banner Clothes List ViewPager
    private void setBannerClothesPagerAdapter(List<BannerClothes> bannerClothesList){
        bannerClothesListPager = findViewById(R.id.viewPager_bannerClothesList);
        bannerClothesPagerAdapter = new BannerClothesPagerAdapter(this,bannerClothesList);
        bannerClothesListPager.setAdapter(bannerClothesPagerAdapter);
    }
}