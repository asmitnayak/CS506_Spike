package com.example.android.cs506_spike;

import android.widget.ImageView;

public class RestaurantMenuItem {
    private String itemName;
    private int menuImage;
    private double itemCost;
    private int itemAvailibility;

    public RestaurantMenuItem(){

    }
    public RestaurantMenuItem(String itemName, int menuImage, double itemCost, int itemAvailibility){
        this.itemName = itemName;
        this.menuImage = menuImage;
        this.itemCost = itemCost;
        this.itemAvailibility = itemAvailibility;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setItemImage(int itemImage){
        this.menuImage = itemImage;
    }
    public void setItemCost(double itemCost){
        this.itemCost = itemCost;
    }
    public void setItemAvailibility(int itemAvailibility){
        this.itemAvailibility = itemAvailibility;
    }

    public String getItemName(){
        return this.itemName;
    }
    public int getMenuImage(){
        return this.menuImage;
    }

    public double getItemCost() {
        return itemCost;
    }

    public int getItemAvailibility() {
        return itemAvailibility;
    }
}
