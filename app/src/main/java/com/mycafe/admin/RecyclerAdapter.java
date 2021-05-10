package com.mycafe.admin;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    ArrayList<items> datalist;
    String foodnames, foodqty;

    public RecyclerAdapter(ArrayList<items> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout,parent,false);
        @SuppressWarnings("UnnecessaryLocalVariable") ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        List<String> list1 = datalist.get(position).getFoodorder();
        foodnames = "";
        foodqty = "";
        for (String x:list1) {
            int temp = list1.indexOf(x);
            if (temp%3 == 0) {
                foodnames += x + "\n";
            }
            else if ((temp+1)%3 == 0) {
                foodqty += x + "\n";
            }
        }

        holder.orderID.setText(datalist.get(position).getOrderid());
        holder.userName.setText(datalist.get(position).getName());
        holder.userMobile.setText(datalist.get(position).getMobile());
        holder.orderDate.setText(datalist.get(position).getDate());
        holder.orderTime.setText(datalist.get(position).getTime());
        holder.totalCost.setText("Total Cost : Rs." + datalist.get(position).getTotalcost());
        holder.orderFoodName.setText(foodnames);
        holder.orderFoodQty.setText(foodqty);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView orderID, userName, userMobile, orderDate, orderTime, totalCost, orderFoodName, orderFoodQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.order_ID);
            userName = itemView.findViewById(R.id.user_Name);
            userMobile = itemView.findViewById(R.id.user_Mobile);
            orderDate = itemView.findViewById(R.id.order_Date);
            orderTime = itemView.findViewById(R.id.order_Time);
            totalCost = itemView.findViewById(R.id.total_Cost);
            orderFoodName = itemView.findViewById(R.id.order_food_name);
            orderFoodQty = itemView.findViewById(R.id.order_food_qty);
        }
    }
}
