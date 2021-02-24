package com.example.android.cs506_spike;

import android.widget.ImageView;

public class MenuItem {
    public String itemName;
    public String menuImage;
    public double itemCost;
    public int itemAvailibility;

    public MenuItem(){

    }
    public MenuItem(String itemName, String menuImage, double itemCost, int itemAvailibility){
        this.itemName = itemName;
        this.menuImage = menuImage;
        this.itemCost = itemCost;
        this.itemAvailibility = itemAvailibility;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setItemImage(String itemImage){
        this.menuImage = itemImage;
    }
    public void setItemCost(double itemCost){
        this.itemCost = itemCost;
    }
    public void setItemAvailibility(int itemAvailibility){
        this.itemAvailibility = itemAvailibility;
    }
}
