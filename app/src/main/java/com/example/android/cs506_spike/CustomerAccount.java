package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerAccount extends AppCompatActivity {

   //private String userName = CreateAccount.accountDetails[0];
   private TextView mUsernameView;
   private TextView mPasswordView;
   private TextView mPasswordConfView;
   private TextView mAddressView;
   private TextView mPhoneNumberView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);
//        mUsernameView = findViewById(R.id.username);
//        mUsernameView.setText(CreateAccount.accountDetails[0]);

    }
}