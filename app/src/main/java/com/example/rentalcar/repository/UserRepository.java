package com.example.rentalcar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.rentalcar.db.dao.UserDao;
import com.example.rentalcar.db.database.RentalDatabase;
import com.example.rentalcar.db.entity.User;

import java.util.List;

public class UserRepository {

    private RentalDatabase rentalDatabase;
    private UserDao userDao;

    public UserRepository(Application application){
        rentalDatabase=RentalDatabase.getInstance(application);
        userDao=rentalDatabase.userDao();
    }

    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public LiveData<List<User>> getUsers(){
        return userDao.getAll();
    }

    public void addUser(User user){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            userDao.addUser(user);
        });
    }

    public void deleteUser(User user){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            userDao.deleteUser(user);
        });
    }
}
