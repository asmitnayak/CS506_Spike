package com.example.android.cs506_spike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Cart extends AppCompatActivity {

    ListView ordersList;
    CustomAdapter customAdp;
    public static int cost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ordersList = (ListView) findViewById(R.id.orders);
        customAdp = new CustomAdapter(getApplicationContext(), CustomAdapter.selectedFood);
        ordersList.setAdapter(customAdp);
        Button checkoutB = (Button) findViewById(R.id.checkoutButton);
        checkoutB.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int c = 0;
                for(RestaurantMenuItem rmi : CustomAdapter.selectedFood){
                    double price = rmi.getItemCost();
                    int quant = rmi.getItemChoosen();
                    c += price*quant;
                }
                cost = c;
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back) {
            goToMenu();
        }else if(item.getItemId() == R.id.checkout){
            goToCheckout();
        }

        return super.onOptionsItemSelected(item);
    }
    public void goToMenu(){
        Intent intent = new Intent(this, CustomerView.class);
        startActivity(intent);
    }
    public void goToCheckout(){
        Intent intent = new Intent(this, Checkout.class);
        startActivity(intent);
    }
}