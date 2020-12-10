package com.example.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Car;

import java.util.List;

public class CarsAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    List<Car> carList;

    public CarsAdapter(Context context, List<Car> carsList){
        ctx=context;
        this.carList=carsList;
        lInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.car_item, parent, false);
        }

        TextView brand=(TextView)view.findViewById(R.id.carBrand);
        TextView model=(TextView)view.findViewById(R.id.carModel);
        TextView price=(TextView)view.findViewById(R.id.carPrice);

        Car car=carList.get(position);

        brand.setText(car.getBrand());
        model.setText(car.getModel());
        price.setText(String.format("%.2f",car.getPrice())+'$');

        return view;
    }
}
