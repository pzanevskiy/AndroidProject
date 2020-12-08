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

        TextView orderId=(TextView)view.findViewById(R.id.curOrderId);
        TextView complCarM=(TextView)view.findViewById(R.id.curOrderCarModel);
        TextView complCarB=(TextView)view.findViewById(R.id.curOrderCarBrand);
        TextView start=(TextView)view.findViewById(R.id.orderStart);
        TextView end=(TextView)view.findViewById(R.id.orderEnd);

        Order order= orders.get(position);
        orderId.setText(String.valueOf(order.getId()));
        complCarM.setText(order.getCar().getModel());
        complCarB.setText(order.getCar().getBrand());
        start.setText(order.getStartDate());
        end.setText(order.getEndDate());

        return view;
    }
}
