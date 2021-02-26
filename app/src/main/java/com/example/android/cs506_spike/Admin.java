package com.example.android.cs506_spike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void adminAddItem(View view) {
        Intent intent = new Intent(this, AdminAddItem.class);
        startActivity(intent);
    }

    public void adminModifyMenu(View view) {
        Intent intent = new Intent(this, AdminModifyMenuList.class);
        startActivity(intent);
    }

    public void usageReports(View view){
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        goToAccount();
        return super.onOptionsItemSelected(item);
    }

    public void goToAccount(){
        Intent intent = new Intent(this, CustomerAccount.class);
        startActivity(intent);
    }
}