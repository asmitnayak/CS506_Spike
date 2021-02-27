package com.example.android.cs506_spike;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StaffMenu extends AppCompatActivity {
    ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    ArrayAdapter<RestaurantMenuItem> menuAdapter;
    CustomAdapter customAdpStaff;
    ListView menuList;
    public static ArrayList<RestaurantMenuItem> foodItems = new ArrayList<RestaurantMenuItem>();
    public static boolean first = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_menu);
        menuList = (ListView) findViewById(R.id.menuList);

        final File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        if (!file.exists())
            file.mkdir();
        // TODO: Change filename
        File mMenu = new File(file, "menu");
        System.out.println("2Size : " + foodItems.size());
        if (!mMenu.exists()) {
            try (FileOutputStream stream = new FileOutputStream(mMenu, false)) {
                String tempMenu = "Pasta:drawable/customer_view_background:19.85:9";
                stream.write((tempMenu + "\n").getBytes());
            } catch (Exception ignored) {
            }
        }

        int length = (int) mMenu.length();
        byte[] bytes = new byte[length];
        FileInputStream in = null;
        try {
            in = new FileInputStream(mMenu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.read(bytes);
        } catch (IOException ignored) {

        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(first == false) {
            String contents = new String(bytes);
            String array[] = contents.split("\n");
            for (String s : array) {
                String[] pieces = s.split(":");
                foodItems.add(new RestaurantMenuItem(pieces[0], getResources().getIdentifier(pieces[1], null, getPackageName()), Double.parseDouble(pieces[2]), Integer.parseInt(pieces[3])));
            }
            first = true;
        }

        customAdpStaff = new CustomAdapter(getApplicationContext(), foodItems);
        menuList.setAdapter(customAdpStaff);
    }
    public void changeAvailability(View view){
        // ArrayList<RestaurantMenuItem> selectedFoodItem = CustomAdapter.selectedFood;
        Intent intent = new Intent(this, ModifyAvailability.class);
        startActivity(intent);
    }

}
