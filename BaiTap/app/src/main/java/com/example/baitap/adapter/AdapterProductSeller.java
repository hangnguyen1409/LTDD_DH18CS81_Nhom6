package com.example.baitap.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitap.R;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;


public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller>{
    private Context context;
    public ArrayList<ModelProducts>productList;

    public AdapterProductSeller(Context context,ArrayList<ModelProducts>productList){
        this.context = context;
        this.productList= productList;

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

        //Set data from database
        holder.TV_title.setText(productName);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class HolderProductSeller extends RecyclerView.ViewHolder {

        private ImageView IV_productIcon;
        private TextView TV_discountNote, TV_title,TV_QuantityS,TV_QuantityM
                ,TV_QuantityL,TV_QuantityXL,TV_discountPrice,TV_originalPrice;
        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);

            IV_productIcon = itemView.findViewById(R.id.IV_productIcon);
            TV_discountNote = itemView.findViewById(R.id.TV_discountNote);
            TV_title = itemView.findViewById(R.id.TV_title);
            TV_QuantityS = itemView.findViewById(R.id.TV_QuantityS);
            TV_QuantityM = itemView.findViewById(R.id.TV_QuantityM);
            TV_QuantityL = itemView.findViewById(R.id.TV_QuantityL);
            TV_QuantityXL = itemView.findViewById(R.id.TV_QuantityXL);
            TV_discountPrice = itemView.findViewById(R.id.TV_discountPrice);
            TV_originalPrice = itemView.findViewById(R.id.TV_originalPrice);


        }




}}
