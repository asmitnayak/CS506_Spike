package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Checkout extends AppCompatActivity {

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

    public void onCheckoutBClicked(View view) {
        Intent intent = new Intent(this, PickupTime.class);
        startActivity(intent);
    }
}