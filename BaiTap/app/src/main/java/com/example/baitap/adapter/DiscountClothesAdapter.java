package com.example.baitap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.R;
import com.example.baitap.model.DiscountClothes;

import java.util.List;

public class DiscountClothesAdapter extends RecyclerView.Adapter<DiscountClothesAdapter.DiscountClothesViewHolder> {


    Context context;

    public DiscountClothesAdapter(Context context, List<DiscountClothes> discountClothesList) {
        this.context = context;
        this.discountClothesList = discountClothesList;
    }

    List<DiscountClothes> discountClothesList;

    @NonNull
    @Override
    //Create holder
    public DiscountClothesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.discount_row_items,parent,false);
        return new DiscountClothesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountClothesViewHolder holder, int position) {

        holder.discountImageView.setImageResource(discountClothesList.get(position).getImageurl());
    }

    //Get picture from discount Clothes List
    @Override
    public int getItemCount() {
        return discountClothesList.size();
    }

    //Reference
    public static class DiscountClothesViewHolder extends RecyclerView.ViewHolder{
        ImageView discountImageView;

        public DiscountClothesViewHolder(@NonNull View itemView) {
            super(itemView);

            discountImageView = itemView.findViewById(R.id.discountImage);
        }
    }
}
