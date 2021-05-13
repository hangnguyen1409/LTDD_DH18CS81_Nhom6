package com.example.baitap.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baitap.R;
import com.example.baitap.activity.ProductActivity;
import com.example.baitap.model.ModelProducts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    ArrayList<ModelProducts> listProduct;
    ImageView imageView;
    TextView name, quantityS, quantityM, quantityL, quantityXL;
    ImageButton plushS, plushM, plushL, plushXL, minusS, minusM, minusL, minusXL, delete;

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
        quantityS = viewProduct.findViewById(R.id.edt_quantityProductS);
        quantityM = viewProduct.findViewById(R.id.edt_quantityProductM);
        quantityL = viewProduct.findViewById(R.id.edt_quantityProductL);
        quantityXL = viewProduct.findViewById(R.id.edt_quantityProductXL);
        plushS = viewProduct.findViewById(R.id.plusProductS);
        plushM = viewProduct.findViewById(R.id.plusProductM);
        plushL = viewProduct.findViewById(R.id.plusProductL);
        plushXL = viewProduct.findViewById(R.id.plusProductXL);
        minusS = viewProduct.findViewById(R.id.minusProductS);
        minusM = viewProduct.findViewById(R.id.minusProductM);
        minusL = viewProduct.findViewById(R.id.minusProductL);
        minusXL = viewProduct.findViewById(R.id.minusProductXL);



        //Bind dữ liệu phần tử vào View
        ModelProducts product = (ModelProducts) getItem(position);

        Picasso.get().load(product.getImage())
                .into(imageView);
        name.setText(String.format("%s ", product.getName()));
        ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
        quantityS.setText(String.valueOf(product.getQuantity_S_size()));
        quantityM.setText(String.valueOf(product.getQuantity_M_size()));
        quantityL.setText(String.valueOf(product.getQuantity_L_size()));
        quantityXL.setText(String.valueOf(product.getQuantity_XL_size()));

        viewProduct.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.cart.remove(position);
            }
        });


        viewProduct.findViewById(R.id.plusProductS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductS)).getText().toString());
                ((EditText)viewProduct.findViewById(R.id.edt_quantityProductS)).setText(String.valueOf(quan+1));
                ProductActivity.cart.get(position).setQuantity_S_size(quan+1);
                ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
            }
        });

        viewProduct.findViewById(R.id.minusProductS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductS)).getText().toString());
                if (quan >=1){
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductS)).setText(String.valueOf(quan-1));
                    ProductActivity.cart.get(position).setQuantity_S_size(quan-1);
                    ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
                }else{
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductS)).setText("0");
                }
            }
        });

        viewProduct.findViewById(R.id.plusProductM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductM)).getText().toString());
                ((EditText)viewProduct.findViewById(R.id.edt_quantityProductM)).setText(String.valueOf(quan+1));
                ProductActivity.cart.get(position).setQuantity_M_size(quan+1);
                ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
            }
        });

        viewProduct.findViewById(R.id.minusProductM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductM)).getText().toString());
                if (quan >=1){
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductM)).setText(String.valueOf(quan-1));
                    ProductActivity.cart.get(position).setQuantity_M_size(quan-1);
                    ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
                }else{
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductM)).setText("0");
                }
            }
        });

        viewProduct.findViewById(R.id.plusProductL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductL)).getText().toString());
                ((EditText)viewProduct.findViewById(R.id.edt_quantityProductL)).setText(String.valueOf(quan+1));
                ProductActivity.cart.get(position).setQuantity_L_size(quan+1);
                ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
            }
        });

        viewProduct.findViewById(R.id.minusProductL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductL)).getText().toString());
                if (quan >=1){
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductL)).setText(String.valueOf(quan-1));
                    ProductActivity.cart.get(position).setQuantity_L_size(quan-1);
                    ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
                }else{
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductL)).setText("0");
                }
            }
        });

        viewProduct.findViewById(R.id.plusProductXL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductXL)).getText().toString());
                ((EditText)viewProduct.findViewById(R.id.edt_quantityProductXL)).setText(String.valueOf(quan+1));
                ProductActivity.cart.get(position).setQuantity_XL_size(quan+1);
                ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
            }
        });

        viewProduct.findViewById(R.id.minusProductXL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProductXL)).getText().toString());
                if (quan >=1){
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductXL)).setText(String.valueOf(quan-1));
                    ProductActivity.cart.get(position).setQuantity_XL_size(quan-1);
                    ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f", allPrice(product)));
                }else{
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProductXL)).setText("0");
                }
            }
        });

        return viewProduct;
    }

    private float allPrice(ModelProducts product) {
        return product.getPrice()*(product.getQuantity_XL_size()+product.getQuantity_S_size()
                + product.getQuantity_L_size() + product.getQuantity_M_size());
    }
}