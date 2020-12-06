package com.example.rentalcar.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE id=:id")
    LiveData<User> getUserById(int id);

    @Query("SELECT * FROM users WHERE email=:email")
    User getUserByEmail(String email);

    @Insert
    void addUser(User user);

    @Insert
    void addUser(List<User> user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
