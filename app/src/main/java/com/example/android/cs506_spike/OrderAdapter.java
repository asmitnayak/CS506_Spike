package com.example.android.cs506_spike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    ArrayList<RestaurantOrder> ordersData;
    //Context context;

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
        holder.orderNum.setText("Order Number: " + Integer.toString(ordersData.get(position).getOrderNumber()));
        holder.delivery.setText("Delivery: " + ordersData.get(position).getDeliveryType());
        holder.cost.setText("Total Cost: " + Double.toString(ordersData.get(position).getCost()));
        holder.food1.setText("Food1");
        holder.food2.setText("Food2");
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
        TextView orderNum, delivery, cost, food1, food2;
        LinearLayout ordersLayout;
        RelativeLayout viewF,viewB;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            //typecast
            orderNum = itemView.findViewById(R.id.Order_Num);
            delivery = itemView.findViewById(R.id.Deliver_Type);
            cost = itemView.findViewById(R.id.Cost);
            ordersLayout = itemView.findViewById(R.id.Food_Items);
            food1 = itemView.findViewById(R.id.Food1);
            food2 = itemView.findViewById(R.id.Food2);
            viewF = itemView.findViewById(R.id.view_Foreground);
            viewB = itemView.findViewById(R.id.view_background);
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
