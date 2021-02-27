package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class AdminAddItem extends AdminModifyMenuList {

    private File master = null;
    private File mMenuList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        if(!file.exists())
            file.mkdir();

        master = file;
        mMenuList = new File(file, "menu_list");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);
    }

    public void addMenuItem(String name, double cost, int availability){
        RestaurantMenuItem addItem = new RestaurantMenuItem(name, 0, cost, availability);
        foodItems.add(addItem);
    }

    public void returnToAdminPage(View view) {
        System.out.println("\n\n running return to admin page \n");
        EditText nameIn = (EditText) findViewById(R.id.menuName);
        String name = nameIn.getText().toString();

        EditText priceIn = (EditText) findViewById(R.id.menuCost);
        String priceString = priceIn.getText().toString();
        double price = Double.parseDouble(priceString);

        EditText inStockIn = (EditText) findViewById(R.id.menuAvailability);
        String inStockString = inStockIn.getText().toString();
        int inStock = Integer.parseInt(inStockString);

        addMenuItem(name, price, inStock);

        finish();
    }

}