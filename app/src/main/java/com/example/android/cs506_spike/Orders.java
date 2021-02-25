package com.example.android.cs506_spike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    ArrayAdapter<RestaurantMenuItem> menuAdapter;
    ListView ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ordersList = (ListView) findViewById(R.id.menuList);
        RestaurantMenuItem [] pasta = new RestaurantMenuItem[10];
        pasta[0] = new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9);
        pasta[1] = new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9);
        CustomAdapter customAdp = new CustomAdapter(getApplicationContext(), pasta);
        ordersList.setAdapter(customAdp);
    }



}