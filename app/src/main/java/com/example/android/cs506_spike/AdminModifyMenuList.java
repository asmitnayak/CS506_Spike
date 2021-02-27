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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AdminModifyMenuList extends AppCompatActivity {
    ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();
    ArrayAdapter<RestaurantMenuItem> menuAdapter;
    CustomAdapter customAdp;
    ListView menuList;
    public static ArrayList<RestaurantMenuItem> foodItems = new ArrayList<RestaurantMenuItem>();
    public static boolean first = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_view);
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
                String tempMenu = "Pasta:0:19.85:9";
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
//        if(first == false) {
//            String contents = new String(bytes);
//            String array[] = contents.split("\n");
//            for (String s : array) {
//                String[] pieces = s.split(":");
//                foodItems.add(new RestaurantMenuItem(pieces[0], getResources().getIdentifier(pieces[1], null, getPackageName()), Double.parseDouble(pieces[2]), Integer.parseInt(pieces[3])));
//            }
//            first = true;
//        }

        customAdp = new CustomAdapter(getApplicationContext(), foodItems);
        menuList.setAdapter(customAdp);
    }
    public void returntoAdmin(View view){
        finish();
    }

    public void goToMenuItem(View view){
        ArrayList<RestaurantMenuItem> selectedFoodItem = CustomAdapter.selectedFood;

      //  EditText et = (EditText)findViewById(R.id.itemName);

        Intent intent = new Intent(this, AdminModifyMenuItem.class);
        startActivity(intent);
    }



}