package com.example.android.cs506_spike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Cart extends AppCompatActivity {

    ListView ordersList;
    CustomAdapter customAdp;
    public static int cost = 0;
    private File folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ordersList = (ListView) findViewById(R.id.orders);
        customAdp = new CustomAdapter(getApplicationContext(), CustomAdapter.selectedFood);
        ordersList.setAdapter(customAdp);
        Button checkoutB = (Button) findViewById(R.id.checkoutButton);
        checkoutB.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int c = 0;
                for(RestaurantMenuItem rmi : CustomAdapter.selectedFood){
                    double price = rmi.getItemCost();
                    int quant = rmi.getItemChoosen();
                    c += price*quant;
                }
                cost = c;
            }

        });
    }
//
//    public void saveOrders(String data, Context context){
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("customer_order.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
//        }
//        catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back) {
            goToMenu();
        } else if(item.getItemId() == R.id.checkout){
            goToCheckout();
        }

        return super.onOptionsItemSelected(item);
    }
    public void goToMenu(){
        Intent intent = new Intent(this, CustomerView.class);
        startActivity(intent);
    }
    public void pdfCreator(String data) {
        //String storageDirectory = Environment.getExternalStorageDirectory().toString();
        File storageDirectory = getFilesDir();
//        File fol = new File(storageDirectory, "cs506_spike");
        folder = new File(storageDirectory, "cs506_spike");
        if (!folder.exists()) {
            boolean bool = folder.mkdir();
        }
        try {
            final File file = new File(folder, "order_receipt.pdf");
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
            for (String line: data.split("\n")) {
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
    public void goToCheckout(){
        String str = "";
        Double total = 0.0;
        for (RestaurantMenuItem m : CustomAdapter.selectedFood){
            if(m.getItemChoosen() > 1){
                for(int i = 1; i <= m.getItemChoosen(); i++){
                    str += m.getItemName() + " $" + m.getItemCost() + "\n";
                    total+= m.getItemCost();
                }
            }else {
                str += m.getItemName() + " $" + m.getItemCost() + "\n";
                total+= m.getItemCost();
            }

        }
        str += "Total: " + total + "\n\n";
        pdfCreator(str);
        Intent intent = new Intent(this, Checkout.class);
        startActivity(intent);
    }

    public void onCheckoutClicked(View view) {
        goToCheckout();
    }
}