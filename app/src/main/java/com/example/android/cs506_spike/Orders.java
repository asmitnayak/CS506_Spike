package com.example.android.cs506_spike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    ArrayList<RestaurantOrder> orders = new ArrayList<RestaurantOrder>();
    RecyclerView ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ordersList = (RecyclerView) findViewById(R.id.ordersList);
        ArrayList<RestaurantOrder> pastaOrder = new ArrayList<RestaurantOrder>();
        ArrayList<RestaurantMenuItem> orderItems = new ArrayList<RestaurantMenuItem>();
        orderItems.add(new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9));
        pastaOrder.add(new RestaurantOrder(1, "Dine In", orderItems, 19.99));
        pastaOrder.add(new RestaurantOrder(2, "Take Out", orderItems, 8.99));
        OrderAdapter orderAdp = new OrderAdapter(this, pastaOrder);
        ordersList.setAdapter(orderAdp);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }



}