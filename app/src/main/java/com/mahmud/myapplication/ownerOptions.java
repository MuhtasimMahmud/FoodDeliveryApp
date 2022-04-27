package com.mahmud.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mahmud.myapplication.model.RestaurantInterface;
import com.mahmud.myapplication.ownerPanel.ownerPendingOrderFragment;
import com.mahmud.myapplication.ownerPanel.ownerProfileFragment;
import com.mahmud.myapplication.ownerPanel.ownerUpdateItemFragment;

public class ownerOptions extends AppCompatActivity {

    Button add_remove_item, price_update, see_orders;
    BottomNavigationView navigationView;

    RestaurantInterface restaurantInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_options);


        navigationView = findViewById(R.id.owner_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.ownerHome:
                        fragment = new ownerProfileFragment();
                        break;
                    case R.id.pendingOrders:
                        fragment = new ownerPendingOrderFragment();
                        break;
                    case R.id.ownerProfile:
                        fragment = new ownerUpdateItemFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();

                return true;
            }
        });
    }

}