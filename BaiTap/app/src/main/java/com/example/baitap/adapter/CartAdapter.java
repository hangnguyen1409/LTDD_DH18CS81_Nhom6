package com.example.baitap.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baitap.R;
import com.example.baitap.activity.ShowCartActivity;
import com.example.baitap.model.ModelProducts;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.baitap.activity.MainActivity.cart;
import static com.example.baitap.activity.MainActivity.listCost;
import static com.example.baitap.activity.MainActivity.listDiscount;

public class CartAdapter extends BaseAdapter {

    ArrayList<ModelProducts> listProduct;
    ImageView imageView;
    TextView name;
    LinearLayout linearLayout;

    public CartAdapter(ArrayList<ModelProducts> listProduct){
        this.listProduct = listProduct;
    }
    @Override
    public int getCount() {
        return listProduct.size();
    }
    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listProduct.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.cart_item, null);
        } else viewProduct = convertView;
        imageView =viewProduct.findViewById(R.id.productImage);
        name = viewProduct.findViewById(R.id.nameproduct);
        linearLayout = viewProduct.findViewById(R.id.linearLayout);



        //Bind dữ liệu phần tử vào View
        ModelProducts product = (ModelProducts) getItem(position);

        Picasso.get().load(product.getImage())
                .into(imageView);
        name.setText(String.format("%s ", product.getName()));
        ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f VND", allPrice(product)));

//        viewProduct.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cart.remove(position);
//            }
//        });
        viewProduct.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.remove(position);
                notifyDataSetChanged();
                listDiscount.remove(position);
                listCost.remove(position);
                ShowCartActivity.hide();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                View view = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_quantity,null);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();



                ImageView productIV = view.findViewById(R.id.productIV);
                try {
                    Glide.with(v.getContext()).load(product.getImage()).into(productIV);;
                }
                catch (Exception ex) {
                    productIV.setImageResource(R.drawable.ic_shopping_cart);
                }
                TextView titleTV = view.findViewById(R.id.titleTV);
                titleTV.setText(product.getName());
                TextView descriptionTV = view.findViewById(R.id.descriptionTV);
                descriptionTV.setText(product.getDescription());
                view.findViewById(R.id.discountNoteTV).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.originalPriceTV).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.discountPriceTV).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.finalPriceTV).setVisibility(View.INVISIBLE);

                ImageButton decrementBtnS = view.findViewById(R.id.decrementBtnS);
                ImageButton decrementBtnM = view.findViewById(R.id.decrementBtnM);
                ImageButton decrementBtnL = view.findViewById(R.id.decrementBtnL);
                ImageButton decrementBtnXL = view.findViewById(R.id.decrementBtnXL);

                ImageButton incrementBtnS = view.findViewById(R.id.incrementBtnS);
                ImageButton incrementBtnM = view.findViewById(R.id.incrementBtnM);
                ImageButton incrementBtnL = view.findViewById(R.id.incrementBtnL);
                ImageButton incrementBtnXL = view.findViewById(R.id.incrementBtnXL);

                TextView quanS_TV = view.findViewById(R.id.quanS);
                quanS_TV.setText(String.valueOf(product.getQuantity_S_size()));
                TextView quanM_TV = view.findViewById(R.id.quanM);
                quanM_TV.setText(String.valueOf(product.getQuantity_M_size()));
                TextView quanL_TV = view.findViewById(R.id.quanL);
                quanL_TV.setText(String.valueOf(product.getQuantity_L_size()));
                TextView quanXL_TV = view.findViewById(R.id.quanXL);
                quanXL_TV.setText(String.valueOf(product.getQuantity_XL_size()));

                Button continueBtn = view.findViewById(R.id.continueBtn);

                continueBtn.setText("Update");

                continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.hide();
                        notifyDataSetChanged();
                        ShowCartActivity.hide();
                    }
                });

                incrementBtnS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanS_TV).getText().toString());
                        float lc = ShowCartActivity.total()+ listCost.get(position)/(cart.get(position).totalQuantitySize());
                        quanS_TV.setText(String.valueOf(quan+1));
                        cart.get(position).setQuantity_S_size(quan+1);
                        float dc = listDiscount.get(position) + cart.get(position).getPrice();
                        listDiscount.set(position,dc);
                        listCost.set(position, lc);
                    }});
                incrementBtnM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanM_TV).getText().toString());
                        float lc = ShowCartActivity.total()+ listCost.get(position)/(cart.get(position).totalQuantitySize());
                        quanM_TV.setText(String.valueOf(quan+1));
                        cart.get(position).setQuantity_M_size(quan+1);
                        float dc = listDiscount.get(position) + cart.get(position).getPrice();
                        listDiscount.set(position,dc);
                        listCost.set(position, lc);
                    }});

                incrementBtnL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanL_TV).getText().toString());
                        float lc = ShowCartActivity.total()+ listCost.get(position)/(cart.get(position).totalQuantitySize());
                        quanL_TV.setText(String.valueOf(quan+1));
                        cart.get(position).setQuantity_L_size(quan+1);
                        float dc = listDiscount.get(position) + cart.get(position).getPrice();
                        listDiscount.set(position,dc);
                        listCost.set(position, lc);
                    }
                });

                incrementBtnXL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanXL_TV).getText().toString());
                        float lc = ShowCartActivity.total()+ listCost.get(position)/(cart.get(position).totalQuantitySize());
                        quanXL_TV.setText(String.valueOf(quan+1));
                        cart.get(position).setQuantity_XL_size(quan+1);
                        float dc = listDiscount.get(position) + cart.get(position).getPrice();
                        listDiscount.set(position,dc);
                        listCost.set(position, lc);
                    }
                });
                decrementBtnS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanS_TV).getText().toString());
                        if ((cart.get(position).getQuantity_M_size() == 0 & cart.get(position).getQuantity_L_size()== 0 &
                                cart.get(position).getQuantity_XL_size()==0) & quan < 2){

                        }else if (quan > 0 ){
                            float lc = ShowCartActivity.total()- listCost.get(position)/(cart.get(position).totalQuantitySize());
                            quanS_TV.setText(String.valueOf(quan-1));
                            cart.get(position).setQuantity_S_size(quan-1);
                            float dc = listDiscount.get(position) - cart.get(position).getPrice();
                            listDiscount.set(position,dc);
                            listCost.set(position, lc);
                        }
                    }
                });
                decrementBtnM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanM_TV).getText().toString());
                        if ((cart.get(position).getQuantity_S_size() == 0 & cart.get(position).getQuantity_L_size()== 0 &
                                cart.get(position).getQuantity_XL_size()==0) & quan < 2){

                        }else if (quan > 0){
                            float lc = ShowCartActivity.total()- listCost.get(position)/(cart.get(position).totalQuantitySize());
                            quanM_TV.setText(String.valueOf(quan-1));
                            cart.get(position).setQuantity_M_size(quan-1);
                            float dc = listDiscount.get(position) - cart.get(position).getPrice();
                            listDiscount.set(position,dc);
                            listCost.set(position, lc);
                        }
                    }
                });
                decrementBtnL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanL_TV).getText().toString());
                        if ((cart.get(position).getQuantity_M_size() == 0 & cart.get(position).getQuantity_S_size()== 0 &
                                cart.get(position).getQuantity_XL_size()==0) & quan < 2){

                        }else  if (quan > 0){
                            float lc = ShowCartActivity.total()- listCost.get(position)/(cart.get(position).totalQuantitySize());
                            quanL_TV.setText(String.valueOf(quan-1));
                            cart.get(position).setQuantity_L_size(quan-1);
                            float dc = listDiscount.get(position) - cart.get(position).getPrice();
                            listDiscount.set(position,dc);
                            listCost.set(position, lc);
                        }
                    }
                });
                decrementBtnXL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quan = Integer.parseInt((quanXL_TV).getText().toString());
                        if ((cart.get(position).getQuantity_M_size() == 0 & cart.get(position).getQuantity_L_size()== 0 &
                                cart.get(position).getQuantity_S_size()==0) & quan < 2){

                        }else  if (quan > 0){
                            float lc = ShowCartActivity.total()- listCost.get(position)/(cart.get(position).totalQuantitySize());
                            quanXL_TV.setText(String.valueOf(quan-1));
                            cart.get(position).setQuantity_XL_size(quan-1);
                            float dc = listDiscount.get(position) - cart.get(position).getPrice();
                            listDiscount.set(position,dc);
                            listCost.set(position, lc);
                        }
                    }
                });
            }
        });

        return viewProduct;
    }

    private float allPrice(ModelProducts product) {
        return product.getPrice()*(product.getQuantity_XL_size()+product.getQuantity_S_size()
                + product.getQuantity_L_size() + product.getQuantity_M_size());
    }
}