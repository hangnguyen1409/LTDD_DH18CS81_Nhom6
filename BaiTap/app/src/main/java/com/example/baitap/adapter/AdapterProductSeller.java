package com.example.baitap.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.R;



public class AdapterProductSeller {
    class HolderProductSeller extends RecyclerView.ViewHolder{
        private ImageView IV_productIcon;
        private TextView TV_discountNote,TV_title,TV_quantity;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);

            IV_productIcon = itemView.findViewById(R.id.IV_productIcon);
            TV_discountNote = itemView.findViewById(R.id.TV_discountNote);
        }
    }
}
