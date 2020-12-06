package com.example.rentalcar.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.viewmodel.OrderViewModel;

public class CurrentOrderActivity extends AppCompatActivity {

    Bundle args;
    int userId;
    Car car;
    Order order;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        Button button=(Button)findViewById(R.id.buttonComplete);
        button.setOnClickListener(onClickListener);
        orderViewModel=new ViewModelProvider(this).get(OrderViewModel.class);
        args=getIntent().getExtras();
        if(args!=null){
            order=(Order)args.getSerializable("order");
            userId=Integer.parseInt(args.getString("userId"));
            TextView orderId=(TextView)findViewById(R.id.tvCurOrderId);
            TextView orderDur=(TextView)findViewById(R.id.tvCurOrderDur);
            TextView orderPrice=(TextView)findViewById(R.id.tvCurOderTotal);
            TextView orderStart=(TextView)findViewById(R.id.tvCurOrderStart);
            TextView orderEnd=(TextView)findViewById(R.id.tvCurOrderEnd);
            orderId.setText(String.valueOf(order.getId()));
            orderDur.setText(String.valueOf(order.getDuration()));
            orderPrice.setText(String.format("%.2f",order.getPrice())+'$');
            orderStart.setText(order.getStartDate());
            orderEnd.setText(order.getEndDate());
            TextView carOrderBrand=(TextView)findViewById(R.id.tvCurOrderCarBrand);
            TextView carOrderModel=(TextView)findViewById(R.id.tvCurOrderCarModel);
            TextView carOrderPrice=(TextView)findViewById(R.id.tvCurOrderCarPrice);
            carOrderBrand.setText(order.getCar().getBrand());
            carOrderModel.setText(order.getCar().getModel());
            carOrderPrice.setText(String.valueOf(order.getCar().getPrice())+'$');
        }
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            order.setStatus("completed");
            orderViewModel.updateOrder(order);
            Intent intent=new Intent(CurrentOrderActivity.this, NavBarActivity.class);
            intent.putExtra("user",userId);
            startActivity(intent);
        }
    };
}