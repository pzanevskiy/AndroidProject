package com.example.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.relations.UserWithOrders;

import java.util.List;

public class UserWithOrdersAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<UserWithOrders> userWithOrders;

    public UserWithOrdersAdapter(Context context, List<UserWithOrders> userWithOrders){
        ctx=context;
        this.userWithOrders=userWithOrders;
        lInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userWithOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return userWithOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
