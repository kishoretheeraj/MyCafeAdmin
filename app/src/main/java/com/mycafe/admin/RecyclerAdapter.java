package com.mycafe.admin;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;

public class RecyclerAdapter extends RecyclerView.ViewHolder{

    TextView orderID, userName, userMobile, orderDate, orderTime, totalCost, orderFoodName, orderFoodQty, sno;
    TableRow tableRow;
    TableLayout tableLayout;
    Chip receive;
    Button deliver;

    public RecyclerAdapter(@NonNull View itemView) {
        super(itemView);
        orderID = itemView.findViewById(R.id.order_ID);
        userName = itemView.findViewById(R.id.user_Name);
        userMobile = itemView.findViewById(R.id.user_Mobile);
        orderDate = itemView.findViewById(R.id.order_Date);
        orderTime = itemView.findViewById(R.id.order_Time);
        totalCost = itemView.findViewById(R.id.total_Cost);
        orderFoodName = itemView.findViewById(R.id.order_food_name);
        orderFoodQty = itemView.findViewById(R.id.order_food_qty);
        sno = itemView.findViewById(R.id.serialno);
        tableRow = itemView.findViewById(R.id.order_row);
        tableLayout = itemView.findViewById(R.id.order_Card);
        receive = itemView.findViewById(R.id.chipReceive);
        deliver = itemView.findViewById(R.id.buttonDeliver);
    }
}
