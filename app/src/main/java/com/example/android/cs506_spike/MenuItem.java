package com.example.android.cs506_spike;

import android.widget.ImageView;

public class MenuItem {
    public String itemName;
    public ImageView menuImage;
    public double itemCost;
    public int itemAvailibility;

    public MenuItem(){

    }
    public MenuItem(String itemName, ImageView menuImage, double itemCost, int itemAvailibility){
        this.itemName = itemName;
        this.menuImage = menuImage;
        this.itemCost = itemCost;
        this.itemAvailibility = itemAvailibility;
    }
}
