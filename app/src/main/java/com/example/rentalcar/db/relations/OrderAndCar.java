package com.example.rentalcar.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;

public class OrderAndCar {
    @Embedded
    public Car car;
    @Relation(parentColumn = "car_id",entityColumn = "car_id")
    public Order order;
}
