package com.example.android.cs506_spike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {
    ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    ArrayAdapter<RestaurantMenuItem> menuAdapter;
    CustomAdapter customAdp;
    ListView menuList;
    ArrayList<RestaurantMenuItem> foodItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        menuList = (ListView) findViewById(R.id.menuList);
        foodItems = new ArrayList<RestaurantMenuItem>();
        foodItems.add(new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9));

        for(int i = 0; i < 10; i++)
            foodItems.add(new RestaurantMenuItem("Pasta"+i, R.drawable.customer_view_background, 23.29, 6));
        customAdp = new CustomAdapter(getApplicationContext(), foodItems);
        menuList.setAdapter(customAdp);

//

//        menuList = (ListView) findViewById(R.id.menuList);
//        menuAdapter = new ArrayAdapter<RestaurantMenuItem> (this, android.R.layout.simple_list_item_1, menuItems);
//        menuList.setAdapter(menuAdapter);
//
//        menuItems.add(foodItems);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appmenu, menu);
        return true;
    }
//    public ArrayList<String> read_credentials() throws IOException {
//
//        int length = (int) mCreds.length();
//
//        byte[] bytes = new byte[length];
//
//        FileInputStream in = new FileInputStream(mCreds);
//        try {
//            in.read(bytes);
//        } finally {
//            in.close();
//        }
//
//        String contents = new String(bytes);
//
//        String array[] = contents.split("\n");
//        for(String s : array)
//            CREDENTIALS.add(s);
//
//        return CREDENTIALS;
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.customerCart) {
            goToCart();
        }else if (item.getItemId() == R.id.customerAccount){
            goToAccount();
        }

        return super.onOptionsItemSelected(item);
    }
    public void goToCart(){
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }
    public void goToAccount(){
        Intent intent = new Intent(this, CustomerAccount.class);
        startActivity(intent);
    }

}