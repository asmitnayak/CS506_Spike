package com.example.android.cs506_spike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;


public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<RestaurantMenuItem> food;
    public static ArrayList<RestaurantMenuItem> selectedFood = new ArrayList<RestaurantMenuItem>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList<RestaurantMenuItem> food) {
        this.context = context;
        this.food = food;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return food.size();
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
        nameV.setText(food.get(i).getItemName());
        ImageView img = (ImageView) view.findViewById(R.id.imageView2);
        img.setImageResource(food.get(i).getMenuImage());
        TextView costV = (TextView) view.findViewById(R.id.costView);
        costV.setText(String.format(Locale.getDefault(),"%2f",food.get(i).getItemCost()));
        TextView availV = (TextView) view.findViewById(R.id.AvailibilityView);
        availV.setText(String.format(Locale.getDefault(),"%d",food.get(i).getItemAvailibility()));

        Button p = (Button) view.findViewById(R.id.plus);
        Button m = (Button) view.findViewById(R.id.minus);
        TextView q = (TextView) view.findViewById(R.id.quantity);
        q.setText(String.format(Locale.getDefault(),"%d",food.get(i).getItemChoosen()));
        CheckBox cb = (CheckBox) view.findViewById(R.id.foodSelect);
        cb.setChecked(food.get(i).getIsChecked());
        p.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                int curr = Integer.parseInt((String) q.getText())+1;
                if (curr <= food.get(i).getItemAvailibility()) {
                    q.setText("" + curr);
                    food.get(i).setItemChoosen(curr);
                }
                if (curr > 0) {
                    cb.setChecked(true);
                    food.get(i).setIsChecked(true);
                }
                else {
                    cb.setChecked(false);
                    food.get(i).setIsChecked(false);
                }
            }
        });

        m.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                int curr = Integer.parseInt((String) q.getText())-1;
                if (curr >= 0) {
                    q.setText("" + curr);
                    food.get(i).setItemChoosen(curr);
                    food.get(i).setIsChecked(true);
                }
                if (curr == 0) {
                    cb.setChecked(false);
                    food.get(i).setIsChecked(false);
                }
            }
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    q.setText("1");
                    food.get(i).setItemChoosen(1);
                    food.get(i).setIsChecked(true);
                    selectedFood.add(food.get(i));
                }else{
                    q.setText("0");
                    food.get(i).setItemChoosen(0);
                    food.get(i).setIsChecked(false);
                    selectedFood.remove(food.get(i));
                }
            }
        });

        return view;
    }
}
