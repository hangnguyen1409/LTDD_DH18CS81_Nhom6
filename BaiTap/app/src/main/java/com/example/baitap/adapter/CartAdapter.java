package com.example.baitap.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.baitap.R;
import com.example.baitap.activity.MainActivity;
import com.example.baitap.model.Cart;
import com.example.baitap.model.ModelProducts;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    ArrayList<Cart> listProduct;

    public CartAdapter(ArrayList<Cart> listProduct){
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
        return listProduct.get(position).getProductId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.cart_item, null);
        } else viewProduct = convertView;

        //Bind dữ liệu phần tử vào View
        Cart product = (Cart) getItem(position);

      /*Thủy làm*/
        Picasso.get().load(product.getProductImage())
                .into((ImageView) viewProduct.findViewById(R.id.productImage));
        ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText(String.format("%s (%s)", product.getProductName(), product.getSize()));
        ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%s", product.getOriginalPrice()));
        ((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).setText(String.valueOf(product.getProductQuantity()));

      /*Hằng làm*/
       Picasso.get().load(/*"https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"*/product.getProductImage())
                .into((ImageView) viewProduct.findViewById(R.id.productImage));
        ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText(String.format("%s", product.getProductName()));
        ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText(String.format("%s", product.getOriginalPrice()));
        /*((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).setText(product.getProductQuantity());*/





        viewProduct.findViewById(R.id.plusProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).getText().toString());
                ((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).setText(String.valueOf(quan+1));
               /* MainActivity.cart.get(position).s(quan+1); */
            }
        });

        viewProduct.findViewById(R.id.minusProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).getText().toString());
                if (quan >=2){
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).setText(String.valueOf(quan-1));
                }else{
                    ((EditText)viewProduct.findViewById(R.id.edt_quantityProduct)).setText("1");
                }
            }
        });


        return viewProduct;
    }
}

