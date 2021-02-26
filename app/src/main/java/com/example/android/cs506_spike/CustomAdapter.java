package com.example.android.cs506_spike;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class CustomAdapter extends BaseAdapter {
    Context context;
    RestaurantMenuItem []food;
    public static ArrayList<RestaurantMenuItem> selectedStrings = new ArrayList<RestaurantMenuItem>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, RestaurantMenuItem [] food) {
        this.context = context;
        this.food = food;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.menu_list_view_layout, null);

        TextView nameV = (TextView) view.findViewById(R.id.nameView);
        nameV.setText(food[i].getItemName());
        ImageView img = (ImageView) view.findViewById(R.id.imageView2);
        img.setImageResource(food[i].getMenuImage());
        TextView costV = (TextView) view.findViewById(R.id.costView);
        costV.setText(String.format(Locale.getDefault(),"%f",food[i].getItemCost()));
        TextView availV = (TextView) view.findViewById(R.id.AvailibilityView);
        availV.setText(String.format(Locale.getDefault(),"%d",food[i].getItemAvailibility()));

        CheckBox cb = (CheckBox) view.findViewById(R.id.foodSelect);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedStrings.add(food[i]);
                }else{
                    selectedStrings.remove(food[i]);
                }
            }
        });

        return view;
    }

    public ArrayList<RestaurantMenuItem> getSelectedStrings(){
        return this.selectedStrings;
    }
}
