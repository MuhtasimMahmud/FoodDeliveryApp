package com.mahmud.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mahmud.myapplication.databinding.ActivityCustomerOptionsBinding;
import com.mahmud.myapplication.model.DataController;
import com.mahmud.myapplication.model.Restaurant;
import com.mahmud.myapplication.model.RestaurantInterface;

public class customerOptions extends AppCompatActivity implements RestaurantInterface {

    BottomNavigationView navigationView;
    private ActivityCustomerOptionsBinding binding;


    RestaurantInterface restaurantInterface;
    DataController controller;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCustomerOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        restaurantInterface = this;
        controller = DataController.getInstance();
        controller.setRestaurantInterface(restaurantInterface);


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.customerHome, R.id.customerCart, R.id.customerProfile).build();

        navController = Navigation.findNavController(this, R.id.customer_options);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        controller.setCurrentMenuItemList(restaurant.getRestaurantMenulist());
        navController.navigate(R.id.action_customerHome_to_navigation_menu);

    }

}