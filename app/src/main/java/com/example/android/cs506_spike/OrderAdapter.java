package com.example.android.cs506_spike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{



    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    ArrayList<RestaurantOrder> ordersData;

    public OrderAdapter( ArrayList<RestaurantOrder> orders){
        ordersData = orders;

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_recycle_view_layout, parent,  false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        RestaurantOrder order = ordersData.get(position);
        holder.orderNum.setText("Order Number: " + Integer.toString(ordersData.get(position).getOrderNumber()));
        holder.cost.setText("Total Cost: $" + Double.toString(ordersData.get(position).getCost()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.foodItemRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(order.getMenuItems().size());

        OrdersSubItemAdapter ordersSubItemAdapter = new OrdersSubItemAdapter(order.getMenuItems());

        holder.foodItemRecyclerView.setLayoutManager(layoutManager);
        holder.foodItemRecyclerView.setAdapter(ordersSubItemAdapter);
        holder.foodItemRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        if(ordersData==null){
            return 0;
        }else {
            return ordersData.size();
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView orderNum, cost;
        LinearLayout ordersLayout;
        RelativeLayout viewF,viewB;
        RecyclerView foodItemRecyclerView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            //typecast
            orderNum = itemView.findViewById(R.id.Order_Num);
            cost = itemView.findViewById(R.id.Cost);
            ordersLayout = itemView.findViewById(R.id.Food_Items);
            viewF = itemView.findViewById(R.id.view_Foreground);
            viewB = itemView.findViewById(R.id.view_background);
            foodItemRecyclerView = itemView.findViewById(R.id.Food_Item_Recycler_View);
        }
    }

    public void removeOrder(int position){
        ordersData.remove(position);

        //this will update recyclerview and refresh it
        notifyItemRemoved(position);
    }

    public void restoreOrder(RestaurantOrder order, int position){
        ordersData.add(position, order);

        notifyItemInserted(position);

    }
}
