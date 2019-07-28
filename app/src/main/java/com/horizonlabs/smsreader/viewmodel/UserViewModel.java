package com.horizonlabs.smsreader.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.horizonlabs.smsreader.data.SMSRepository;
import com.horizonlabs.smsreader.data.local.model.UserEntity;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private SMSRepository userRepository;

    private LiveData<List<UserEntity>> allUser;

    public UserViewModel(@NonNull Application context) {
        super(context);
        userRepository = new SMSRepository(context);
        allUser = userRepository.getAllUsers();
    }


    public void insertUser(UserEntity... userEntities) {
        userRepository.insertUser(userEntities);
    }

    public void deleteUser(UserEntity... userEntities) {
        userRepository.deleteUser(userEntities);
    }

    public void update(UserEntity userEntity) {
        // userRepository.update(userEntity);
    }

    public LiveData<List<UserEntity>> getAllUser() {
        if (allUser == null) return userRepository.getAllUsers();
        return allUser;
    }
}
