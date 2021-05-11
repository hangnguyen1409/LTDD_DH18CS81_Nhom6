package com.example.baitap.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitap.R;
import com.example.baitap.activity.ProductActivity;
import com.example.baitap.model.ModelCate;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.AllCategoryViewHolder> {

    Context context;
    List<ModelCate> categoryList;

    public AllCategoryAdapter(Context context, List<ModelCate> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public AllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.all_category_row_items, parent, false);

        return new AllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryViewHolder holder, int position) {
            ModelCate modelCate = categoryList.get(position);
            String catename = modelCate.getName();
            holder.TV_cate.setText(catename);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ProductActivity.class);
                    i.putExtra("Id_Cate", modelCate.getId());
                    context.startActivity(i);
                }

            });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static class AllCategoryViewHolder extends RecyclerView.ViewHolder{

        TextView TV_cate;

        public AllCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_cate = itemView.findViewById(R.id.TV_cate);
        }
    }

}
