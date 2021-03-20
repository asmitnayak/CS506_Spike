package com.example.android.cs506_spike;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.android.cs506_spike.CreateAccount.accountDetails;
import static com.example.android.cs506_spike.StaffMenu.foodItems;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {


    LayoutInflater inflter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);
    }

    private String payType = "";

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.applePay:
                if (checked)
                    payType = "Apple Pay";
                    break;
            case R.id.paypal:
                if (checked)
                    payType = "PayPal";
                    break;
            case R.id.stripe:
                if (checked)
                    payType = "Stripe";
                    break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCheckoutBClicked(View view) {

        if (accountDetails[5].equalsIgnoreCase("N/A")) {
            final File folder = getFilesDir();
            File file = new File(folder, "cs506_spike");
            File mCreds = new File(file, "credentials");

            int length = (int) mCreds.length();
            byte[] bytes = new byte[length];
            FileInputStream in = null;
            try {
                in = new FileInputStream(mCreds);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                in.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String contents = new String(bytes);
            String[] array = contents.split("\n");
            ArrayList<String> lc = new ArrayList<>(Arrays.asList(array));

            String username = accountDetails[0];
            for(int i = 0; i < lc.size(); i++){
                String cred = lc.get(i);
                String[] pieces = cred.split(":");
                if (pieces[0].equalsIgnoreCase(username)){
                    lc.remove(i);
                    accountDetails[5] = payType;
                }
                lc.add(i, String.join(":", accountDetails));
            }
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(mCreds);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for(String s : lc) {
                try {
                    stream.write((s + "\n").getBytes());
                } catch (Exception e) {
                } finally {
                    // do nothing
                }
            }
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //createOrder();
        Intent intent = new Intent(this, PickupTime.class);
        startActivity(intent);


    }
    /*
    public void createOrder(){
        ArrayList<RestaurantMenuItem> customerOrder = CustomAdapter.selectedFood;
        ArrayList<RestaurantMenuItem> newOrder = new ArrayList<RestaurantMenuItem>(customerOrder);
        Orders.orders.add(new RestaurantOrder(Orders.ordNum++, newOrder, CalculateCost(newOrder)));
    }

    public Double CalculateCost(ArrayList<RestaurantMenuItem> orders){
        Double totalCost = 0.0;
        for (int i = 0; i < orders.size(); i++){
            totalCost += (orders.get(i).getItemCost()*Double.valueOf(orders.get(i).getQuantity()));
        }
        return totalCost;
    }
    */

}