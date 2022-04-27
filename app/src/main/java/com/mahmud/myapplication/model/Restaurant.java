package com.mahmud.myapplication.model;

import java.util.List;

public class Restaurant {
    String restaurantName;
    String restaurantEmail;
    String rastaurantImgUrl;
    String restaurantDescription;

    List< MenuItem > restaurantMenulist;

    public Restaurant(){

    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantDescription) {
        this.restaurantEmail = restaurantDescription;
    }

    public String getRastaurantImgUrl() {
        return rastaurantImgUrl;
    }

    public void setRastaurantImgUrl(String rastaurantImgUrl) {
        this.rastaurantImgUrl = rastaurantImgUrl;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public List< MenuItem > getRestaurantMenulist() {
        return restaurantMenulist;
    }

    public void setRestaurantMenulist(List< MenuItem > restaurantMenulist) {
        this.restaurantMenulist = restaurantMenulist;
    }
}
