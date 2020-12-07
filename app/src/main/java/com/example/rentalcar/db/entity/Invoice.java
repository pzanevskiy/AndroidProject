package com.example.rentalcar.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoices",
        foreignKeys = {
        @ForeignKey(
                entity = Order.class,
                parentColumns = "id",
                childColumns = "order_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id",
                onDelete = ForeignKey.CASCADE)
})
public class Invoice {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "order_id")
    private int orderId;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "price")
    private double price;

    private String message;

    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
