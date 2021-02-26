package com.example.android.cs506_spike;

import android.widget.ImageView;

public class RestaurantMenuItem {
    private String itemName;
    private int menuImage;
    private double itemCost;
    private int itemAvailibility;
    private int itemChoosen;
    private boolean isChecked;

    public RestaurantMenuItem(){

    }
    public RestaurantMenuItem(String itemName, int menuImage, double itemCost, int itemAvailibility){
        this.itemName = itemName;
        this.menuImage = menuImage;
        this.itemCost = itemCost;
        this.itemAvailibility = itemAvailibility;
        this.itemChoosen = 0;
        this.isChecked = false;
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
    public void setItemChoosen(int items){
        this.itemChoosen = items;
    }
    public void setIsChecked(boolean select){
        this.isChecked = select;
    }

    public String getItemName(){
        return this.itemName;
    }
    public int getMenuImage(){
        return this.menuImage;
    }
    public int getItemChoosen(){
        return this.itemChoosen;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public double getItemCost() {
        return itemCost;
    }

    public int getItemAvailibility() {
        return itemAvailibility;
    }
    @Override
    public String toString(){
        String menuItemDescription = this.itemName + "\nPrice" + this.itemCost + "\nAvailable" + this.itemAvailibility;
        return menuItemDescription;
    }
}
