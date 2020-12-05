package com.example.rentalcar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.dao.CarDao;
import com.example.rentalcar.db.database.RentalDatabase;
import com.example.rentalcar.db.entity.Car;

import java.util.List;

public class CarRepository {

    private RentalDatabase rentalDatabase;
    private CarDao carDao;

    public CarRepository(Application application){
        rentalDatabase=RentalDatabase.getInstance(application);
        carDao=rentalDatabase.carDao();
    }

    public LiveData<List<Car>> getCars(){
        return carDao.getAll();
    }

    public void addCar(Car car){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            carDao.addCar(car);
        });
    }

    public void deleteCar(Car car){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            carDao.deleteCar(car);
        });
    }
}
