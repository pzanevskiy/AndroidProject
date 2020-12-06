package com.example.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.relations.UserWithOrders;
import com.example.rentalcar.viewmodel.OrderViewModel;

import java.util.List;

public class UserWithOrdersAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Order> orders;

    public UserWithOrdersAdapter(Context context, List<Order> orders){
        ctx=context;
        this.orders = orders;
        lInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.order_item, parent, false);
        }

        TextView carId=(TextView)view.findViewById(R.id.orderCarId);
        TextView userId=(TextView)view.findViewById(R.id.orderUserId);
        TextView duration=(TextView)view.findViewById(R.id.orderDuration);
        TextView start=(TextView)view.findViewById(R.id.orderStart);
        TextView end=(TextView)view.findViewById(R.id.orderEnd);

        Order order= orders.get(position);
        carId.setText(String.valueOf(order.getCar().getId()));
        userId.setText(String.valueOf(order.getUserId()));
        duration.setText(String.valueOf(order.getDuration()));
        start.setText(order.getStartDate());
        end.setText(order.getEndDate());

        return view;
    }
}
