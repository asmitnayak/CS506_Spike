package com.example.android.cs506_spike;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Orders extends AppCompatActivity implements CallBackOrderTouch{
    private ArrayList<RestaurantOrder> orders = new ArrayList<RestaurantOrder>();
    private RecyclerView ordersList;
    private OrderAdapter orderAdp;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        layout = findViewById(R.id.RLM);
        ordersList = (RecyclerView) findViewById(R.id.ordersList);
        ArrayList<RestaurantOrder> pastaOrder = new ArrayList<RestaurantOrder>();
        ArrayList<RestaurantMenuItem> orderItems = new ArrayList<RestaurantMenuItem>();
        orderItems.add(new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9));
        pastaOrder.add(new RestaurantOrder(1, "Dine In", orderItems, 19.99));
        pastaOrder.add(new RestaurantOrder(2, "Take Out", orderItems, 8.99));
        orderAdp = new OrderAdapter(this, pastaOrder);
        ordersList.setAdapter(orderAdp);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new orderItemTouchHelperCallBack(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(ordersList);
    }


    @Override
    public void orderTouchOnMove(int oldPosition, int newPosition) {
        orders.add(newPosition,orders.remove(oldPosition));
        orderAdp.notifyItemMoved(oldPosition,newPosition);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {
        // delete order and undo
        String orderNum = Integer.toString(orders.get(viewHolder.getAdapterPosition()).getOrderNumber());

        //back up or removed order for undo
        final RestaurantOrder deletedOrder = orders.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        //remove item for recyclerview
        orderAdp.removeOrder(viewHolder.getAdapterPosition());

        Snackbar snackbar = Snackbar.make(layout, orderNum+" removed", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAdp.restoreOrder(deletedOrder,deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
    }
}