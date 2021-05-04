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
        /*
        * Import data from database
        */
        ModelProducts modelProducts = productList.get(position);
        Integer productId = modelProducts.getProductId();
        String productName = modelProducts.getProductName();
        String productDescription = modelProducts.getProductDescription();
        String productCategory = modelProducts.getProductCategory();
        Integer productQuantityS = modelProducts.getProductQuantityS();
        Integer productQuantityM = modelProducts.getProductQuantityM();
        Integer productQuantityL = modelProducts.getProductQuantityL();
        Integer productQuantityXL = modelProducts.getProductQuantityXL();
        String productImage = modelProducts.getProductImage();
        String originalPrice = modelProducts.getOriginalPrice();
        String userID = modelProducts.getUserID();

        //Set data
        holder.TV_productName.setText(productName);
        holder.TV_QuantityS.setText(productQuantityS);
        holder.TV_QuantityM.setText(productQuantityM);
        holder.TV_QuantityL.setText(productQuantityL);
        holder.TV_QuantityXL.setText(productQuantityXL);
        holder.TV_originalPrice.setText(originalPrice);

        //Load product Image
        try{
            Picasso.get().load(productImage).placeholder(R.drawable.ic_shopping_cart).into(holder.IV_productIcon);
        }catch(Exception e){
            //Cannot load
            holder.IV_productIcon.setImageResource(R.drawable.ic_shopping_cart);
        }
        //Click item product
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show item details

            }
        });
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

    class HolderProductSeller extends RecyclerView.ViewHolder {

        private ImageView IV_productIcon;
        private TextView TV_discountNote, TV_productName,TV_QuantityS,TV_QuantityM
                ,TV_QuantityL,TV_QuantityXL,TV_discountPrice,TV_originalPrice;
        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            IV_productIcon = itemView.findViewById(R.id.IV_productIcon);
            TV_productName = itemView.findViewById(R.id.TV_productName);
            TV_QuantityS = itemView.findViewById(R.id.TV_QuantityS);
            TV_QuantityM = itemView.findViewById(R.id.TV_QuantityM);
            TV_QuantityL = itemView.findViewById(R.id.TV_QuantityL);
            TV_QuantityXL = itemView.findViewById(R.id.TV_QuantityXL);
            TV_originalPrice = itemView.findViewById(R.id.TV_originalPrice);
        }

}}
