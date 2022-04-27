package com.mahmud.myapplication;

import com.mahmud.myapplication.model.MenuItem;

import java.util.List;

public class CurrentMenu {

    List< MenuItem > Items;

    public CurrentMenu(List< MenuItem > items) {
        Items = items;
    }

    public List< MenuItem > getItems() {
        return Items;
    }

    public void setItems(List< MenuItem > items) {
        Items = items;
    }
}

