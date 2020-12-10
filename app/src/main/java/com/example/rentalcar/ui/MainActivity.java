package com.example.rentalcar.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.viewmodel.CarViewModel;
import com.example.rentalcar.viewmodel.UserViewModel;


public class MainActivity extends AppCompatActivity {

    UserViewModel userViewModel;
    CarViewModel carViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel= new ViewModelProvider(this).get(UserViewModel.class);
        carViewModel=new ViewModelProvider(this).get(CarViewModel.class);
//        Car car=new Car();
//        car.setBrand("newCar");
//        car.setModel("iModel");
//        car.setPrice(99.9);
//        for(int i=0;i<10;i++){
//            carViewModel.addCar(car);
//        }
    }


    public void enter(View view) {
        EditText editText=(EditText)findViewById(R.id.editTextMail);
        User user = new User();
        try{
            user= userViewModel.getUserByEmail(editText.getText().toString());
        }catch (Exception e){
            Log.i("ex", "error");
        }
        System.out.println();
            Intent intent=new Intent(MainActivity.this, NavBarActivity.class);
            intent.putExtra("user",user.getId());
            startActivity(intent);
    }

    public void create(View view) {
        Intent intent=new Intent(MainActivity.this, NewUserActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        User user=(User)data.getSerializableExtra("user");
        userViewModel.addUser(user);
        super.onActivityResult(requestCode, resultCode, data);
    }
}