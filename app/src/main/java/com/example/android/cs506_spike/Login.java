package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText usernameIn = findViewById(R.id.usernameInput);
        String username = usernameIn.getText().toString();
        EditText passwordIn = findViewById(R.id.passwordInput);
        String password = passwordIn.getText().toString();

        // Check for valid input.
        if (username != null && !username.equals("") && password != null && !password.equals("")) {
            // Check if login credentials matches.
            check(username, password);
        } else {
            Toast.makeText(Login.this, "Invalid input. Please try again!", Toast.LENGTH_LONG).show();
        }
    }
    public void check(final String username, final String password) {
        //some checking credentials here
        goToCustomerView(username);

    }
    // Redirect the user to main screen.
    public void goToCustomerView(String username) {
        // Redirect the user to main screen.
        Intent intent = new Intent(this, CustomerView.class);
        startActivity(intent);
    }
    public void goToCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
}