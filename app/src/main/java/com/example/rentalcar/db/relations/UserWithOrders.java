package com.example.rentalcar.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserWithOrders {
    @Embedded
    public User user;

    @Relation(parentColumn = "id", entityColumn = "user_id")
    public List<Order> orders = new ArrayList<>();
}
