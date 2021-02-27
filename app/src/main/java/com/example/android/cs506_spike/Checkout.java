package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
    Orders restaurantOrders;
    CustomAdapter customAdp;

    LayoutInflater inflter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.applePay:
                if (checked)

                    break;
            case R.id.paypal:
                if (checked)

                    break;
            case R.id.stripe:
                if (checked)

                    break;
        }
    }

    public void onCheckoutClicked(View view) {
        Intent intent = new Intent(this, PickupTime.class);
        createOrder();
        startActivity(intent);


    }

    public void createOrder(){
        ArrayList<RestaurantMenuItem> customerOrder = customAdp.selectedFood;
        ArrayList<RestaurantMenuItem> newOrder = new ArrayList<RestaurantMenuItem>(customerOrder);
        restaurantOrders.orders.add(new RestaurantOrder(restaurantOrders.ordNum++, newOrder, CalculateCost(newOrder)));
    }

    public Double CalculateCost(ArrayList<RestaurantMenuItem> orders){
        Double totalCost = 0.0;
        for (int i = 0; i < orders.size(); i++){
            totalCost += (orders.get(i).getItemCost()*Double.valueOf(orders.get(i).getQuantity()));
        }
        return totalCost;
    }
}