package com.mycafe.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore fStore;
    String fooditems, totalcost;
    FirebaseAuth fAuth;
    FirebaseUser user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fStore = FirebaseFirestore.getInstance();





        CollectionReference cb = fStore.collection("orders");
        cb.addSnapshotListener(MainActivity.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                //System.out.println(value.getDocuments());
                refresh();
               // Toast.makeText(MainActivity.this, "" + value.getDocuments(), Toast.LENGTH_SHORT).show();
            }
        });

        /*DocumentReference documentReference = fStore.collection("orders").document(userId);
        documentReference.addSnapshotListener(MainActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                fooditems = documentSnapshot.getString("foodorder");
                totalcost = documentSnapshot.getString("totalcost");

                //textView.setText(data);
                Toast.makeText(MainActivity.this, "" + fooditems + totalcost, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    private void refresh(){

        fStore.collection("orders").
                get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                userId=document.getId();

//Koushik, document.get("enter the field name") all the specific field oda data will be there.If you want to show entire document na use document.getData()
//so youtube la documents ellam epdi recyclerview la kamikarathu paaru

                                //here is the example for both
                                Toast.makeText(MainActivity.this, ""+document.get("date"), Toast.LENGTH_SHORT).show();

                                Toast.makeText(MainActivity.this, ""+document.getData(), Toast.LENGTH_SHORT).show();
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}