package com.example.rentalcar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> getUserById(int id){
        return userRepository.getUserById(id);
    }
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public LiveData<List<User>> getUsers() {
        return userRepository.getUsers();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
