package com.example.baitap.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitap.R;
import com.example.baitap.activity.FilterProduct;
import com.example.baitap.model.ModelProducts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller> implements Filterable {
    private Context context;
    public ArrayList<ModelProducts>productList,filterList;

    //Use when filter product returns null variable
    private FilterProduct filterProduct;

    public AdapterProductSeller(Context context,ArrayList<ModelProducts>productList){
        this.context = context;
        this.productList = productList;
        this.filterList = productList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        * Inflate layout
        */
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_seller,parent,false);
        return new HolderProductSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {
        //Import data from database
        ModelProducts modelProducts = productList.get(position);
        Integer category = modelProducts.getCategory();
        String description = modelProducts.getDescription();
        Integer id = modelProducts.getId();
        String image = modelProducts.getImage();
        String name = modelProducts.getName();
        Integer price = modelProducts.getPrice();
        Integer promotion_id = modelProducts.getPromotion_id();
        Integer quantity_L_size = modelProducts.getQuantity_L_size();
        Integer quantity_M_size = modelProducts.getQuantity_M_size();
        Integer quantity_S_size = modelProducts.getQuantity_S_size();
        Integer quantity_XL_size = modelProducts.getQuantity_XL_size();

        //Set data
        holder.TV_productName.setText(name);
        holder.TV_QuantityS.setText(quantity_S_size);
        holder.TV_QuantityM.setText(quantity_M_size);
        holder.TV_QuantityL.setText(quantity_L_size);
        holder.TV_QuantityXL.setText(quantity_XL_size);
        holder.TV_originalPrice.setText(price);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        if(filterProduct == null){
            filterProduct = new FilterProduct(this,filterList);
        }
        return filterProduct;
    }

    public static class HolderProductSeller extends RecyclerView.ViewHolder {

        private ImageView IV_productIcon;
        private TextView TV_discountNote, TV_productName,
                TV_QuantityS,TV_QuantityM,TV_QuantityL,TV_QuantityXL
                ,TV_discountPrice,TV_originalPrice;
        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            IV_productIcon = itemView.findViewById(R.id.IV_productIcon);
            TV_productName = itemView.findViewById(R.id.TV_productName);
            TV_QuantityS = itemView.findViewById(R.id.TV_QuantityS);
            TV_QuantityM = itemView.findViewById(R.id.TV_QuantityM);
            TV_QuantityL = itemView.findViewById(R.id.TV_QuantityL);
            TV_QuantityXL = itemView.findViewById(R.id.TV_QuantityXL);
            TV_originalPrice = itemView.findViewById(R.id.TV_originalPrice);
            TV_discountNote = itemView.findViewById(R.id.TV_discountNote);
            TV_discountPrice = itemView.findViewById(R.id.TV_discountPrice);
        }

}}
