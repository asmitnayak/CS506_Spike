package com.example.android.cs506_spike;

import java.util.ArrayList;

public class RestaurantOrder {
    private int orderNumber;
    private ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    private double cost;

    public RestaurantOrder(){}

    public RestaurantOrder(int orderNumber, ArrayList<RestaurantMenuItem> menuItems, double cost){
        this.orderNumber = orderNumber;
        this.menuItems = menuItems;
        this.cost = cost;
    }

    public void setOrderNumber(int orderNumber){ this.orderNumber = orderNumber;}
    public void setMenuItems(ArrayList<RestaurantMenuItem> menuItems){ this.menuItems = menuItems;}
    public void setCost(double cost){ this.cost = cost;}

    public int getOrderNumber(){return this.orderNumber;}
    public ArrayList<RestaurantMenuItem> getMenuItems(){return this.menuItems;}
    public double getCost(){return this.cost;}

}
