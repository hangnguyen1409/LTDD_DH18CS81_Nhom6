package com.example.baitap.activity;

import android.widget.Filter;

import com.example.baitap.adapter.AdapterProductSeller;
import com.example.baitap.model.ModelProducts;

import java.util.ArrayList;
import java.util.List;

public class FilterProduct extends Filter {

    private AdapterProductSeller adapter;
    private List<ModelProducts> filterList;

    public FilterProduct(AdapterProductSeller adapter, List<ModelProducts> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //get data to search
        if(constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            //filter list
            ArrayList<ModelProducts> filterModels = new ArrayList<>();

            for(int i = 0; i< filterList.size();i++)
            {
                if(filterList.get(i).getName().toUpperCase().equals(constraint)){
                    //Add data to list
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


}