package com.example.rentalcar.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.service.DateService;
import com.example.rentalcar.viewmodel.OrderViewModel;
import com.example.rentalcar.viewmodel.UserViewModel;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class RentCarActivity extends AppCompatActivity {

    Bundle args;
    int userId;
    Car car;
    OrderViewModel orderViewModel;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        orderViewModel=new ViewModelProvider(this).get(OrderViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        args=getIntent().getExtras();
        if(args!=null){
            car=(Car)args.getSerializable("car");
            userId=Integer.parseInt(args.getString("userId"));
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
        User user=userViewModel.getUser(userId);
        if((user.getMoney()-(car.getPrice()*Integer.parseInt(duration.getText().toString())))<0){
            Toast t = Toast.makeText(this,"Not enough money!\nCar is not rented.",Toast.LENGTH_LONG);
            t.show();
        }else {
            order.setCar(car);
            //order.setCarId(car.getId());
            order.setUserId(userId);
            order.setDuration(Integer.parseInt(duration.getText().toString()));
            order.setPrice(order.getDuration()*car.getPrice());
            order.setStatus("current");
            LocalDateTime localDateTime=LocalDateTime.now(DateTimeZone.forID("Europe/Minsk"));
            order.setStartDate(DateService.getParsedDate(localDateTime));
            order.setEndDate(DateService.getParsedDate(DateService.getAfterDurationDateTime(localDateTime,order.getDuration())));
            orderViewModel.addOrder(order);
            user.setMoney(user.getMoney()-order.getPrice());
            userViewModel.updateUser(user);
        }
        Intent intent=new Intent(RentCarActivity.this,NavBarActivity.class);
        intent.putExtra("user",userId);
        startActivity(intent);
    }
}