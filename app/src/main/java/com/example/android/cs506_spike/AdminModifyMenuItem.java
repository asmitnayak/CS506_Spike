package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminModifyMenuItem extends AdminModifyMenuList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modify_menu_item);
    }

    public void returnToMenuPage(View view) {
        EditText usernameIn = findViewById(R.id.itemName);
        String username = usernameIn.getText().toString();

        EditText costIn = findViewById(R.id.itemCost);
        String costString = costIn.getText().toString();
        int cost =  Integer.parseInt(costString);

        EditText availabilityIn = findViewById(R.id.itemAvailability);
        String availabilityString = availabilityIn.getText().toString();
        int availability = Integer.parseInt(availabilityString);

        RestaurantMenuItem addItem = new RestaurantMenuItem(username, 0, cost, availability);

        ArrayList<RestaurantMenuItem> selectedFoodItem = CustomAdapter.selectedFood;

        int i = foodItems.indexOf(selectedFoodItem.get(0));
        foodItems.set(i, addItem);

        Intent intent = new Intent(this, AdminModifyMenuList.class);
        startActivity(intent);


    }



}