package com.example.android.cs506_spike;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends AsyncTask<Void, Void, Boolean> {

    File mMenu = null;

    Menu(File fileDir) {
        mMenu = new File(fileDir, "menu");
        try {
            read_menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        return false;
    }

    public ArrayList<RestaurantMenuItem> read_menu() throws IOException {

        int length = (int) mMenu.length();

        byte[] bytes = new byte[length];

        FileInputStream in = new FileInputStream(mMenu);
        try {
            in.read(bytes);
        } finally {
            in.close();
        }

        String contents = new String(bytes);
        ArrayList<RestaurantMenuItem> menuItems = new ArrayList<>();
        String array[] = contents.split("\n");
        for(String s : array){
            String[] pieces = s.split(":");
            menuItems.add(new RestaurantMenuItem(pieces[0], Integer.parseInt(pieces[1]), Double.parseDouble(pieces[2]), Integer.parseInt(pieces[3])));
        }
        return menuItems;
    }

    public boolean write_menu(String name, int pic, double cost, int avail) throws IOException {
        boolean success;

        FileOutputStream stream = new FileOutputStream(mMenu, false);
        String str = name + ":" + pic + ":" + cost + ":" + avail;
        if (str.equals(":::"))
            return false;
        try {
            stream.write((str + "\n").getBytes());
            success = true;
        } catch (Exception e) {
            success = false;
        } finally {
            stream.close();
        }

        return success;
    }
}
