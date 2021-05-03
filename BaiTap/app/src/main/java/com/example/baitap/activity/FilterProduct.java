package com.example.baitap.activity;

import android.widget.Filter;

import com.example.baitap.adapter.AdapterProductSeller;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;
import java.util.List;

public class FilterProduct extends Filter {

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();

            ArrayList<ModelProducts> filterModels = new ArrayList<>();
            /**
             * filterModels: List of products after searching every turn
             * filterList: List of products
             */

            for(int i = 0; i< filterList.size();i++)
            {
                if(filterList.get(i).getProductName().toUpperCase().equals(constraint)
                        || filterList.get(i).getProductCategory().toUpperCase().equals(constraint)){
                    //Add data which was searched
                    filterModels.add(filterList.get(i));
                }

            }
            //If found --> done searched
            results.count = filterModels.size();
            results.values = filterModels;
        }
        else
        {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }
    //Final result
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.productList = (ArrayList<ModelProducts>)results.values;
        //Refresh adapter of product seller
        adapter.notifyDataSetChanged();
    }
    private AdapterProductSeller adapter;
    private List<ModelProducts> filterList;

    public FilterProduct(AdapterProductSeller adapter, List<ModelProducts> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

}
