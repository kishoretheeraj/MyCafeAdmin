package com.mycafe.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    SimpleDateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
    Date todayDate = new Date();
    String thisDate = currentDate.format(todayDate);

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    CollectionReference ref = fStore.collection("orders");
    FirestoreRecyclerOptions<items> options;
    FirestoreRecyclerAdapter<items, RecyclerAdapter>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.reyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ReadData();

    }

    private void ReadData() {
        options = new FirestoreRecyclerOptions.Builder<items>().setQuery(ref.orderBy("time", Query.Direction.DESCENDING), items.class).build();
        adapter = new FirestoreRecyclerAdapter<items, RecyclerAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerAdapter holder, int position, @NonNull items model) {

                List<String> list1 = model.getFoodorder();
                String foodnames = "FOOD NAME";
                String foodqty = "QTY";
                String sno = "S NO";
                int i = 1;
                for (String x:list1) {
                    int temp = list1.indexOf(x);
                    if (temp%3 == 0) {
                        sno += "\n" + i + ".";
                        foodnames += "\n" + x;
                    }
                    else if ((temp+1)%3 == 0) {
                        foodqty += "\n" + x;
                        i += 1;
                    }
                }

                holder.orderID.setText(model.getOrderid());
                holder.userName.setText(model.getName());
                holder.userMobile.setText(model.getMobile());
                holder.orderDate.setText(model.getDate());
                holder.orderTime.setText(model.getTime());
                holder.totalCost.setText("Total Cost : Rs." + model.getTotalcost());
                holder.orderFoodName.setText(foodnames);
                holder.orderFoodQty.setText(foodqty);
                holder.sno.setText(sno);

                holder.receive.setOnClickListener(v -> {
                    holder.receive.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#04B80B")));
                    holder.receive.setTextColor(Color.WHITE);
                    holder.receive.setText("RECEIVED");
                    holder.receive.setEnabled(false);
                    holder.deliver.setVisibility(View.VISIBLE);
                });

                holder.deliver.setOnClickListener(v -> {
                    //Code for deliver button
                });

            }

            @NonNull
            @Override
            public RecyclerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                return new RecyclerAdapter(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}