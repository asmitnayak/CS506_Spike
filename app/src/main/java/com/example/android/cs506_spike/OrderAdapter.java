package com.example.android.cs506_spike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    ArrayList<RestaurantOrder> ordersData;
    Context context;

    public OrderAdapter(Context ct, ArrayList<RestaurantOrder> orders){
        context = ct;
        ordersData = orders;

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.orders_recycle_view_layout, parent,  false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.orderNumTitle.setText("Order Number:");
        holder.orderNum.setText(Integer.toString(ordersData.get(position).getOrderNumber()));
        holder.deliveryTitle.setText("Delivery: ");
        holder.delivery.setText(ordersData.get(position).getDeliveryType());
        holder.costTitle.setText("Cost: ");
        holder.cost.setText(Double.toString(ordersData.get(position).getCost()));
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
        TextView orderNumTitle, orderNum, deliveryTitle, delivery, costTitle, cost, food1, food2;
        LinearLayout ordersLayout;
        Button finishedButton;
        RelativeLayout viewF,viewB;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumTitle = itemView.findViewById(R.id.Order_Num_Title);
            orderNum = itemView.findViewById(R.id.Order_Num);
            deliveryTitle = itemView.findViewById(R.id.Deliver_Type_Title);
            delivery = itemView.findViewById(R.id.Delivery_Type);
            costTitle = itemView.findViewById(R.id.Cost_Title);
            cost = itemView.findViewById(R.id.Order_Cost);
            ordersLayout = itemView.findViewById(R.id.Food_Items);
            food1 = itemView.findViewById(R.id.Food1);
            food2 = itemView.findViewById(R.id.Food2);
            finishedButton = itemView.findViewById(R.id.Order_Done_Button);
            viewF = itemView.findViewById(R.id.view_Foreground);
            viewB = itemView.findViewById(R.id.view_background);
        }
    }

    public void removeOrder(int position){
        ordersData.remove(position);

        //this will update recyclerview means refresh it
        notifyItemRemoved(position);
    }

    public void restoreOrder(RestaurantOrder order, int position){
        ordersData.add(position, order);

        notifyItemInserted(position);

    }
}
