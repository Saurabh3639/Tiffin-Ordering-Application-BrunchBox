package com.tiffin.app.AdminFoodPanel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tiffin.app.R;

import java.util.ArrayList;
import java.util.List;

public class AllOrders extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AdminFinalOrders1> adminFinalOrders1List;
    private AdminPreparedOrderAdapter adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);
        recyclerView = findViewById(R.id.Recycle_allOrders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllOrders.this));
        adminFinalOrders1List = new ArrayList<>();
        AdminPrepareOrders();
    }

    private void AdminPrepareOrders() {

        databaseReference = FirebaseDatabase.getInstance().getReference("AdminFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminFinalOrders1List.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DatabaseReference data = FirebaseDatabase.getInstance().getReference("AdminFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(snapshot.getKey()).child("OtherInformation");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            AdminFinalOrders1 adminFinalOrders1 = dataSnapshot.getValue(AdminFinalOrders1.class);
                            adminFinalOrders1List.add(adminFinalOrders1);
                            adapter = new AdminPreparedOrderAdapter(AllOrders.this, adminFinalOrders1List);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
