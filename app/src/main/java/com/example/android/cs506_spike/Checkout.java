package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void onCheckboxClicked(View view) {

    }

    public void onCheckoutClicked(View view) {
        Intent intent = new Intent(this, PickupTime.class);
        startActivity(intent);
    }
}