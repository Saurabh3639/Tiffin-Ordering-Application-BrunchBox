package com.tiffin.app.AdminFoodPanel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiffin.app.Admin;
import com.tiffin.app.R;
import com.tiffin.app.SendNotification.APIService;
import com.tiffin.app.SendNotification.Client;
import com.tiffin.app.SendNotification.Data;
import com.tiffin.app.SendNotification.MyResponse;
import com.tiffin.app.SendNotification.NotificationSender;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPreparedOrderView extends AppCompatActivity {

    RecyclerView recyclerViewdish;
    private List<AdminFinalOrders> adminFinalOrdersList;
    private AdminPreparedOrderViewAdapter adapter;
    DatabaseReference reference;
    String RandomUID, userid;
    TextView grandtotal, address, name, number, time, fdate, tdate;
    LinearLayout l1;
    Button Prepared;
    private ProgressDialog progressDialog;
    private APIService apiService;
    Spinner Shipper;
    String deliveryId = "CLwDraf103cpAyTTOWi8Snsh5no2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_prepared_order_view);
        recyclerViewdish = findViewById(R.id.Recycle_viewOrder);
        grandtotal = findViewById(R.id.Gtotal);
        address = findViewById(R.id.Cadress);
        name = findViewById(R.id.Cname);
        number = findViewById(R.id.Cnumber);
        //access time, from date and to date from layout
        time = findViewById(R.id.tme);
        fdate = findViewById(R.id.fdt);
        tdate = findViewById(R.id.tdt);
        l1 = findViewById(R.id.linear);
        Shipper = findViewById(R.id.shipper);
        Prepared = findViewById(R.id.prepared);
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        progressDialog = new ProgressDialog(AdminPreparedOrderView.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(AdminPreparedOrderView.this));
        adminFinalOrdersList = new ArrayList<>();
        AdminorderdishesView();
    }

    private void AdminorderdishesView() {

        RandomUID = getIntent().getStringExtra("RandomUID");

        reference = FirebaseDatabase.getInstance().getReference("AdminFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminFinalOrdersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AdminFinalOrders adminFinalOrders = snapshot.getValue(AdminFinalOrders.class);
                    adminFinalOrdersList.add(adminFinalOrders);
                }
                if (adminFinalOrdersList.size() == 0) {
                    l1.setVisibility(View.INVISIBLE);

                } else {
                    l1.setVisibility(View.VISIBLE);
                    Prepared.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            progressDialog.setMessage("Please wait...");
                            progressDialog.show();

                            DatabaseReference data = FirebaseDatabase.getInstance().getReference("Admin").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            data.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    final Admin admin = dataSnapshot.getValue(Admin.class);
                                    final String AdminName = admin.getFname() + " " + admin.getLname();
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("AdminFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                final AdminFinalOrders adminFinalOrders = dataSnapshot1.getValue(AdminFinalOrders.class);
                                                HashMap<String, String> hashMap = new HashMap<>();
                                                String dishid = adminFinalOrders.getDishId();
                                                userid = adminFinalOrders.getUserId();
                                                hashMap.put("AdminId", adminFinalOrders.getAdminId());
                                                hashMap.put("DishId", adminFinalOrders.getDishId());
                                                hashMap.put("DishName", adminFinalOrders.getDishName());
                                                hashMap.put("DishPrice", adminFinalOrders.getDishPrice());
                                                hashMap.put("DishQuantity", adminFinalOrders.getDishQuantity());
                                                hashMap.put("RandomUID", RandomUID);
                                                hashMap.put("TotalPrice", adminFinalOrders.getTotalPrice());
                                                hashMap.put("UserId", adminFinalOrders.getUserId());
                                                FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(deliveryId).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap);
                                            }
                                            DatabaseReference data = FirebaseDatabase.getInstance().getReference("AdminFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                                            data.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    final AdminFinalOrders1 adminFinalOrders1 = dataSnapshot.getValue(AdminFinalOrders1.class);
                                                    HashMap<String, String> hashMap1 = new HashMap<>();
                                                    String adminid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                    hashMap1.put("Address", adminFinalOrders1.getAddress());
                                                    hashMap1.put("AdminId", adminid);
                                                    hashMap1.put("AdminName", AdminName);
                                                    hashMap1.put("GrandTotalPrice", adminFinalOrders1.getGrandTotalPrice());
                                                    hashMap1.put("MobileNumber", adminFinalOrders1.getMobileNumber());
                                                    hashMap1.put("Name", adminFinalOrders1.getName());
                                                    hashMap1.put("RandomUID", RandomUID);
                                                    hashMap1.put("Status", "Order is Prepared");
                                                    hashMap1.put("UserId", userid);

                                                    //adding time, from date and to date - OtherInformation
                                                    hashMap1.put("Time", adminFinalOrders1.getTime());
                                                    hashMap1.put("FromDate", adminFinalOrders1.getFromDate());
                                                    hashMap1.put("ToDate", adminFinalOrders1.getToDate());

                                                    FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(deliveryId).child(RandomUID).child("OtherInformation").setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(RandomUID).child("OtherInformation").child("Status").setValue("Order is Prepared...").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            String usertoken = dataSnapshot.getValue(String.class);
                                                                            sendNotifications(usertoken, "Estimated Time", "Your Order is Prepared", "Prepared");
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                                }
                                                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    FirebaseDatabase.getInstance().getReference().child("Tokens").child(deliveryId).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            String usertoken = dataSnapshot.getValue(String.class);
                                                                            sendNotifications(usertoken, "New Order", "You have a New Order", "DeliveryOrder");
                                                                            progressDialog.dismiss();
                                                                            AlertDialog.Builder builder = new AlertDialog.Builder(AdminPreparedOrderView.this);
                                                                            builder.setMessage("Order has been sent to shipper");
                                                                            builder.setCancelable(false);
                                                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int which) {

                                                                                    dialog.dismiss();
                                                                                    Intent b = new Intent(AdminPreparedOrderView.this, AdminPreparedOrder.class);
                                                                                    startActivity(b);
                                                                                    finish();


                                                                                }
                                                                            });
                                                                            AlertDialog alert = builder.create();
                                                                            alert.show();
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                                }
                                                            });


                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    });

                }
                adapter = new AdminPreparedOrderViewAdapter(AdminPreparedOrderView.this, adminFinalOrdersList);
                recyclerViewdish.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AdminFinalOrders1 adminFinalOrders1 = dataSnapshot.getValue(AdminFinalOrders1.class);
                grandtotal.setText("₹ " + adminFinalOrders1.getGrandTotalPrice());
                address.setText(adminFinalOrders1.getAddress());
                name.setText(adminFinalOrders1.getName());
                number.setText("+91" + adminFinalOrders1.getMobileNumber());

                //set text for textview of time, to date and from date
                time.setText(adminFinalOrders1.getTime());
                fdate.setText(adminFinalOrders1.getFromDate());
                tdate.setText(adminFinalOrders1.getToDate());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendNotifications(String usertoken, String title, String message, String prepared) {

        Data data = new Data(title, message, prepared);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(AdminPreparedOrderView.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }
}
