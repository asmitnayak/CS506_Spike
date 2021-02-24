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
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setItemImage(ImageView itemImage){
        this.menuImage = itemImage;
    }
    public void setItemCost(double itemCost){
        this.itemCost = itemCost;
    }
    public void setItemAvailibility(int itemAvailibility){
        this.itemAvailibility = itemAvailibility;
    }
}
