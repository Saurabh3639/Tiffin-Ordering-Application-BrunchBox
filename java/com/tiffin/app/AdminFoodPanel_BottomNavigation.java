package com.tiffin.app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tiffin.app.AdminFoodPanel.AdminHomeFragment;
import com.tiffin.app.AdminFoodPanel.AdminOrderFragment;
import com.tiffin.app.AdminFoodPanel.AdminPendingOrdersFragment;
import com.tiffin.app.AdminFoodPanel.AdminProfileFragment;
import com.tiffin.app.SendNotification.Token;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.iid.FirebaseInstanceId;

public class AdminFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_panel__bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.admin_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        UpdateToken();
        String name = getIntent().getStringExtra("PAGE");
        if (name != null) {
            if (name.equalsIgnoreCase("Orderpage")) {
                loadadminfragment(new AdminPendingOrdersFragment());
            } else if (name.equalsIgnoreCase("Confirmpage")) {
                loadadminfragment(new AdminOrderFragment());
            } else if (name.equalsIgnoreCase("AcceptOrderpage")) {
                loadadminfragment(new AdminHomeFragment());
            } else if (name.equalsIgnoreCase("Deliveredpage")) {
                loadadminfragment(new AdminHomeFragment());
            }
        } else {
            loadadminfragment(new AdminHomeFragment());
        }
    }

    private void UpdateToken() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Task<String> refreshToken = FirebaseMessaging.getInstance().getToken();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    private boolean loadadminfragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.adminHome:
                fragment = new AdminHomeFragment();
                break;

            case R.id.PendingOrders:
                fragment = new AdminPendingOrdersFragment();
                break;

            case R.id.Orders:
                fragment = new AdminOrderFragment();
                break;
            case R.id.adminProfile:
                fragment = new AdminProfileFragment();
                break;
        }
        return loadadminfragment(fragment);
    }
}
