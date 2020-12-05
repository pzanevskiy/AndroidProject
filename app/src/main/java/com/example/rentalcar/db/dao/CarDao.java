package com.example.rentalcar.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.rentalcar.db.entity.Car;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM cars")
    LiveData<List<Car>> getAll();

    @Query("SELECT * FROM cars")
    List<Car> getAllCars();

    @Query("SELECT * FROM cars WHERE id=:id")
    Car getCarById(int id);

    @Insert
    void addCar(Car car);

    @Insert
    void addCar(List<Car> cars);

    @Update
    void updateCar(Car car);

    @Delete
    void deleteCar(Car car);
}
