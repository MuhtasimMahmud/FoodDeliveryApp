package com.mahmud.myapplication.model;

import java.util.List;

public class DataController {

    public static DataController instance;



    public static DataController getInstance(){

        if(instance == null){
            instance = new DataController();
        }
        return instance;
    }


    RestaurantInterface restaurantInterface;
    List<MenuItem> currentMenuItemList;


    public RestaurantInterface getRestaurantInterface() {
        return restaurantInterface;
    }

    public void setRestaurantInterface(RestaurantInterface restaurantInterface) {
        this.restaurantInterface = restaurantInterface;
    }

    public List< MenuItem > getCurrentMenuItemList() {
        return currentMenuItemList;
    }

    public void setCurrentMenuItemList(List< MenuItem > currentMenuItemList) {
        this.currentMenuItemList = currentMenuItemList;
    }
}
