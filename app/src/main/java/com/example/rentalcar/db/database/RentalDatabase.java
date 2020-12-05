package com.example.rentalcar.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rentalcar.db.dao.CarDao;
import com.example.rentalcar.db.dao.OrderDao;
import com.example.rentalcar.db.dao.UserDao;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Car.class, Order.class}, version = 1)
public abstract class RentalDatabase extends RoomDatabase {

    private static final String DB_NAME = "test.db";
    private static final int NUMBER_OF_THREADS = 6;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract UserDao userDao();
    public abstract CarDao carDao();
    public abstract OrderDao orderDao();

    private static volatile RentalDatabase INSTANCE;

    public static RentalDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (RentalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RentalDatabase.class, DB_NAME).fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
