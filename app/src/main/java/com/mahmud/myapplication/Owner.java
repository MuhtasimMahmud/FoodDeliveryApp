package com.mahmud.myapplication;

public class Owner {

    String RestaurantName;
    String RestaurantEmail;
    String rastaurantImgUrl;
    String restaurantDescription;


    public Owner(){

    }

    public Owner(String restaurantName, String restaurantEmail, String rastaurantImgUrl, String restaurantDescription) {
        RestaurantName = restaurantName;
        RestaurantEmail = restaurantEmail;
        this.rastaurantImgUrl = rastaurantImgUrl;
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return RestaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        RestaurantEmail = restaurantEmail;
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
}
