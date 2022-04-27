package com.mahmud.myapplication.ownerPanel;

public class FoodDetails {
    public String item_name, item_price, item_description, RandomUID, ownerID;

    public FoodDetails() {

    }

    public FoodDetails(String item_name, String item_price, String item_description, String randomUID, String ownerID) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        RandomUID = randomUID;
        this.ownerID = ownerID;
    }
}
