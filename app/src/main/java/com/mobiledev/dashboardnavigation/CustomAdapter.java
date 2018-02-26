package com.mobiledev.dashboardnavigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by manu on 1/7/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    Context context;
    ArrayList<ItemModel> itemModels;
    public CustomAdapter(Context context, ArrayList<ItemModel> itemModels){
        this.context =  context;
        this.itemModels = itemModels;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }
}
