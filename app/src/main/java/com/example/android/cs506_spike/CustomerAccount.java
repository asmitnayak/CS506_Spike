package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CustomerAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        TextView username = (TextView) findViewById(R.id.username);
        username.append(" " + CreateAccount.accountDetails[0]);
        TextView password = (TextView) findViewById(R.id.passwordHolder);
        password.append(" " + CreateAccount.accountDetails[1]);
        TextView address = (TextView) findViewById(R.id.addressHolder);
        address.append(" " + CreateAccount.accountDetails[3]);
        TextView phone = (TextView) findViewById(R.id.phoneHolder);
        phone.append(" " + CreateAccount.accountDetails[4]);
        TextView paym = (TextView) findViewById(R.id.paymentTyple);
        paym.append(" " + CreateAccount.accountDetails[5]);
    }
}