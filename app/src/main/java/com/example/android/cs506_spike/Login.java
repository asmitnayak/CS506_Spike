package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private File mCreds = null;
    protected static final ArrayList<String> LOGINCREDENTIALS = new ArrayList<>();
    private String mRole = "";
    private Authorization auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText usernameIn = findViewById(R.id.carDescription);
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
        auth = new Authorization(username, password);
        System.out.println("here");
        if (auth.doInBackground()){
            // login based on role
            if(mRole.equalsIgnoreCase("Customer")) {
                goToCustomerView(username);
            }
            else if (mRole.equalsIgnoreCase("Staff")){
                goToStaffView(username);
            }
            else if (mRole.equalsIgnoreCase("Admin")){
                goToAdminView(username);
            }
        }

    }
    // Redirect the user to main screen.
    public void goToCustomerView(String username) {
        // Redirect the user to main screen.
        Intent intent = new Intent(this, CustomerView.class);
        startActivity(intent);
    }

    public void goToStaffView(String username) {
        // Redirect the user to main screen.
        //Intent intent = new Intent(this, StaffView.class);
        //startActivity(intent);
    }

    public void goToAdminView(String username) {
        // Redirect the user to main screen.
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
    public void goToCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public class Authorization extends AsyncTask<Void, Void, Boolean> {

        private String mUser = "";
        private String mPassword = "";
        Authorization(String uName, String pass){

            mUser = uName;
            mPassword = pass;

            final File folder = getFilesDir();
            File file = new File(folder, "cs506_spike");
            if(!file.exists())
                file.mkdir();
            mCreds = new File(file, "credentials");
            try{
                read_credentials();
            }catch (IOException ioe){
                // do nothing
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if(LOGINCREDENTIALS.isEmpty())
                return false;

            for (String credential : LOGINCREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUser)) {
                    // Account exists, return true if the password matches.
                    if (pieces[1].equals(mPassword)){
                        mRole = pieces[2];
                        return true;
                    }
                    else
                        return false;
                }
            }

            return false;
        }

        public ArrayList<String> read_credentials() throws IOException {

            int length = (int) mCreds.length();

            byte[] bytes = new byte[length];

            FileInputStream in = new FileInputStream(mCreds);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }

            String contents = new String(bytes);

            String array[] = contents.split("\n");
            for(String s : array)
                LOGINCREDENTIALS.add(s);

            return LOGINCREDENTIALS;
        }
    }
}