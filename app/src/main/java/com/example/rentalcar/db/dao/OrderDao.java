package com.example.rentalcar.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.relations.UserWithOrders;

import java.util.List;

@Dao
public interface OrderDao {


    @Query("SELECT * FROM orders WHERE status=:status AND user_id=:id")
    LiveData<List<Order>> getUserWithOrders(String status, int id);

    @Insert
    void addOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Update
    void updateOrder(Order order);
}
