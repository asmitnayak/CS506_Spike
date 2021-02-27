package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateAccount extends AppCompatActivity {

    private File mCreds = null;
    private File master = null;
    protected static final ArrayList<String> CREDENTIALS = new ArrayList<>();
    private FileWriter writer = null;
    private FileReader reader = null;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mPasswordConfView;
    private EditText mAddressView;
    private EditText mPhoneNumberView;
    private Spinner mRoleSpinner;
    private UserLoginTask mAuthTask;

    public static String[] accountDetails = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Spinner spinner = findViewById(R.id.role);
        ArrayList<String> roleList = new ArrayList<>();
        roleList.add("Customer");
        roleList.add("Staff");
        roleList.add("Admin");
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roleList);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(listAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String tutorial = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected:" + tutorial, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


        final File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        if(!file.exists())
            file.mkdir();

        master = file;
        mCreds = new File(file, "credentials");
        mAuthTask = new UserLoginTask();
        mUsernameView = findViewById(R.id.carDescription);
        mPasswordView = findViewById(R.id.passwordInput);
        mPasswordConfView = findViewById(R.id.passwordInputConfirm);
        mAddressView = findViewById(R.id.address);
        mPhoneNumberView = findViewById(R.id.phoneNumber);
        mRoleSpinner = spinner;

        Button mRegisterButton = (Button) findViewById(R.id.registerLink);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mAuthTask.write_credentials())
                        finish();
                        // TODO: go to login this line is error generating
                        if (mRoleSpinner.getSelectedItem().toString().equalsIgnoreCase("Customer")){
                            goToCustomer();
                        }
                        else if (mRoleSpinner.getSelectedItem().toString().equalsIgnoreCase("Staff")){
                            goToStaff();
                        }
                        else if (mRoleSpinner.getSelectedItem().toString().equalsIgnoreCase("Admin")){
                            goToAdmin();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void goToCustomer(){
        Intent intent = new Intent(this, CustomerView.class);
        startActivity(intent);
    }

    public void goToStaff(){
        Intent intent = new Intent(this, RestaurantStaff.class);
        startActivity(intent);
    }

    public void goToAdmin(){
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }

    public void goToPage(View view) {
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private String mUser;
        private String mPassword;

        UserLoginTask(String user, String password) {
            mUser = user;
            mPassword = password;
        }
        UserLoginTask() {
            mUser = "";
            mPassword = "";
            try {
                writer = new FileWriter(mCreds, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                reader = new FileReader(mCreds);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                read_credentials();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void setUsernamePassword(String user, String pass){
            mUser = user;
            mPassword = pass;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            if(CREDENTIALS.isEmpty())
                return false;

            for (String credential : CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUser)) {
                    // Account exists, return true if the password matches.
                    if (pieces[1].equals(mPassword))
                        System.arraycopy(pieces, 0, accountDetails, 0, 6);
                    return  pieces[1].equals(mPassword);
                }
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
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
                CREDENTIALS.add(s);

            return CREDENTIALS;
        }

        private boolean write_credentials() throws IOException {
            boolean success;

            if(CREDENTIALS.contains(mUsernameView.getText().toString())){
                mUsernameView.setError("The username already exists");
                return false;
            }

            FileOutputStream stream = new FileOutputStream(mCreds, true);
            String str = mUsernameView.getText().toString().trim() + ":" + mPasswordView.getText().toString().trim() +
                    ":" + mRoleSpinner.getSelectedItem().toString() + ":" + mAddressView.getText().toString().trim()
                    + ":" + mPhoneNumberView.getText().toString().trim() + ":" + "N/A";
            if(str.equals(":::::"))
                return false;
            try {
                stream.write((str + "\n").getBytes());
                success = true;
                CREDENTIALS.add(str);
                String[] pieces = str.split(":");
                System.arraycopy(pieces, 0, accountDetails, 0, 6);
            } catch(Exception e){
                success = false;
            } finally {
                stream.close();
            }

            File f = new File(master, mUsernameView.getText().toString());
            f.mkdir();

            return success;
        }
    }

}