package com.example.btl.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.example.btl.model.BannerClothes;

import java.util.List;

public class BannerClothesPagerAdapter extends PagerAdapter {

    Context context;
    List<BannerClothes> bannerClothesList;


    public BannerClothesPagerAdapter(Context context, List<BannerClothes> bannerClothesList) {
        this.context = context;
        this.bannerClothesList = bannerClothesList;
    }

    @Override
    public int getCount() {
        return bannerClothesList.size();
    }

    // Check old view of banner
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //Remove old banner

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    //Inflate xml layout to view and add transfer view to ImageView
    // List of Clothes Banner gets position, ImageUrl from ImageView
    // Container adds items from List of Clothes Banner
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_clothes_layout,null);
        ImageView bannerImage = view.findViewById(R.id.banner_clothes_image);

        Glide.with(context).load(bannerClothesList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);
        return view;
    }
}
