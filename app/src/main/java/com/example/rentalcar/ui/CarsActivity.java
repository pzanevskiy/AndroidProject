package com.example.rentalcar.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.adapter.CarsAdapter;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.viewmodel.CarViewModel;

import java.util.List;

public class CarsActivity extends AppCompatActivity {

    private CarsAdapter carsAdapter;
    private CarViewModel carViewModel;
    private ListView carListView;
    Bundle args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        carListView=(ListView)findViewById(R.id.listViewCars);

        args=getIntent().getExtras();

        carViewModel.getCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                carsAdapter=new CarsAdapter(CarsActivity.this, cars);
                carListView.setAdapter(carsAdapter);
                carsAdapter.notifyDataSetChanged();
            }
        });

        ListView cars=(ListView)findViewById(R.id.listViewCars);
        setOnItemClickListener();

    }

    public void setOnItemClickListener(){
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(args!=null){
                    int userId=Integer.parseInt(String.valueOf(args.getInt("user")));
                    Intent intent=new Intent(CarsActivity.this,RentCarActivity.class);
                    Car car = (Car) parent.getItemAtPosition(position);
                    intent.putExtra("userId",userId);
                    intent.putExtra("car",car);
                    startActivity(intent);
                }
            }
        };
        carListView.setOnItemClickListener(itemListener);
    }
}