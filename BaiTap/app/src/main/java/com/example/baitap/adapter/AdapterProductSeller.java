package com.example.baitap.adapter;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.baitap.R;
import com.example.baitap.activity.MainActivity;
import com.example.baitap.activity.ShowCartActivity;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.ModelCate;
import com.example.baitap.model.ModelProducts;
import com.example.baitap.model.Promotion;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.baitap.activity.MainActivity.cart;
import static com.example.baitap.activity.MainActivity.listCost;
import static com.example.baitap.activity.MainActivity.listDiscount;


public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller> implements Filterable {
    private Context context;
    public List<ModelProducts> productList;
    public List<ModelProducts> productListFull;
    public AdapterProductSeller(Context context,List<ModelProducts>productList){
        this.context = context;
        this.productList = productList;
        productListFull = new ArrayList<>();
        productListFull.addAll(productList);
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
            ModelProducts modelProducts = productList.get(position);
            String img = modelProducts.getImage();
            String name = modelProducts.getName();
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
            holder.TV_originalPrice.setText(String.format("%.0f", price) + "VN??");
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
                        holder.TV_discountPrice.setText(String.format("%.0f", discountPrice) + "VN??");
                        holder.TV_discountPrice.setVisibility(View.VISIBLE);
                        holder.TV_originalPrice.setPaintFlags(holder.TV_originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }

                    @Override
                    public void onFailure(Call<Promotion> call, Throwable t) {
                    }
                });
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailsBottomSheet(modelProducts);
                }
            });


    }
    private double costPrice = 0;
    private double finalCostPrice = 0;
    private double costDiscount = 0;
    private double finalCostDiscount = 0;
    private int quanS = 0;
    private int quanM = 0;
    private int quanL = 0;
    private int quanXL= 0;

    private void showQuantityDialog(ModelProducts modelProducts) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quantity,null);

        ImageView productIV = view.findViewById(R.id.productIV);
        TextView titleTV = view.findViewById(R.id.titleTV);
        TextView descriptionTV = view.findViewById(R.id.descriptionTV);
        TextView discountNoteTV = view.findViewById(R.id.discountNoteTV);
        TextView originalPriceTV = view.findViewById(R.id.originalPriceTV);
        TextView discountPriceTV = view.findViewById(R.id.discountPriceTV);
        TextView finalPriceTV = view.findViewById(R.id.finalPriceTV);

        ImageButton decrementBtnS = view.findViewById(R.id.decrementBtnS);
        ImageButton decrementBtnM = view.findViewById(R.id.decrementBtnM);
        ImageButton decrementBtnL = view.findViewById(R.id.decrementBtnL);
        ImageButton decrementBtnXL = view.findViewById(R.id.decrementBtnXL);

        ImageButton incrementBtnS = view.findViewById(R.id.incrementBtnS);
        ImageButton incrementBtnM = view.findViewById(R.id.incrementBtnM);
        ImageButton incrementBtnL = view.findViewById(R.id.incrementBtnL);
        ImageButton incrementBtnXL = view.findViewById(R.id.incrementBtnXL);

        TextView quanS_TV = view.findViewById(R.id.quanS);
        TextView quanM_TV = view.findViewById(R.id.quanM);
        TextView quanL_TV = view.findViewById(R.id.quanL);
        TextView quanXL_TV = view.findViewById(R.id.quanXL);

        Button continueBtn = view.findViewById(R.id.continueBtn);


        String img = modelProducts.getImage();
        String name = modelProducts.getName();
        String des = modelProducts.getDescription();
        Integer promotion_id = modelProducts.getPromotion_id();
        Integer quantity_L_size = modelProducts.getQuantity_L_size();
        Integer quantity_M_size = modelProducts.getQuantity_M_size();
        Integer quantity_S_size = modelProducts.getQuantity_S_size();
        Integer quantity_XL_size = modelProducts.getQuantity_XL_size();

        Float price = modelProducts.getPrice();
        if(modelProducts.getPromotion_id() == null){
            //not discount
            discountNoteTV.setVisibility(View.GONE);
            discountPriceTV.setVisibility(View.GONE);
            costPrice = Double.parseDouble(String.valueOf(price).replaceAll("VN??",""));
            finalCostPrice = Double.parseDouble(String.valueOf(0).replaceAll("VN??",""));

            finalPriceTV.setText(String.format("%.0f",finalCostPrice) +"VN??");
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
                    discountNoteTV.setText(promotion.getName());
                    discountNoteTV.setVisibility(View.VISIBLE);
                    Float discountPrice = modelProducts.getPrice() - (modelProducts.getPrice() * promotion.getDiscount());
                    discountPriceTV.setText(String.format("%.0f",discountPrice) + "VN??");
                    originalPriceTV.setPaintFlags(originalPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    costDiscount = Double.parseDouble(String.valueOf(discountPrice).replaceAll("VN??",""));
                    finalCostDiscount = Double.parseDouble(String.valueOf(0).replaceAll("VN??",""));
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) +"VN??");

                }

                @Override
                public void onFailure(Call<Promotion> call, Throwable t) {
                }
            });
        }
        quanS = 0;
        quanM = 0;
        quanL = 0;
        quanXL = 0;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        try {
            Glide.with(context).load(img).into(productIV);;
        }
        catch (Exception ex) {
           productIV.setImageResource(R.drawable.ic_shopping_cart);
        }
        titleTV.setText(name);
        quanS_TV.setText(String.valueOf(quanS));
        quanM_TV.setText(String.valueOf(quanM));
        quanL_TV.setText(String.valueOf(quanL));
        quanXL_TV.setText(String.valueOf(quanXL));
        descriptionTV.setText(des);
        originalPriceTV.setText(String.format("%.0f",price) + "VN??");



        AlertDialog dialog = builder.create();
        dialog.show();

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -1;
                if (cart.size()>0){
                    for (int i=0 ; i < MainActivity.cart.size(); i++){
                        if (modelProducts.getId() == (MainActivity.cart.get(i).getId())) {
                            index = i;
                        }
                    }
                }
                if (index > -1){ // trung voi product trong cart
                    ModelProducts newPrduct = null;
                    try {
                        newPrduct = (ModelProducts) MainActivity.cart.get(index).clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    newPrduct.setQuantity_S_size(quanS + newPrduct.getQuantity_S_size());
                    newPrduct.setQuantity_M_size(quanM + newPrduct.getQuantity_M_size());
                    newPrduct.setQuantity_L_size(quanL + newPrduct.getQuantity_L_size());
                    newPrduct.setQuantity_XL_size(quanXL + newPrduct.getQuantity_XL_size());
                    Float total = modelProducts.getPrice()*(quanS+quanM+quanL+quanXL);
                    if(total == 0){
                        Toast.makeText(view.getContext(),"Please choose your size...",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        MainActivity.listCost.set(index, MainActivity.listCost.get(index) + total);
                        if (finalCostDiscount != 0) {
                            MainActivity.listDiscount.set(index, MainActivity.listDiscount.get(index) + (float) finalCostDiscount);
                            newPrduct.setPrice(newPrduct.getPrice() + (float) costDiscount);
                        } else {
                            MainActivity.listDiscount.set(index, MainActivity.listDiscount.get(index) + (float) finalCostPrice);
                            newPrduct.setPrice(newPrduct.getPrice() + (float) costPrice);
                        }
                        MainActivity.cart.set(index, newPrduct);
                        Toast.makeText(view.getContext(), "Add To Cart...", Toast.LENGTH_SHORT).show();
                        dialog.hide();
                    }
                }else { // khong trung
                    ModelProducts newPrduct = null;
                    try {
                        newPrduct = (ModelProducts) modelProducts.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    newPrduct.setQuantity_S_size(quanS);
                    newPrduct.setQuantity_M_size(quanM);
                    newPrduct.setQuantity_L_size(quanL);
                    newPrduct.setQuantity_XL_size(quanXL);
                    Float total = modelProducts.getPrice()*(quanS+quanM+quanL+quanXL);
                    if(total == 0){
                        Toast.makeText(view.getContext(),"Please choose your size...",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        MainActivity.listCost.add(total);
                        if (finalCostDiscount != 0) {
                            MainActivity.listDiscount.add((float) finalCostDiscount);
                            newPrduct.setPrice((float) costDiscount);
                        } else {
                            MainActivity.listDiscount.add((float) finalCostPrice);
                            newPrduct.setPrice((float) costPrice);
                        }
                        MainActivity.cart.add(newPrduct);
                        Toast.makeText(view.getContext(), "Add To Cart...", Toast.LENGTH_SHORT).show();
                        dialog.hide();
                    }
                }
            }
        });
        incrementBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanS <= quantity_S_size && promotion_id != null) {
                    quanS++;
                    quanS_TV.setText(String.valueOf(quanS));
                    finalCostDiscount = finalCostDiscount + costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanS <= quantity_S_size && promotion_id == null){
                    quanS++;
                    quanS_TV.setText(String.valueOf(quanS));
                    finalCostPrice = finalCostPrice + costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }});
        incrementBtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanM <= quantity_M_size && promotion_id != null) {
                    quanM++;
                    quanM_TV.setText(String.valueOf(quanM));
                    finalCostDiscount = finalCostDiscount + costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanM <= quantity_M_size && promotion_id == null){
                    quanM++;
                    quanM_TV.setText(String.valueOf(quanM));
                    finalCostPrice = finalCostPrice + costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });

        incrementBtnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanL <= quantity_L_size && promotion_id != null) {
                    quanL++;
                    quanL_TV.setText(String.valueOf(quanL));
                    finalCostDiscount = finalCostDiscount + costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanL <= quantity_XL_size && promotion_id == null){
                    quanL++;
                    quanL_TV.setText(String.valueOf(quanL));
                    finalCostPrice = finalCostPrice + costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });

        incrementBtnXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanXL <= quantity_XL_size && promotion_id != null) {
                    quanXL++;
                    quanXL_TV.setText(String.valueOf(quanXL));
                    finalCostDiscount = finalCostDiscount + costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanXL <= quantity_XL_size && promotion_id == null){
                    quanXL++;
                    quanXL_TV.setText(String.valueOf(quanXL));
                    finalCostPrice = finalCostPrice + costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });
        decrementBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanS > 0 && promotion_id != null ) {
                    quanS--;
                    quanS_TV.setText(String.valueOf(quanS));
                    finalCostDiscount = finalCostDiscount - costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanS > 0 && promotion_id == null&& quanS > 0){
                    quanS--;
                    quanS_TV.setText(String.valueOf(quanS));
                    finalCostPrice = finalCostPrice - costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });
        decrementBtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanM > 0 && promotion_id != null) {
                    quanM--;
                    quanM_TV.setText(String.valueOf(quanM));
                    finalCostDiscount = finalCostDiscount - costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanM > 0 && promotion_id == null&& quanM > 0){
                    quanM--;
                    quanM_TV.setText(String.valueOf(quanM));
                    finalCostPrice = finalCostPrice - costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });
        decrementBtnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanL > 0 && promotion_id != null) {
                    quanL--;
                    quanL_TV.setText(String.valueOf(quanL));
                    finalCostDiscount = finalCostDiscount - costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanL <= quantity_XL_size && promotion_id == null&& quanL > 0){
                    quanL--;
                    quanL_TV.setText(String.valueOf(quanL));
                    finalCostPrice = finalCostPrice - costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });
        decrementBtnXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanXL > 0 && promotion_id != null) {
                    quanXL--;
                    quanXL_TV.setText(String.valueOf(quanXL));
                    finalCostDiscount = finalCostDiscount - costDiscount;
                    finalPriceTV.setText(String.format("%.0f",finalCostDiscount) + "VN??");
                }
                else if(quanXL <= quantity_XL_size && promotion_id == null && quanXL > 0){
                    quanXL--;
                    quanXL_TV.setText(String.valueOf(quanXL));
                    finalCostPrice = finalCostPrice - costPrice;
                    finalPriceTV.setText(String.format("%.0f",finalCostPrice) + "VN??");
                }
            }
        });
    }

    private void detailsBottomSheet(ModelProducts modelProducts) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.product_detail_seller, null);
        bottomSheetDialog.setContentView(view);
        ImageView IV_add = view.findViewById(R.id.IV_add);
        ImageButton backBtn = view.findViewById(R.id.backBtn);
        ImageView IV_productIcon = view.findViewById(R.id.IV_productIcon);
        TextView TV_discountNote = view.findViewById(R.id.TV_discountNote);
        TextView TV_productName = view.findViewById(R.id.TV_productName);
        TextView TV_productDes = view.findViewById(R.id.TV_productDes);
        TextView TV_originalPrice = view.findViewById(R.id.TV_originalPrice);
        TextView TV_discountPrice = view.findViewById(R.id.TV_discountPrice);
        TextView TV_category = view.findViewById(R.id.TV_category);
        String img = modelProducts.getImage();
        String name = modelProducts.getName();
        String description = modelProducts.getDescription();
        Integer cate = modelProducts.getCategory();
        Float price = modelProducts.getPrice();
        Integer promotion_id = modelProducts.getPromotion_id();


        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<ModelCate> callcate = apiInterface.getAllCateById(cate);
        callcate.enqueue(new Callback<ModelCate>() {
            @Override
            public void onResponse(Call<ModelCate> call, Response<ModelCate> response) {
                ModelCate modelCate = response.body();
                TV_category.setText("Category: "+ modelCate.getName());
            }

            @Override
            public void onFailure(Call<ModelCate> call, Throwable t) {

            }
        });
        TV_productName.setText(name);
        String pro = "No description !!!";
        try {
            if (description == null) {
                TV_productDes.setText(pro);
            }
            TV_productDes.setText(description);
        }catch (NullPointerException ex){
        }

        TV_originalPrice.setText(String.format("%.0f", price) + "VN??");
        if (promotion_id == null) {
            //not discount
            TV_discountNote.setVisibility(View.GONE);
            TV_discountPrice.setVisibility(View.GONE);
        } else {
            //discount
            apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
            Call<Promotion> Promo = apiInterface.getPromotioById(promotion_id);
            Promo.enqueue(new Callback<Promotion>() {
                @Override
                public void onResponse(Call<Promotion> call, Response<Promotion> response) {
                    Promotion promotion = response.body();
                    TV_discountNote.setText(promotion.getName());
                    TV_discountNote.setVisibility(View.VISIBLE);
                    Float discountPrice = price - (price * promotion.getDiscount());
                    TV_discountPrice.setText(String.format("%.0f", discountPrice) + "VN??");
                    TV_discountPrice.setVisibility(View.VISIBLE);
                    TV_originalPrice.setPaintFlags(TV_originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }

                @Override
                public void onFailure(Call<Promotion> call, Throwable t) {
                }
            });
        }
        try {
            Glide.with(context).load(img).into(IV_productIcon);
            ;
        } catch (Exception ex) {
            IV_productIcon.setImageResource(R.drawable.ic_shopping_cart);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        IV_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuantityDialog(modelProducts);
            }
        });
        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    @Override
    public Filter getFilter() {
        return filterList;
    }
    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ModelProducts> filteredList = new ArrayList<>();
            try {
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(productListFull);
                } else {
                    for (ModelProducts item: productListFull) {
                        if (item.toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                }
            } catch (NullPointerException ex){

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public static class HolderProductSeller extends RecyclerView.ViewHolder {
        ImageView IV_productIcon,IV_add;
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
