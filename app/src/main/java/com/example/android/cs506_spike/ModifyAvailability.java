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

public class  ModifyAvailability extends StaffMenu{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_availability);
    }

    public void returnToStaffMenu(View view) {
        EditText availabilityIn = findViewById(R.id.itemAvailability);
        String availabilityString = availabilityIn.getText().toString();
        int availability = Integer.parseInt(availabilityString);
        //int i = foodItems.indexOf(selectedFoodItem.get(0));
       // foodItems.set(i,)

        Intent intent = new Intent(this, StaffMenu.class);
        startActivity(intent);
    }



}