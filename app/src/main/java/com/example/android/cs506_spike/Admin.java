package com.example.android.cs506_spike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.IOException;

import static com.example.android.cs506_spike.AdminModifyMenuList.foodItems;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void adminAddItem(View view) {
        Intent intent = new Intent(this, AdminAddItem.class);
        startActivity(intent);
    }

    public void adminModifyMenu(View view) {
        Intent intent = new Intent(this, AdminModifyMenuList.class);
        startActivity(intent);
    }

    public void printUsageReport(View view){
        // print the usage report and put it into a pdf with all the necessary information

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        goToAccount();
        return super.onOptionsItemSelected(item);
    }

    public void goToAccount(){
        Intent intent = new Intent(this, CustomerAccount.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO: Update Menu
        File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        Menu m = new Menu(file);

        for(RestaurantMenuItem rmi: foodItems){
            try {
                m.write_menu(rmi.getItemName(), rmi.getMenuImage(), rmi.getItemCost(), rmi.getItemAvailibility());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}