package com.example.baitap.adapter;


import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baitap.R;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.ModelCate;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.Promotion;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller>{
    private Context context;
    public List<ModelProducts> productList;
    public List<Promotion> promotionList;
    public AdapterProductSeller(Context context,List<ModelProducts>productList){
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_seller,parent,false);
        return new HolderProductSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {

            Float discountPrice;
            ModelProducts modelProducts = productList.get(position);
            String img = modelProducts.getImage();
            String name = modelProducts.getName();
            Integer category = modelProducts.getCategory();
            String description = modelProducts.getDescription();
            Integer id = modelProducts.getId();
            Float price = modelProducts.getPrice();
            Integer promotion_id = modelProducts.getPromotion_id();
            Integer quantity_L_size = modelProducts.getQuantity_L_size();
            Integer quantity_M_size = modelProducts.getQuantity_M_size();
            Integer quantity_S_size = modelProducts.getQuantity_S_size();
            Integer quantity_XL_size = modelProducts.getQuantity_XL_size();


            holder.TV_productName.setText(name);
            holder.TV_QuantityS.setText(quantity_S_size.toString());
            holder.TV_QuantityM.setText(quantity_M_size.toString());
            holder.TV_QuantityL.setText(quantity_L_size.toString());
            holder.TV_QuantityXL.setText(quantity_XL_size.toString());
            holder.TV_originalPrice.setText("$" + price.toString());
            try {
                Glide.with(context).load(img).into(holder.IV_productIcon);;
            }
            catch (Exception ex) {
                holder.IV_productIcon.setImageResource(R.drawable.ic_shopping_cart);
            }
            if(productList.get(position).getPromotion_id() == null){
                //not discount
                holder.TV_discountNote.setVisibility(View.GONE);
                holder.TV_discountPrice.setVisibility(View.GONE);
            }
            else {
                //discount
                ApiInterface apiInterface;
                apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
                Call<Promotion> Promo = apiInterface.getPromotioById(promotion_id);
                Promo.enqueue(new Callback<Promotion>() {
                    @Override
                    public void onResponse(Call<Promotion> call, Response<Promotion> response) {
                        Promotion promotion = response.body();
                        holder.TV_discountNote.setText(promotion.getName());
                        holder.TV_discountNote.setVisibility(View.VISIBLE);
                        Float discountPrice = productList.get(position).getPrice() - (productList.get(position).getPrice() * promotion.getDiscount());
                        holder.TV_discountPrice.setText(discountPrice.toString()+"VNĐ");
                        holder.TV_discountPrice.setVisibility(View.VISIBLE);
                        holder.TV_originalPrice.setPaintFlags(holder.TV_originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }

                    @Override
                    public void onFailure(Call<Promotion> call, Throwable t) {
                    }
                });
            }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class HolderProductSeller extends RecyclerView.ViewHolder {
        ImageView IV_productIcon;
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
