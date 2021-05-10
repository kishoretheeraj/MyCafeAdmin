package com.mycafe.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore fStore;
    ArrayList<items> datalist;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.reyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();
        adapter = new RecyclerAdapter(datalist);
        recyclerView.setAdapter(adapter);

        fStore = FirebaseFirestore.getInstance();
        fStore.collection("orders").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<DocumentSnapshot> list1 = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list1){
                        items obj = d.toObject(items.class);
                        datalist.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                    refresh();
                });

    }

    private void refresh() {
        fStore.collection("orders").addSnapshotListener((value, error) -> {
            if (error!=null) {
                Log.w("TAG", "Listen Failed", error);
                return;
            }

            for (DocumentChange doc : value.getDocumentChanges()) {
                datalist.add(doc.getDocument().toObject(items.class));
                adapter.notifyDataSetChanged();
            }
        });
    }
}