package com.example.android.cs506_spike;

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

import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {
    ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    ArrayAdapter<MenuItem> menuAdapter;
    ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        menuList = (ListView) findViewById(R.id.menuList);
        menuAdapter = new ArrayAdapter<MenuItem> (this, android.R.layout.simple_list_item_1, menuItems);
        menuList.setAdapter(menuAdapter);
        //menuItems.add();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appmenu, menu);
        return true;
    }
}