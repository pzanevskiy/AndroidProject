package com.example.rentalcar.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.entity.User;

public class OrderWithUserAndCar {

    @Embedded
    public Order order;

    @Relation(parentColumn = "id", entityColumn = "user_id")
    public User user;

    @Relation(parentColumn = "id", entityColumn = "car_id")
    public Car car;


}
