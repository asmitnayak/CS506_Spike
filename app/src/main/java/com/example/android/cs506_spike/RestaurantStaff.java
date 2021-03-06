package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class RestaurantStaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_staff);
    }

    // Redirect the user to Orders.
    public void goToOrders(View view) {
        // Redirect the user to Orders Screen.
        Intent intent = new Intent(this, Orders.class);
        startActivity(intent);
    }
    public void goToStaffMenu(View view){
        Intent intent = new Intent(this, StaffMenu.class);
        startActivity(intent);
    }
}