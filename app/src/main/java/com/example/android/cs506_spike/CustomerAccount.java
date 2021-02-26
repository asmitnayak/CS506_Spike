package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CustomerAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        TextView tv = (TextView) findViewById(R.id.username);
        tv.append(" "+CreateAccount.accountDetails[0]);
    }
}