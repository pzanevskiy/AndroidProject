package com.example.rentalcar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.relations.UserWithOrders;
import com.example.rentalcar.repository.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private OrderRepository orderRepository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
    }

    public LiveData<List<Order>> getOrders(String status, int id) {
        return orderRepository.getUserWithOrders(status, id);
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void deleteOrder(Order order) {
        orderRepository.deleteOrder(order);
    }

    public void updateOrder(Order order) {
        orderRepository.updOrder(order);
    }
}