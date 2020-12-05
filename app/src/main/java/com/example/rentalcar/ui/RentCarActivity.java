package com.example.rentalcar.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.service.DateService;
import com.example.rentalcar.viewmodel.OrderViewModel;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class RentCarActivity extends AppCompatActivity {

    Bundle args;
    int userId;
    Car car;
    OrderViewModel orderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        orderViewModel=new ViewModelProvider(this).get(OrderViewModel.class);
        args=getIntent().getExtras();
        if(args!=null){
            car=(Car)args.getSerializable("car");
            userId=Integer.parseInt(String.valueOf(args.getInt("userId")));
            TextView brand=(TextView)findViewById(R.id.carBrandRent);
            TextView model=(TextView)findViewById(R.id.carModelRent);
            TextView price=(TextView)findViewById(R.id.carPriceRent);
            brand.setText(car.getBrand());
            model.setText(car.getModel());
            price.setText(String.valueOf(car.getPrice())+'$');
        }
    }

    public void buttonRentClick(View view) {
        EditText duration=(EditText)findViewById(R.id.durRent);
        Order order=new Order();
        order.setCarId(car.getId());
        order.setUserId(userId);
        order.setDuration(Integer.parseInt(duration.getText().toString()));
        order.setPrice(order.getDuration()*car.getPrice());
        order.setStatus("current");
        LocalDateTime localDateTime=LocalDateTime.now(DateTimeZone.forID("Europe/Minsk"));
        order.setStartDate(DateService.getParsedDate(localDateTime));
        order.setEndDate(DateService.getParsedDate(DateService.getAfterDurationDateTime(localDateTime,order.getDuration())));
        orderViewModel.addOrder(order);
        Intent intent=new Intent(RentCarActivity.this,CarsActivity.class);
        intent.putExtra("user",userId);
        startActivity(intent);
    }
}