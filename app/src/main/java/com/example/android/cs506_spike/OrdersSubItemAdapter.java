package com.example.android.cs506_spike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersSubItemAdapter extends RecyclerView.Adapter<OrdersSubItemAdapter.OrdersSubItemViewHolder>{

    ArrayList<RestaurantMenuItem> menuItems;

    public OrdersSubItemAdapter(ArrayList<RestaurantMenuItem> menuItems){
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public OrdersSubItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_subitem_layout, parent,  false);
        return new OrdersSubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersSubItemViewHolder holder, int position) {
        RestaurantMenuItem food = menuItems.get(position);
        holder.foodItem.setText(food.getItemName());
        holder.quantity.setText("Quantity: " + Integer.toString(food.getQuantity()));
        holder.itemCost.setText(Double.toString((food.getItemCost())*Double.valueOf(food.getQuantity())));
        //holder.itemCost.setText(Double.toString(food.getItemCost()));
    }

    @Override
    public int getItemCount() {

            return menuItems.size();
    }

    public class OrdersSubItemViewHolder extends RecyclerView.ViewHolder{
        TextView foodItem, quantity, itemCost;


        public OrdersSubItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //typecast
            foodItem = itemView.findViewById(R.id.Food_Item);
            quantity = itemView.findViewById(R.id.Quantity);
            itemCost = itemView.findViewById(R.id.Item_Cost);

        }
    }

}
