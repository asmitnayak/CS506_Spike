package com.example.android.cs506_spike;

import java.util.ArrayList;

public class RestaurantOrder {
    private int orderNumber;
    private String deliveryType;
    private ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    private double cost;

    public RestaurantOrder(){}

    public RestaurantOrder(int orderNumber, String deliveryType, ArrayList<RestaurantMenuItem> menuItems, double cost){
        this.orderNumber = orderNumber;
        this.deliveryType = deliveryType;
        this.menuItems = menuItems;
        this.cost = cost;
    }

    public void setOrderNumber(int orderNumber){ this.orderNumber = orderNumber;}
    public void setDeliveryType(String deliveryType){ this.deliveryType = deliveryType;}
    public void setMenuItems(ArrayList<RestaurantMenuItem> menuItems){ this.menuItems = menuItems;}
    public void setCost(double cost){ this.cost = cost;}

    public int getOrderNumber(){return this.orderNumber;}
    public String getDeliveryType(){return this.deliveryType;}
    public ArrayList<RestaurantMenuItem> getMenuItems(){return this.menuItems;}
    public double getCost(){return this.cost;}

}
