package com.example.rentalcar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.dao.CarDao;
import com.example.rentalcar.db.dao.OrderDao;
import com.example.rentalcar.db.database.RentalDatabase;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.relations.UserWithOrders;

import java.util.List;

public class OrderRepository {
    private RentalDatabase rentalDatabase;
    private OrderDao orderDao;

    public OrderRepository(Application application){
        rentalDatabase=RentalDatabase.getInstance(application);
        orderDao=rentalDatabase.orderDao();
    }

    public LiveData<List<UserWithOrders>> getUserWithOrders(String status, int userId){
        return orderDao.getUserWithOrders(status, userId);
    }

    public void addOrder(Order order){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            orderDao.addOrder(order);
        });
    }

    public void deleteOrder(Order order){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            orderDao.deleteOrder(order);
        });
    }
}
