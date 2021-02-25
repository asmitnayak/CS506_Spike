package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class AdminAddItem extends AppCompatActivity {

    private File master = null;
    private File mMenuList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        if(!file.exists())
            file.mkdir();

        master = file;
        mMenuList = new File(file, "menu_list");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);
    }

    public void returnToAdminPage(View view) {
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


//    public class MenuItemAdd extends AsyncTask<String, Void, Void>{
//
//        MenuItemAdd(){
//            if(mMenuList == null){
//                File folder = getFilesDir();
//                File file = new File(folder, "cs506_spike");
//                if(!file.exists())
//                    file.mkdir();
//                mMenuList = new File(file, "menu_list");
//            }
//        }
//
//        @Override
//        protected Void doInBackground(String... strings) {
//
//        }
//    }
}