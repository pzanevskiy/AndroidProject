package com.example.rentalcar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.repository.CarRepository;

import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private CarRepository carRepository;

    public CarViewModel(@NonNull Application application) {
        super(application);
        carRepository=new CarRepository(application);
    }

    public LiveData<List<Car>> getCars(){
        return carRepository.getCars();
    }

    public void addCar(Car car){
        carRepository.addCar(car);
    }

    public void deleteCar(Car car){
        carRepository.deleteCar(car);
    }
}
