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

public class AdminModifyMenuList extends AppCompatActivity {
    ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    ArrayAdapter<RestaurantMenuItem> menuAdapter;
    ListView menuId;
    RestaurantMenuItem [] pasta = new RestaurantMenuItem[10];

    boolean first = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modify_menu_list);

        menuId = (ListView) findViewById(R.id.menuId);

        // TODO: CHANGE TO ARRAYLIST
        /*if(first == false) {
            pasta[0] = new RestaurantMenuItem("Pasta1", R.drawable.customer_view_background, 19.99, 9);
            pasta[1] = new RestaurantMenuItem("Pasta2", R.drawable.customer_view_background, 19.99, 9);
            first = true;
        }

        int j = 0;
        while(pasta[j] != null){
            System.out.println("Pasta value : " + pasta[j].getItemName());
            j++;
        }

        CustomAdapter customAdp = new CustomAdapter(getApplicationContext(), pasta);
        menuId.setAdapter(customAdp);
        */



//        menuList = (ListView) findViewById(R.id.menuList);
//        menuAdapter = new ArrayAdapter<RestaurantMenuItem> (this, android.R.layout.simple_list_item_1, menuItems);
//        menuList.setAdapter(menuAdapter);
//
//        menuItems.add(pasta);
        System.out.println("End");
    }
    public void returntoAdmin(View view){
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }

    public void addMenuItem(String name, int cost, int availability){
        int i;
        System.out.println("\n\n running add menu item \n");
        for(i = 0; i < 10; i++){
            if(pasta[i] == null){
                break;
            }
        }
        System.out.println("Before");
        int j = 0;
        while(pasta[j] != null){
            System.out.println("Pasta value : " + pasta[j].getItemName());
            j++;
        }

        if(i == 9){
            System.out.println("The menu is full! Cannot add any more items.");
        }
        else{
            pasta[i] = new RestaurantMenuItem(name, R.drawable.customer_view_background, cost, availability);
        }

        System.out.println("After");
        j = 0;
        while(pasta[j] != null){
            System.out.println("Pasta value : " + pasta[j].getItemName());
            j++;
        }

    }


}