package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminModifyMenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modify_menu_list);
    }

    public void returnToAdmin(View view) {
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
        /*
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
        }*/
    }

}