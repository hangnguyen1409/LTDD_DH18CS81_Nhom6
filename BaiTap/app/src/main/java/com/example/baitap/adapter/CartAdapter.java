package com.example.baitap.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baitap.R;
import com.example.baitap.activity.MainActivity;
import com.example.baitap.activity.ShowCartActivity;
import com.example.baitap.model.ModelProducts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    ArrayList<ModelProducts> listProduct;
    ImageView imageView;
    TextView name;
    ImageButton delete;

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



        //Bind dữ liệu phần tử vào View
        ModelProducts product = (ModelProducts) getItem(position);

        Picasso.get().load(product.getImage())
                .into(imageView);
        name.setText(String.format("%s ", product.getName()));
        ((TextView)viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%.0f VND", allPrice(product)));

        viewProduct.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cart.remove(position);
            }
        });
        viewProduct.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cart.remove(position);
                notifyDataSetChanged();
                MainActivity.listDiscount.remove(position);
                MainActivity.listCost.remove(position);
                ShowCartActivity.hide();
            }
        });

        return viewProduct;
    }

    private float allPrice(ModelProducts product) {
        return product.getPrice()*(product.getQuantity_XL_size()+product.getQuantity_S_size()
                + product.getQuantity_L_size() + product.getQuantity_M_size());
    }
}