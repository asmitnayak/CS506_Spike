package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PickupTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_time);
    }


    public void onDoneClicked(View view) {
        Intent intent = new Intent(this, CustomerView.class);
        startActivity(intent);
    }
}