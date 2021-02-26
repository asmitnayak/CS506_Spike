package com.example.android.cs506_spike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private File mMenu = null;
    private File masterMenu = null;
    protected static final ArrayList<String> menuItems = new ArrayList<>();
    private FileWriter writer = null;
    private FileReader reader = null;
    private EditText mMenuItemName;
    private EditText mMenuItemPhoto;
    private EditText mMenuItemCost;
    private EditText mMenuItemAvailability;
    //private EditText mPhoneNumberView;
    //private Spinner mRoleSpinner;
    private Menu.UserLoginTask mAuthTask;

    public static String[] menuItemDetails = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        final File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        if (!file.exists())
            file.mkdir();

        masterMenu = file;
        mMenu = new File(file, "menu");
        mAuthTask = new Menu.UserLoginTask();
        //mMenuItemName = findViewById(R.id.nameView);
        mMenuItemPhoto = findViewById(R.id.passwordInput);
        mMenuItemCost = findViewById(R.id.passwordInputConfirm);
        mMenuItemAvailability = findViewById(R.id.address);
        //mRoleSpinner = spinner;

        /*Button mRegisterButton = (Button) findViewById(R.id.registerLink);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mAuthTask.write_menu())
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
        });*/

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
                writer = new FileWriter(mMenu, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                reader = new FileReader(mMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                read_menu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            if(menuItems.isEmpty())
                return false;

            for (String credential : menuItems) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUser)) {
                    // Account exists, return true if the password matches.
                    if (pieces[1].equals(mPassword))
                        System.arraycopy(pieces, 0, menuItemDetails, 0, 5);
                    return  pieces[1].equals(mPassword);
                }
            }

            return false;
        }



        public ArrayList<String> read_menu() throws IOException {

            int length = (int) mMenu.length();

            byte[] bytes = new byte[length];

            FileInputStream in = new FileInputStream(mMenu);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }

            String contents = new String(bytes);

            String array[] = contents.split("\n");
            for (String s : array)
                menuItems.add(s);

            return menuItems;
        }

        private boolean write_menu() throws IOException {
            boolean success;


            FileOutputStream stream = new FileOutputStream(mMenu, true);
            String str = mMenuItemName.getText().toString().trim() + ":" + mMenuItemPhoto.getText().toString().trim() +
                    ":" + mMenuItemCost.getText().toString().trim() + ":" + mMenuItemAvailability.getText().toString().trim();
            if (str.equals("::"))
                return false;
            try {
                stream.write((str + "\n").getBytes());
                success = true;
                menuItems.add(str);
                String[] pieces = str.split(":");
                System.arraycopy(pieces, 0, menuItemDetails, 0, 5);
            } catch (Exception e) {
                success = false;
            } finally {
                stream.close();
            }


            return success;
        }
    }
}

