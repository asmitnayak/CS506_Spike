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
    public static ArrayList<RestaurantOrder> orders = new ArrayList<RestaurantOrder>();
    private RecyclerView ordersList;
    private OrderAdapter orderAdp;
    RelativeLayout layout;
    public static int ordNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        layout = findViewById(R.id.RLM);
        ordersList = (RecyclerView) findViewById(R.id.ordersList);
        initOrders();

    }

    private void initOrders() {
        ArrayList<RestaurantMenuItem> orderItem1 = new ArrayList<RestaurantMenuItem>();
        orderItem1.add(new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9));
        orderItem1.get(0).setQuantity(2);
        ArrayList<RestaurantMenuItem> orderItem2 = new ArrayList<RestaurantMenuItem>();
        orderItem2.add(new RestaurantMenuItem("Pizza", R.drawable.customer_view_background, 9.99, 9));
        orderItem2.add(new RestaurantMenuItem("Pasta", R.drawable.customer_view_background, 19.99, 9));
        orderItem2.get(0).setQuantity(3);
        orderItem2.get(1).setQuantity(1);
        orders.add(new RestaurantOrder(1, orderItem1, 39.98));
        orders.add(new RestaurantOrder(2, orderItem2, 49.96));


        //setup recycler view
        ordersList.setLayoutManager(new LinearLayoutManager(this));
        orderAdp = new OrderAdapter(orders);
        ordersList.setAdapter(orderAdp);
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