package com.example.android.cs506_spike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.android.cs506_spike.AdminModifyMenuList.foodItems;

public class Admin extends AppCompatActivity {
    private File folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        Menu m = new Menu(file);
        try {
            foodItems = m.read_menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adminAddItem(View view) {
        Intent intent = new Intent(this, AdminAddItem.class);
        startActivity(intent);
    }

    public void adminModifyMenu(View view) {
        Intent intent = new Intent(this, AdminModifyMenuList.class);
        startActivity(intent);
    }


    public void producePdf(String data){
        File storageDirectory = getFilesDir();
        folder = new File(storageDirectory, "cs506_spike");
        if (!folder.exists()) {
            boolean bool = folder.mkdir();
        }
        try {
            final File file = new File(folder, "usage_report.pdf");
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);


            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new
                    PdfDocument.PageInfo.Builder(200, 200, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setStrokeWidth(1f);

            int x = 10, y = 10;
            for (String line : data.split("\n")) {
                canvas.drawText(line, x, y, paint);
                y += paint.descent() - paint.ascent();
            }


            document.finishPage(page);
            document.writeTo(fOut);
            document.close();

        } catch (IOException e) {
            Log.i("error", e.getLocalizedMessage());
        }
    }

    public void printUsageReport() {
        ArrayList<RestaurantMenuItem> items = foodItems;

        String str = "";
        for(int i = 0; i < foodItems.size(); i++){
            str += foodItems.get(i).getItemName() + " ";
            str += "$" +(foodItems.get(i).getItemCost()) + " ";
            str += "Availability : " + foodItems.get(i).getItemAvailibility() + "\n";
        }

        producePdf(str);
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

    @Override
    public void onBackPressed() {
        // TODO: Update Menu
        File folder = getFilesDir();
        File file = new File(folder, "cs506_spike");
        Menu m = new Menu(file);

        for(RestaurantMenuItem rmi: foodItems){
            try {
                m.write_menu(rmi.getItemName(), rmi.getMenuImage(), rmi.getItemCost(), rmi.getItemAvailibility());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onBackPressed();
    }
}