package com.example.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Order;

import java.util.List;

public class UserWithCompletedOrdersAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Order> orders;

    public UserWithCompletedOrdersAdapter(Context context, List<Order> orders){
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
            view = lInflater.inflate(R.layout.fragment_compl_order, parent, false);
        }
        TextView orderId=(TextView)view.findViewById(R.id.complOrderId);
        TextView curOrderCarB=(TextView)view.findViewById(R.id.complOrderCarBrand);
        TextView curOrderCarM=(TextView)view.findViewById(R.id.complOrderCarModel);
        TextView start=(TextView)view.findViewById(R.id.complOrderStart);
        TextView end=(TextView)view.findViewById(R.id.complOrderEnd);

        Order order= orders.get(position);

        orderId.setText(String.valueOf(order.getId()));
        curOrderCarB.setText(order.getCar().getBrand());
        curOrderCarM.setText(order.getCar().getModel());
        start.setText(order.getStartDate());
        end.setText(order.getEndDate());

        return view;
    }
}